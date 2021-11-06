package com.covelopfit.autotrading.controller;

import com.covelopfit.autotrading.testdata.PostOrderTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ExtendWith({ SpringExtension.class })
public class OrderControllerTest {

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private String accessKey;
    private String secretKey;

    @Test
    void givenValidParameterWhenPostOrderThen201(){

        PostOrderTestData postOrderTestData = new PostOrderTestData("KRW-BTC","bid","1","10000","limit",accessKey,secretKey);

        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/order").build();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(postOrderTestData.getJsonString(),headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uriComponents.toString(),request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
