package com.kuroshan.workshop.ms.hr.zuul.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class MsHrZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHrZuulServerApplication.class, args);
	}

}
