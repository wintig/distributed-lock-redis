package com.wintig.distributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistributedLockRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedLockRedisApplication.class, args);
	}
}
