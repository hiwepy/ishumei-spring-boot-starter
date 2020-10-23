package com.ishumei.spring.boot;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;

public class ShumeiAntiFraudTemplate {
	
	private ObjectMapper objectMapper;
	private OkHttpClient okhttp3Client;
	private final ShumeiAntiFraudTextOperations antiTextOps = new ShumeiAntiFraudTextOperations(this);
	private final ShumeiAntiFraudImageOperations antiImageOps = new ShumeiAntiFraudImageOperations(this);
	private final ShumeiAntiFraudProperties properties;
	
	public ShumeiAntiFraudTemplate(ShumeiAntiFraudProperties properties, ObjectMapper objectMapper, OkHttpClient okhttp3Client) {
		this.objectMapper = objectMapper;
		this.okhttp3Client = okhttp3Client;
		this.properties = properties;
	}
	
	public ShumeiAntiFraudTextOperations opsForText() {
		return antiTextOps;
	}
	
	public ShumeiAntiFraudImageOperations opsForImage() {
		return antiImageOps;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
	
	public ShumeiAntiFraudProperties getProperties() {
		return properties;
	}

	public OkHttpClient getOkhttp3Client() {
		return okhttp3Client;
	}
	
}
