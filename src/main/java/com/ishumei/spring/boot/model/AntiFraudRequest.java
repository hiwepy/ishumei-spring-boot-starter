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
	 *  事件标识，需要联系数美服务开通，请使用数美单独提供的传值为准
	 * 	可选值：
	 * 	headImage：头像
	 * 	album：相册
	 * 	dynamic：动态
	 * 	article：帖子
	 * 	comment：评论
	 * 	roomCover：房间封面
	 * 	groupMessage：群聊图片
	 * 	message：私聊图片
	 * 	product：商品图片
	 */
	@JsonProperty("eventId")
	private String eventId;

	/**
	 * 检测的风险类型，请使用数美单独提供的传值为准
	 * 可选值：
	 * POLITICS：涉政识别
	 * PORN：色情识别
	 * OCR：图片中的OCR文字识别
	 * AD：广告识别
	 * LOGO商企logo识别
	 * MINOR：未成年人识别
	 * SCREEN：特殊画面识别
	 * SCENCE：场景画面识别
	 * QR：二维码识别
	 * QUALITY：图像质量识别
	 * FACE：人脸识别
	 * PORTRAIT：人像识别
	 * BEAUTY：颜值识别
	 * ANIMAL：动物识别
	 * OBJECT：物品识别
	 * VIOLENCE：暴恐识别
	 * BAN：违禁
	 * 如果需要多个识别功能，通过下划线连接
	 */
	@JsonProperty("type")
	private String type;

}
