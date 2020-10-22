package com.ishumei.spring.boot;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 	数美接口集成 
 * https://www.ishumei.com/help/documents.html?id=21110
 */
@Slf4j
public class ShumeiAntiFraudTemplate {

	public final static String APPLICATION_JSON_VALUE = "application/json";
	public final static String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	private OkHttpClient okhttp3Client;

	private final ShumeiAntiFraudImageOperations antiOps = new ShumeiAntiFraudImageOperations(this);

	public ShumeiAntiFraudTemplate(ShumeiAntiFraudProperties properties, OkHttpClient okhttp3Client) {
		this.okhttp3Client = okhttp3Client;
	}

	public ShumeiAntiFraudImageOperations opsForImage() {
		return antiOps;
	}
	
	public <T> T request(String url, Object params, Class<T> cls) {
		return toBean(requestInvoke(url, params), cls);
	}

	public <T> T toBean(String json, Class<T> cls) {
		try {
			return objectMapper.readValue(json, cls);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * http 请求service
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public String requestInvoke(String url, Object params) {
		String json = null;
		try {
			RequestBody requestBody = RequestBody.create(MediaType.parse(APPLICATION_JSON_VALUE),
					objectMapper.writeValueAsString(params));
			Request request = new Request.Builder().url(url).post(requestBody).build();
			json = okhttp3Client.newCall(request).execute().body().string();
			log.info("数美 响应 {}", json);
		} catch (Exception e) {
			log.error("数美 请求异常", e);
		}
		return json;
	}

}
