package com.ishumei.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;


@Configuration
@EnableConfigurationProperties(ShumeiAntiFraudProperties.class)
public class ShumeiAntiFraudAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public OkHttpClient okhttp3Client() {
		return new OkHttpClient.Builder().build();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return objectMapper;
	}
	
	@Bean
	public ShumeiAntiFraudTemplate shumeiAntiFraudTemplate(ShumeiAntiFraudProperties properties,  
			ObjectMapper objectMapper, OkHttpClient okhttp3Client) {
		return new ShumeiAntiFraudTemplate(properties, objectMapper, okhttp3Client);
	}
	
}
