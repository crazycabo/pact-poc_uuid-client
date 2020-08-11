package com.ingendevelopment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PactPocUuidClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PactPocUuidClientApplication.class, args);
	}

}
