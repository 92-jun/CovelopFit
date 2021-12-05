package com.covelopfit.autotrading.service;

import com.covelopfit.autotrading.dto.CommonResponse;
import com.covelopfit.autotrading.dto.TickerApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class TickerService {

    private ObjectMapper objectMapper = new ObjectMapper();

    public CommonResponse getTicker(String marketCodes){

        TickerApiResponse tickerAPIResResult = new TickerApiResponse();

        String upbitURL = "https://api.upbit.com/v1/ticker?markets=" + marketCodes;

        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(upbitURL))
                    .header("Accept", "application/json")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != 200){
                return new CommonResponse(HttpStatus.valueOf(response.statusCode()), "실패");
            }

            String resbody = response.body();

            tickerAPIResResult = objectMapper.readValue(resbody.substring(1,resbody.length()-1), TickerApiResponse.class);

        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

        return new CommonResponse(HttpStatus.valueOf(200), "성공", tickerAPIResResult);
    }
}
