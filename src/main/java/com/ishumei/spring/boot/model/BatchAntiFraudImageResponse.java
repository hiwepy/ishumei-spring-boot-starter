package com.ishumei.spring.boot.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 响应结果
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BatchAntiFraudImageResponse {

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

	/**
	 * 每张图片的识别结果（code 为 1100 时存在）
	 */
	@JsonProperty("imgs")
	private List<BatchAntiFraudImageItem> imgs;

	/**
	 * 整形数组，长度为 4，分别表示一次批量图片 请求中拒绝数、审核数、通过数（code 为 1100 时存在）和错误数
	 */
	@JsonProperty("statistics")
	private List<Integer> statistics;

	public boolean isSuccess() {
		return Objects.nonNull(code) && code.equals("1100");
	}

}
