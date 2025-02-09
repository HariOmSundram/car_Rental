package com.example.p08_Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class P08GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(P08GatewayApplication.class, args);
	}

}
