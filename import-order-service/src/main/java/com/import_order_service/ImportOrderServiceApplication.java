package com.import_order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ImportOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImportOrderServiceApplication.class, args);
	}

}
