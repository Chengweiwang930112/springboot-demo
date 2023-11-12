package com.demo.coding;

import com.demo.coding.domain.model.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CodingApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@LocalServerPort
	int serverPort;

	@Test
	void testGetUser() {
		ResponseEntity<String> response = restTemplate.exchange("/base/test", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Hello World!", response.getBody());
	}

	@Test
	void testPostUser(){
		UserDO user = new UserDO();
		user.setName("wcwtest");
		user.setEmail("wcw@test.com");
		HttpEntity<UserDO> request = new HttpEntity<>(user);
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + serverPort + "/base/user", request, String.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}

}
