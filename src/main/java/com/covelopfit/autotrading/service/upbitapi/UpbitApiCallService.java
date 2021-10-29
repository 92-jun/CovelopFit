package com.covelopfit.autotrading.service.upbitapi;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.covelopfit.autotrading.domain.upbitapi.OrderApiRequest;
import com.covelopfit.autotrading.domain.upbitapi.OrderApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@PropertySource(ignoreResourceNotFound = false, value = "classpath:application_api_key.properties")
public class UpbitApiCallService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${upbit.api.accessKey}")
    private String accessKey;

    @Value("${upbit.api.secretKey}")
    private String secretKey;

    @Value("${upbit.api.serverUrl}")
    private String serverUrl;

    public OrderApiResponse callUpbitOrderApi(OrderApiRequest orderApiRequest) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        OrderApiResponse result = new OrderApiResponse();

        HashMap<String, String> params = this.buildOrderApiRequestParam(orderApiRequest);

        ArrayList<String> queryElements = new ArrayList<>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(serverUrl + "/v1/orders");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);
            request.setEntity(new StringEntity(new Gson().toJson(params)));

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            result = objectMapper.readValue(entity.getContent(), OrderApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private HashMap<String, String> buildOrderApiRequestParam(OrderApiRequest orderApiRequest) {
        HashMap<String, String> params = new HashMap<>();
        params.put("market", orderApiRequest.getMarket());
        params.put("side", orderApiRequest.getSide());
        params.put("volume", orderApiRequest.getVolume());
        params.put("price", orderApiRequest.getPrice());
        params.put("ord_type", orderApiRequest.getOrd_type());

        if (orderApiRequest.getIdentifier() != null) {
            params.put("identifier", orderApiRequest.getIdentifier());
        }

        return params;
    }
}
