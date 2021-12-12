package com.covelopfit.autotrading.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.covelopfit.autotrading.dto.AssetResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@PropertySource(ignoreResourceNotFound = false, value = "classpath:application_api_key.properties")
public class AssetService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${upbit.api.accessKey}")
    private String accessKey;

    @Value("${upbit.api.secretKey}")
    private String secretKey;

    @Value("${upbit.api.serverUrl}")
    private String serverUrl;

    public List<AssetResponse> getAllAsset() {

        List<AssetResponse> result = null;
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }

            result = objectMapper.readValue(entity.getContent(), objectMapper.getTypeFactory().constructCollectionType(List.class, AssetResponse.class));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
