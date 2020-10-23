package com.ishumei.spring.boot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IM响应结果
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AntiFraudTextResponse extends AntiFraudResponse{

	/**
	 * 风险详情
	 */
	@JsonProperty("detail")
	private AntiFraudTextDetail detail;
	
}
