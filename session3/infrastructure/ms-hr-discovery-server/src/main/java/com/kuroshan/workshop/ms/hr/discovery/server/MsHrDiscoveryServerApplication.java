package com.kuroshan.workshop.ms.hr.discovery.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsHrDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHrDiscoveryServerApplication.class, args);
	}

}
