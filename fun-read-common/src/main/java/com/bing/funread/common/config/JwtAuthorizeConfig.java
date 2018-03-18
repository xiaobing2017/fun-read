package com.bing.funread.common.config;

import com.bing.funread.common.filter.JwtAuthorizeFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class JwtAuthorizeConfig {
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtAuthorizeFilter());
		List<String> urlPatterns = new ArrayList<>();
		urlPatterns.add("/api/v1/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

}
