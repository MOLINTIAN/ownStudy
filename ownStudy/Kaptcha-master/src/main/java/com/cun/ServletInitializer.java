package com.cun;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer { 
    /*
     * // SpringBootServletInitializer嵌入式Servlet容器 使用外置Servlet容器的步骤:
     * 
     * 1 必须创建war项目,需要剑豪web项目的目录结构
     * 
     * 2 嵌入式Tomcat依赖scope指定provided
     * 
     * 3 编写SpringBootServletInitializer类子类,并重写configure方法
     */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(VsCodeApplication.class);
	}

}
