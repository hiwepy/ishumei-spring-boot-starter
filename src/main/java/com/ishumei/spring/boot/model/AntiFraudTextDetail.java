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
package com.ishumei.spring.boot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AntiFraudTextDetail extends AntiFraudDetail {

	/**
	 * 该账号被画像策略标记为高危账号，可能取值：高危账号：1
	 */
	@JsonProperty("isBlackToken")
	private String isBlackToken;

	/**
	 * 命中的敏感词在文本中的位置； 从0开始计数
	 */
	@JsonProperty("hitPosition")
	private String hitPosition;

	/**
	 * 命中的敏感词被替换为*后的文本； 该参数仅在命中敏感词时存在
	 */
	@JsonProperty("filteredText")
	private String filteredText;

	/**
	 * 命中敏感词所在的名单名称； 该参数仅在命中敏感词时存在
	 */
	@JsonProperty("matchedList")
	private String matchedList;

	/**
	 * 命中的具体敏感词； 该参数仅在命中敏感词时存在
	 */
	@JsonProperty("matchedItem")
	private String matchedItem;

	/**
	 * 联系方式识别结果; 包含识别出的微信、微博、QQ、手机号的字符串类型和内容
	 */
	@JsonProperty("contactResult")
	private String contactResult;

	/**
	 * 命中的敏感词详细信息；需要和数美沟通开启相应策略，可以反序列化为json_array
	 */
	@JsonProperty("matchedDetail")
	private AntiFraudTextMatchedDetail matchedDetail;

}
