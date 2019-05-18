package com.cun.conf;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Component
public class KaptchaConfig {
	@Bean //对@Bean理解
	public DefaultKaptcha getDefaultKaptcha() {
		com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
		Properties properties = new Properties(); //对Properties理解
		// 图片边框
		properties.setProperty("kaptcha.border", "yes");
		// 边框颜色
		properties.setProperty("kaptcha.border.color", "105,179,90");
		// 字体颜色
		properties.setProperty("kaptcha.textproducer.font.color", "red");
		// 图片宽
		properties.setProperty("kaptcha.image.width", "110");
		// 图片高
		properties.setProperty("kaptcha.image.height", "40");
		// 字体大小
		properties.setProperty("kaptcha.textproducer.font.size", "30");
		// session key
		properties.setProperty("kaptcha.session.key", "code");
		// 验证码长度
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		// 字体
		properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
		
        /*
         * web容器在初始化时使用一个ServletConfig(即config)对象向JSP页面传递信息，
         * 此配置信息包括初始化参数（在当前Web应用的应用部署描述文件web.xml中定义）
         * 以及表示Servlet或JSP页面所属Web应用的ServletContext对象。
         */
		Config config = new Config(properties); // 对Config的理解
		defaultKaptcha.setConfig(config);

		return defaultKaptcha;
	}
}