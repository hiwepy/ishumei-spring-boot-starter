/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.ishumei.spring.boot;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 	数美接口集成 
 * https://www.ishumei.com/help/documents.html?id=21110
 */
@Slf4j
public abstract class ShumeiAntiFraudOperations {

	public static final String APPLICATION_JSON_VALUE = "application/json";
	public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";
	
	public static final String DELIMITER = "&";
	public static final String SEPARATOR = "=";
	
	protected final Joiner.MapJoiner joiner = Joiner.on(DELIMITER).withKeyValueSeparator(SEPARATOR);
	protected final ObjectMapper objectMapper = new ObjectMapper();
 
	protected ShumeiAntiFraudTemplate template;

	public ShumeiAntiFraudOperations(ShumeiAntiFraudTemplate template) {
		this.template = template;
	}
	
	public ShumeiAntiFraudTemplate getTemplate() {
		return template;
	}
	
	protected <T> T request(String url, Object params, Class<T> cls) {
		return toBean(requestInvoke(url, params), cls);
	}

	protected <T> T toBean(String json, Class<T> cls) {
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
	protected String requestInvoke(String url, Object params) {
		String json = null;
		try {
			RequestBody requestBody = RequestBody.create(MediaType.parse(APPLICATION_JSON_VALUE),
					objectMapper.writeValueAsString(params));
			Request request = new Request.Builder().url(url).post(requestBody).build();
			json = getTemplate().getOkhttp3Client().newCall(request).execute().body().string();
			log.info("数美 响应 {}", json);
		} catch (Exception e) {
			log.error("数美 请求异常", e);
		}
		return json;
	}

}
