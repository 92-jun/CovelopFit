package com.covelopfit.autotrading.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.covelopfit.autotrading.dto.PostOrderDto;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
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
public class OrderService {

    public boolean postOrder(PostOrderDto postOrderDto){


       String serverUrl = "https://api.upbit.com/v1/orders";
       String secretKey = postOrderDto.getSecretKey();
       String accessKey = postOrderDto.getAccessKey();
       HashMap<String, String> params = new HashMap<>();
       params.put("market", postOrderDto.getMarket());
       params.put("side", postOrderDto.getSide());
       params.put("volume", postOrderDto.getVolume());
       params.put("price", postOrderDto.getPrice());
       params.put("ord_type", postOrderDto.getOrd_type());

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
           HttpPost request = new HttpPost(serverUrl);
           request.setHeader("Content-Type", "application/json");
           request.addHeader("Authorization", authenticationToken);
           request.setEntity(new StringEntity(new Gson().toJson(params)));

           HttpResponse response = client.execute(request);
           HttpEntity entity = response.getEntity();

           System.out.println(EntityUtils.toString(entity, "UTF-8"));
       } catch (IOException | NoSuchAlgorithmException e) {

           e.printStackTrace();

       }

       return true;
   }
}
