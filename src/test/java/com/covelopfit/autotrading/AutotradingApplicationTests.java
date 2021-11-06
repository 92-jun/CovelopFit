package com.covelopfit.autotrading;

import com.covelopfit.autotrading.testdata.PostOrderTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AutotradingApplicationTests {

	@Test
	void contextLoads() {
	}

	protected TestRestTemplate restTemplate = new TestRestTemplate();

	protected HttpHeaders headers = new HttpHeaders();

	@Test
	void givenValidParameterWhenPostOrderThen201(){
		PostOrderTestData postOrderTestData = new PostOrderTestData("KRW-BTC","bid","1","10000","limit","9RrvIQOVEwF6xSgZb8wNZcyIAZTsfsAl1cCJxWli","fMNPQuwsFkPboZVXByNCyg8EAPrUEd3kk2qdvsv7");
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/order").build();
		HttpEntity<String> request = new HttpEntity<>(postOrderTestData.getJsonString(),headers);
		ResponseEntity<String> response = restTemplate.postForEntity(uriComponents.toString(),request, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}
}
