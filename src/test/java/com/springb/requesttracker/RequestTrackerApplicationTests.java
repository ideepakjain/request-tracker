package com.springb.requesttracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.springb.requesttracker.entity.Request;

@SpringBootTest(classes = RequestTrackerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestTrackerApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;
    
	@Cacheable(value="rootUrl", key="#url")  
	private String getRootUrl() {
		return "http://localhost:" + port + "/api/v1/users/2";
	}

	@Test
	public void contextLoads() {

	}

	
	@Test
	public void testGetAllRequests() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/requests", HttpMethod.GET, entity,
				String.class);
		assertNotNull(response.getBody());
	}


	
	@Test
	public void testCreateRequest() {
		Request request = new Request();
		request.setRequestCategory("requestCategory");
		request.setRequestDetails("requestDetails");
		request.setRequestPriority("requestPriority");
		request.setRequestSubject("requestSubject");
		ResponseEntity<Request> postResponse = restTemplate.postForEntity(getRootUrl() + "/requests", request,
				Request.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateRequest() {
		int id = 1;
		Request request = restTemplate.getForObject(getRootUrl() + "/requests/" + id, Request.class);
		request.setRequestDetails("requestDetails");
		request.setRequestCategory("requestCategory");
		restTemplate.put(getRootUrl() + "/requests/" + id, request);
		Request updatedRequest = restTemplate.getForObject(getRootUrl() + "/requests/" + id, Request.class);
		assertNotNull(updatedRequest);
	}
	
	@Test
	public void testGetRequestById() {
		Request Request = restTemplate.getForObject(getRootUrl() + "/requests/1", Request.class);
		System.out.println(Request.getId());
		assertNotNull(Request);
	}

	@Test
	public void testDeleteRequest() {
		int id = 1;
		Request Request = restTemplate.getForObject(getRootUrl() + "/requests/" + id, Request.class);
		assertNotNull(Request);
		restTemplate.delete(getRootUrl() + "/requests/" + id);
		try {
			Request = restTemplate.getForObject(getRootUrl() + "/requests/" + id, Request.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}