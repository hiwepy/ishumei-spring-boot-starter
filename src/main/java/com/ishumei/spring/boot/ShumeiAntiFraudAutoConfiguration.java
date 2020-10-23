package com.ishumei.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	public ShumeiAntiFraudTemplate shumeiAntiFraudTemplate(ShumeiAntiFraudProperties properties,  OkHttpClient okhttp3Client) {
		return new ShumeiAntiFraudTemplate(properties, okhttp3Client);
	}
	
}
