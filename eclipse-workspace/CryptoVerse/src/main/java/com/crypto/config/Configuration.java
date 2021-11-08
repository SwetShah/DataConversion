package com.crypto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Data
@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
