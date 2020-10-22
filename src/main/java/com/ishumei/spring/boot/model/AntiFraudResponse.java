package com.ishumei.spring.boot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * IM响应结果
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AntiFraudResponse {

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
	 * 风险分数；取值范围[0,1000]，分数越高风险越大
	 */
	@JsonProperty("score")
	private int score;

	/**
	 * 风险级别 可能返回值： PASS：正常内容，建议直接放行 REVIEW：可疑内容，建议人工审核 REJECT：违规内容，建议直接拦截
	 */
	@JsonProperty("riskLevel")
	private String riskLevel;

	/**
	 * 提示服务是否超时; 正常：0; 超时：501
	 */
	@JsonProperty("status")
	private int status;

	/**
	 * 风险详情
	 */
	@JsonProperty("detail")
	private AntiFraudDetail detail;
	
	public boolean isSuccess() {
		return status == 0 && code.equals("1100");
	}
}
