package com.covelopfit.autotrading.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.covelopfit.autotrading.dto.OrderApiResponse;
import com.covelopfit.autotrading.dto.OrderForm;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@PropertySource(ignoreResourceNotFound = false, value = "classpath:application_api_key.properties")
public class OrderService extends BaseService {

    private ObjectMapper objectMapper = new ObjectMapper();

    //@Value("${upbit.api.accessKey}")
    private String accessKey = "5kNl3DOeyx5aPDWx9lkVEl1j21q0Y0RwuyIk2B1s";

    //@Value("${upbit.api.secretKey}")
    private String secretKey = "C0GGTPZtxxyuaVySkd7iy1busDMygRxme3BhWJPc";

    //@Value("${upbit.api.serverUrl}")
    private String serverUrl = "https://api.upbit.com/";

    public OrderApiResponse postOrder(OrderForm orderForm){

        OrderApiResponse result = new OrderApiResponse();

        HashMap<String, String> params = new HashMap<>();
        params.put("market", orderForm.getMarket());
        params.put("side", orderForm.getSide());
        params.put("volume", orderForm.getVolume());
        params.put("price", orderForm.getPrice());
        params.put("ord_type", orderForm.getOrd_type());

        ArrayList<String> queryElements = new ArrayList<>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
           queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));


        try {

           MessageDigest md;
           md = MessageDigest.getInstance("SHA-512");
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
           HttpClient client = HttpClientBuilder.create().build();
           HttpPost request = new HttpPost(serverUrl + "/v1/orders");
           request.setHeader("Content-Type", "application/json");
           request.addHeader("Authorization", authenticationToken);
           request.setEntity(new StringEntity(new Gson().toJson(params)));

           HttpResponse response = client.execute(request);
           if (response.getStatusLine().getStatusCode() != 201) {
               return null;
           }

           HttpEntity entity = response.getEntity();
           result = objectMapper.readValue(entity.getContent(), OrderApiResponse.class);

        } catch (IOException | NoSuchAlgorithmException e) {
            return null;
        }

        return result;
   }

    public OrderApiResponse getOrder() {
        OrderApiResponse orderResponse = null;

        HashMap<String, String> params = new HashMap<>();
        params.put("uuid", "5025b250-6df7-4910-bb1b-8c05e5e902b1");

        ArrayList<String> queryElements = new ArrayList<>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        try {
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

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/order?" + queryString);
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            orderResponse = objectMapper.readValue(entity.getContent(),OrderApiResponse.class);

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return orderResponse;
    }

    public OrderApiResponse deleteOrder(String uuid) {
        OrderApiResponse orderResponse = null;
        HashMap<String, String> params = new HashMap<>();
        params.put("uuid", uuid);

        ArrayList<String> queryElements = new ArrayList<>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));


        try {
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

            HttpClient client = HttpClientBuilder.create().build();
            HttpDelete request = new HttpDelete(serverUrl + "/v1/order?" + queryString);
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            orderResponse = objectMapper.readValue(entity.getContent(),OrderApiResponse.class);

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return orderResponse;
    }
}
