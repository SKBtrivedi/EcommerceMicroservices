package com.nagarro.checkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CheckOutServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckOutServiceApplication.class, args);
	}

}
