package com.ishumei.spring.boot;

import okhttp3.OkHttpClient;

public class ShumeiAntiFraudTemplate {
	
	private OkHttpClient okhttp3Client;
	private final ShumeiAntiFraudTextOperations antiTextOps = new ShumeiAntiFraudTextOperations(this);
	private final ShumeiAntiFraudImageOperations antiImageOps = new ShumeiAntiFraudImageOperations(this);

	public ShumeiAntiFraudTemplate(ShumeiAntiFraudProperties properties, OkHttpClient okhttp3Client) {
		this.okhttp3Client = okhttp3Client;
	}
	
	public ShumeiAntiFraudTextOperations opsForText() {
		return antiTextOps;
	}
	
	public ShumeiAntiFraudImageOperations opsForImage() {
		return antiImageOps;
	}

	public OkHttpClient getOkhttp3Client() {
		return okhttp3Client;
	}
	
}
