package com.carrental.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CarRentalApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApiGatewayApplication.class, args);
	}

}
