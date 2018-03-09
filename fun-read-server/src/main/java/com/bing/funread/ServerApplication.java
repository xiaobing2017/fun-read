package com.bing.funread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class, OAuth2AutoConfiguration.class})
@EnableRetry
@EnableAsync
@ComponentScan(value = {"com.bing.funread"})
public class ServerApplication implements DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);

	private static ConfigurableApplicationContext ctx;

	public static void main(String[] args) {
		ctx = SpringApplication.run(ServerApplication.class, args);
		logger.info("Boot Server started......");
	}

	@Override
	public void destroy() throws Exception {
		if (null != ctx && ctx.isRunning()) {
			ctx.close();
		}
	}
}
