package com.ishumei.spring.boot;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;


@Configuration
@EnableConfigurationProperties(ShumeiAntiFraudProperties.class)
public class ShumeiAntiFraudAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public OkHttpClient okhttp3Client() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(6L, TimeUnit.SECONDS);
        builder.readTimeout(6L, TimeUnit.SECONDS);
        builder.writeTimeout(6L, TimeUnit.SECONDS);
        ConnectionPool connectionPool = new ConnectionPool(50, 60, TimeUnit.SECONDS);
        builder.connectionPool(connectionPool);
        return builder.build();
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
