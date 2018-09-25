package com.wintig.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.wintig.distributed")
@ServletComponentScan
public class DistributedLockRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedLockRedisApplication.class, args);
	}
}
