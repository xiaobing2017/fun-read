package com.bing.funread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableRetry
@EnableAsync
@ComponentScan(value = {"com.bing.funread"})
public class FunReadServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunReadServerApplication.class, args);
	}
}
