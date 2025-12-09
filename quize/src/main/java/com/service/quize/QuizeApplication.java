package com.service.quize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients
public class QuizeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizeApplication.class, args);
	}

}
