package com.ishumei.spring.boot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 识别请求
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AntiFraudRequest {

	/**
	 * 应用标识； 用于区分相同公司的不同应用，该参数传递值可与数美服务协商用于区分应用； 默认应用值：default
	 */
	@JsonProperty("appId")
	private String appId = "default";
	
	/**
	 * 公司密钥；用于权限认证，开通账号服务时由数美提供
	 */
	@JsonProperty("accessKey")
	private String accessKey;

	/**
	 * 平台业务类型，可选值(必须大写) 直播：ZHIBO 电商：ECOM 游戏：GAME 新闻资讯 ：NEWS 论坛：FORUM 社交 ：SOCIAL
	 */
	@JsonProperty("type")
	private String type;

}
