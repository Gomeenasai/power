package com.xiaoer.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.xiaoer.manage.util.CrosFilter;

@MapperScan("com.xiaoer.manage.dao")
@SpringBootApplication
public class PowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerApplication.class, args);

	}
	
	@Bean
	public FilterRegistrationBean regidterFilter() {
		FilterRegistrationBean bean =new FilterRegistrationBean();
		bean.addUrlPatterns("/*");
		bean.setFilter(new CrosFilter());
		return bean;
	}

}
