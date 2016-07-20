package com.demo.java.common.listener;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * 启动Web容器时,自动装配ApplicationContext的配置信息
 */
public class WebContextListener extends ContextLoaderListener {

    @Override
    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    欢迎使用 WebMagic 爬虫\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
        return super.initWebApplicationContext(servletContext);
    }
}
