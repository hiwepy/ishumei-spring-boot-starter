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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AntiFraudTextMatchedDetail {

	/**
	 * 返回码
	 */
	@JsonProperty("listId")
	private String listId;

	/**
	 * 标识昵称或文本内容命中了敏感词； （该参数仅在命中敏感词时存在），可选值： 文本命中敏感词：text, 昵称命中敏感词：nickname
	 */
	@JsonProperty("matchedField")
	private List<String> matchedField;

	/**
	 * 命中敏感词所在的名单名称
	 */
	@JsonProperty("name")
	private String name;

	/**
	 * 命中名单所属的公司标识；其中“GLOBAL”为全局名单
	 */
	@JsonProperty("organization")
	private String organization;

	/**
	 * 命中的对应名单中的所有敏感词
	 */
	@JsonProperty("words")
	private List<String> words;

	/**
	 * 联系方式识别结果; 包含识别出的微信、微博、QQ、手机号的字符串类型和内容
	 */
	@JsonProperty("wordPostitions")
	private List<AntiFraudTextWordPostitionsDetail> wordPostitions;


}
