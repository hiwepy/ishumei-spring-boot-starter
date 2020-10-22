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

import com.google.common.base.Joiner;

/**
 * Tim 接口集成
 * https://cloud.tencent.com/document/product/269/42440
 */
public abstract class ShumeiAntiFraudOperations {

	public static final String APPLICATION_JSON_VALUE = "application/json";
	public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

	public static final String DELIMITER = "&";
	public static final String SEPARATOR = "=";
	
	protected final Joiner.MapJoiner joiner = Joiner.on(DELIMITER).withKeyValueSeparator(SEPARATOR);
 
	protected ShumeiAntiFraudTemplate timTemplate;

	public ShumeiAntiFraudOperations(ShumeiAntiFraudTemplate timTemplate) {
		this.timTemplate = timTemplate;
	}
	
	protected <T> T request(String url, Object params, Class<T> cls) {
		return getTimTemplate().toBean(getTimTemplate().requestInvoke(url, params), cls);
	}
	
	public ShumeiAntiFraudTemplate getTimTemplate() {
		return timTemplate;
	}
	
}
