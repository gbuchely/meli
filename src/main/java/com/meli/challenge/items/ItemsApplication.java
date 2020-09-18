package com.meli.challenge.items;

import com.meli.challenge.items.service.HealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ItemsApplication {
	private static final Logger logger = LoggerFactory.getLogger(ItemsApplication.class);

	@Autowired
	private HealthService healthService;

	@Bean
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add((request, body, execution) -> {
			long start = System.currentTimeMillis();
			ClientHttpResponse response = execution.execute(request, body);
			long stop = System.currentTimeMillis();
			healthService.registerExternalApiCall(
				request.getMethod(), request.getURI(), response.getStatusCode(), start, stop
			);
			return response;
		});

		return restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(ItemsApplication.class, args);
	}



}
