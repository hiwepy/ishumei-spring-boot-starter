package com.ishumei.spring.boot;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;


@Configuration
@EnableConfigurationProperties({ShumeiAntiFraudProperties.class, ShumeiOkHttp3Properties.class})
public class ShumeiAntiFraudAutoConfiguration {
	
	@Bean
	public ShumeiAntiFraudTemplate shumeiAntiFraudTemplate(ShumeiAntiFraudProperties properties,  
			ShumeiOkHttp3Properties okHttp3Properties,
			ObjectProvider<ObjectMapper> objectMapperProvider,	
			ObjectProvider<OkHttpClient> okhttp3ClientProvider) {
		
		ObjectMapper objectMapper = objectMapperProvider.getIfAvailable(() -> { 
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			return mapper;
		});
		
		OkHttpClient okhttp3Client = okhttp3ClientProvider.getIfAvailable(() -> { 
			
			OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.callTimeout(okHttp3Properties.getCallTimeout(), TimeUnit.SECONDS)
				.connectionPool(new ConnectionPool(okHttp3Properties.getMaxIdleConnections(),
						okHttp3Properties.getKeepAliveDuration().getSeconds(), TimeUnit.SECONDS))
				.connectTimeout(okHttp3Properties.getConnectTimeout(), TimeUnit.SECONDS)
				.followRedirects(okHttp3Properties.isFollowRedirects())
				.followSslRedirects(okHttp3Properties.isFollowSslRedirects())
				.pingInterval(okHttp3Properties.getPingInterval(), TimeUnit.SECONDS)
				.readTimeout(okHttp3Properties.getReadTimeout(), TimeUnit.SECONDS)
				.retryOnConnectionFailure(okHttp3Properties.isRetryOnConnectionFailure())
				.writeTimeout(okHttp3Properties.getWriteTimeout(), TimeUnit.SECONDS);
				
	        return builder.build();
	        
		});
		
		return new ShumeiAntiFraudTemplate(properties, objectMapper, okhttp3Client);
	}
	
}
