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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.DigestUtils;
import org.springframework.util.FileCopyUtils;

import com.ishumei.spring.boot.model.AntiFraudImageRequest;
import com.ishumei.spring.boot.model.AntiFraudImageRequestData;
import com.ishumei.spring.boot.model.AntiFraudImageRequestItem;
import com.ishumei.spring.boot.model.AntiFraudResponse;
import com.ishumei.spring.boot.model.BatchAntiFraudImageResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Tim 接口集成 1、帐号管理
 * https://cloud.tencent.com/document/product/269/42440
 */
@Slf4j
public class ShumeiAntiFraudImageOperations extends ShumeiAntiFraudOperations {

	public ShumeiAntiFraudImageOperations(ShumeiAntiFraudTemplate timTemplate) {
		super(timTemplate);
	}

	/**
	 * 1、智能图片识别
	 * API：https://www.ishumei.com/help/documents.html?id=21210
	 * @param type  平台业务类型，可选值(必须大写)；直播：ZHIBO、电商：ECOM、游戏：GAME、新闻资讯 ：NEWS、论坛：FORUM、社交 ：SOCIAL
	 * @param tokenId 客户端用户唯一标识；用于用户行为分析，建议传入用户UID；注：不同用户务必传入不同的tokenId对其进行唯一标识
	 * @param img 要检测的图片；支持格式：jpg，jpeg，jp2，png，webp，gif，bmp，tiff，tif，dib，ppm，pgm，pbm，hdr，pic；建议图片像素不小于256*256
	 * @return 
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public AntiFraudResponse antiFraud(String type, String tokenId, File img) throws UnsupportedEncodingException, IOException {
		String imgBase64 = new String(Base64.getEncoder().encode(FileCopyUtils.copyToByteArray(img)), "ISO-8859-1");
        return this.antiFraud(type, tokenId, imgBase64);
	}
	
	/**
	 * 2、智能图片识别
	 * API：https://www.ishumei.com/help/documents.html?id=21210
	 * @param type  平台业务类型，可选值(必须大写)；直播：ZHIBO、电商：ECOM、游戏：GAME、新闻资讯 ：NEWS、论坛：FORUM、社交 ：SOCIAL
	 * @param tokenId 客户端用户唯一标识；用于用户行为分析，建议传入用户UID；注：不同用户务必传入不同的tokenId对其进行唯一标识
	 * @param img 要检测的图片；支持格式：jpg，jpeg，jp2，png，webp，gif，bmp，tiff，tif，dib，ppm，pgm，pbm，hdr，pic；建议图片像素不小于256*256
	 * @return 
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public AntiFraudResponse antiFraud(String type, String tokenId, InputStream img) throws UnsupportedEncodingException, IOException {
		String imgBase64 = new String(Base64.getEncoder().encode(FileCopyUtils.copyToByteArray(img)), "ISO-8859-1");
        return this.antiFraud(type, tokenId, imgBase64);
	}
	
	/**
	 * 3、智能图片识别
	 * API：https://www.ishumei.com/help/documents.html?id=21210
	 * @param type  平台业务类型，可选值(必须大写)；直播：ZHIBO、电商：ECOM、游戏：GAME、新闻资讯 ：NEWS、论坛：FORUM、社交 ：SOCIAL
	 * @param tokenId 客户端用户唯一标识；用于用户行为分析，建议传入用户UID；注：不同用户务必传入不同的tokenId对其进行唯一标识
	 * @param img 要检测的图片；可使用图片的base64编码或者图片的url链接；支持格式：jpg，jpeg，jp2，png，webp，gif，bmp，tiff，tif，dib，ppm，pgm，pbm，hdr，pic；建议图片像素不小于256*256
	 * @return 
	 */
	public AntiFraudResponse antiFraud(String type, String tokenId, String img) {
		
		AntiFraudImageRequest payload = new AntiFraudImageRequest();
		payload.setAccessKey(getTemplate().getProperties().getAccessKey());
		payload.setAppId(getTemplate().getProperties().getAppId());
		payload.setType(type);
		
		AntiFraudImageRequestData data = new AntiFraudImageRequestData();
		data.setChannel(getTemplate().getProperties().getChannelTxt());
		data.setTokenId(tokenId);
		data.setImg(img);
		
		payload.setData(data);
        
		AntiFraudResponse res = request(getTemplate().getProperties().getAntiFraudImgUrl(), payload, AntiFraudResponse.class);
		if (!res.isSuccess()) {
			log.error("图片识别失败：code: {}、RequestId: {}、Message: {}", res.getCode(), res.getRequestId(), res.getMessage());
		}
		return res;
	}
	
	/**
	 * 4、批量智能图片识别
	 * API：https://www.ishumei.com/help/documents.html?id=21210
	 * @param type  平台业务类型，可选值(必须大写)；直播：ZHIBO、电商：ECOM、游戏：GAME、新闻资讯 ：NEWS、论坛：FORUM、社交 ：SOCIAL
	 * @param tokenId 客户端用户唯一标识；用于用户行为分析，建议传入用户UID；注：不同用户务必传入不同的tokenId对其进行唯一标识
	 * @param imgs 要检测的图片集合；可使用图片的base64编码或者图片的url链接；支持格式：jpg，jpeg，jp2，png，webp，gif，bmp，tiff，tif，dib，ppm，pgm，pbm，hdr，pic；建议图片像素不小于256*256
	 * @return 
	 */
	public BatchAntiFraudImageResponse antiFraud(String type, String tokenId, List<String> imgs) {
		
		AntiFraudImageRequest payload = new AntiFraudImageRequest();
		payload.setAccessKey(getTemplate().getProperties().getAccessKey());
		payload.setAppId(getTemplate().getProperties().getAppId());
		payload.setType(type);
		
		AntiFraudImageRequestData data = new AntiFraudImageRequestData();
		data.setChannel(getTemplate().getProperties().getChannelTxt());
		data.setTokenId(tokenId);
		data.setImgs(imgs.stream().map(img -> {
			AntiFraudImageRequestItem item = new AntiFraudImageRequestItem();
			item.setBtId(DigestUtils.md5DigestAsHex(img.getBytes()));
			item.setImg(img);
			item.setTokenId(tokenId);
			return item;
		}).collect(Collectors.toList()));
		
		payload.setData(data);
        
		BatchAntiFraudImageResponse res = request(getTemplate().getProperties().getAntiFraudImgUrl(), payload, BatchAntiFraudImageResponse.class);
		if (!res.isSuccess()) {
			log.error("图片识别失败：code: {}、RequestId: {}、Message: {}", res.getCode(), res.getRequestId(), res.getMessage());
		}
		return res;
	}
	
}
