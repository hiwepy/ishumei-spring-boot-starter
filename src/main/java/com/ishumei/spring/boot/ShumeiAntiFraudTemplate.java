package com.ishumei.spring.boot;

import okhttp3.OkHttpClient;

public class ShumeiAntiFraudTemplate {
	
	private OkHttpClient okhttp3Client;
	private final ShumeiAntiFraudTextOperations antiTextOps = new ShumeiAntiFraudTextOperations(this);
	private final ShumeiAntiFraudImageOperations antiImageOps = new ShumeiAntiFraudImageOperations(this);
	private final ShumeiAntiFraudProperties properties;
	
	public ShumeiAntiFraudTemplate(ShumeiAntiFraudProperties properties, OkHttpClient okhttp3Client) {
		this.okhttp3Client = okhttp3Client;
		this.properties = properties;
	}
	
	public ShumeiAntiFraudTextOperations opsForText() {
		return antiTextOps;
	}
	
	public ShumeiAntiFraudImageOperations opsForImage() {
		return antiImageOps;
	}
	
	public ShumeiAntiFraudProperties getProperties() {
		return properties;
	}

	public OkHttpClient getOkhttp3Client() {
		return okhttp3Client;
	}
	
}
