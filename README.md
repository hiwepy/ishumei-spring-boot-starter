# ishumei-spring-boot-starter

#### 组件简介

> 基于 [数美科技 ](https://www.ishumei.com/) API 实现的文本识别、图片识别


#### 使用说明

##### 1、Spring Boot 项目添加 Maven 依赖

``` xml
<dependency>
	<groupId>com.github.hiwepy</groupId>
	<artifactId>ishumei-spring-boot-starter</artifactId>
	<version>1.0.1.RELEASE</version>
</dependency>
```

##### 2、在`application.yml`文件中增加如下配置

```yaml
#################################################################################################
### 数美 配置：
#################################################################################################
shumei:
  anti-fraud:
    access-key: xx
    app-id: xxx 
    channel-txt: 
    channel-img: 
    channel-audio: 
    channel-video: 
```

##### 3、使用示例

 
```java

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishumei.spring.boot.model.AntiFraudResponse;
import com.ishumei.spring.boot.model.BatchAntiFraudImageResponse;

@SpringBootApplication
public class ShumeiAntiFraudApplication_Test {
	
	@Autowired
	private ShumeiAntiFraudTemplate template;
	@Autowired
	private ObjectMapper objectMapper;
	
	@PostConstruct
	public void testText() {

		try {

			AntiFraudResponse response = template.opsForText().antiFraud("SOCIAL", "00001", "你妈的");
			System.out.println(objectMapper.writeValueAsString(response));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@PostConstruct
	public void testImage() {

		try {
			
			List<String> imgs = new ArrayList<String>();

			imgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1603775417273&di=d07584c12801a0a097634667ba944eb2&imgtype=0&src=http%3A%2F%2Fimg011.hc360.cn%2Fhb%2FMTQ2MDYwODAzMjY0ODE5NzM2NTMxMDE%3D.jpg");

			BatchAntiFraudImageResponse response2 = template.opsForImage().antiFraud("POLITICS_PORN_AD", "00001", imgs);
			System.out.println(objectMapper.writeValueAsString(response2));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ShumeiAntiFraudApplication_Test.class, args);
	}

}
```

## Jeebiz 技术社区

Jeebiz 技术社区 **微信公共号**、**小程序**，欢迎关注反馈意见和一起交流，关注公众号回复「Jeebiz」拉你入群。

|公共号|小程序|
|---|---|
| ![](https://raw.githubusercontent.com/hiwepy/static/main/images/qrcode_for_gh_1d965ea2dfd1_344.jpg)| ![](https://raw.githubusercontent.com/hiwepy/static/main/images/gh_09d7d00da63e_344.jpg)|