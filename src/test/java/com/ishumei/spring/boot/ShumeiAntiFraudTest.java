package com.ishumei.spring.boot;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishumei.spring.boot.model.AntiFraudResponse;
import com.ishumei.spring.boot.model.BatchAntiFraudImageResponse;

import okhttp3.OkHttpClient;

public class ShumeiAntiFraudTest {

	ObjectMapper objectMapper = new ObjectMapper();
	OkHttpClient okhttp3Client = new OkHttpClient.Builder().build();
	ShumeiAntiFraudProperties properties = new ShumeiAntiFraudProperties();

	@Before
	public void setup() {
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		properties.setAccessKey("");
		properties.setChannelTxt("");
		properties.setChannelImg("");
	}

	@Test
	public void testText() {

		try {
			ShumeiAntiFraudTemplate template = new ShumeiAntiFraudTemplate(properties, objectMapper, okhttp3Client);

			AntiFraudResponse response = template.opsForText().antiFraud("SOCIAL", "00001", "你妈的");
			System.out.println(objectMapper.writeValueAsString(response));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testImage() {

		try {
			
			ShumeiAntiFraudTemplate template = new ShumeiAntiFraudTemplate(properties, objectMapper, okhttp3Client);
			
			List<String> imgs = new ArrayList<String>();

			imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603775417273&di=d07584c12801a0a097634667ba944eb2&imgtype=0&src=http%3A%2F%2Fimg011.hc360.cn%2Fhb%2FMTQ2MDYwODAzMjY0ODE5NzM2NTMxMDE%3D.jpg");

			BatchAntiFraudImageResponse response2 = template.opsForImage().antiFraud("POLITICS_PORN_AD", "00001", imgs);
			System.out.println(objectMapper.writeValueAsString(response2));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}