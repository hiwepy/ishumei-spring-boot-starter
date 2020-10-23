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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;

public class ShumeiAntiFraudTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		OkHttpClient okhttp3Client = new OkHttpClient.Builder().build();
		ShumeiAntiFraudProperties properties = new ShumeiAntiFraudProperties();
		//properties.setAccessKey(accessKey);
		//properties.setAppId(appId);
		//properties.setChannelTxt(channelTxt);
		//properties.setChannelImg(channelImg);
		
		ShumeiAntiFraudTemplate template = new ShumeiAntiFraudTemplate(properties, objectMapper, okhttp3Client);
		
	}
	 
}