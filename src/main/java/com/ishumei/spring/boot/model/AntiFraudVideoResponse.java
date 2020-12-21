package com.ishumei.spring.boot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * IM响应结果
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AntiFraudVideoResponse {

	/**
	 * 返回码，详见常见错误码除message和requestId之外的字段，只有当code为1100时才会存在 code 见：
	 * https://www.ishumei.com/help/documents.html?id=24000
	 */
	@JsonProperty("code")
	private String code;

	/**
	 * 返回码详情描述
	 */
	@JsonProperty("message")
	private String message;
	
	/**
	 * 请求唯一标识，后续可用于数据查询
	 */
	@JsonProperty("requestId")
	private String requestId;



}
