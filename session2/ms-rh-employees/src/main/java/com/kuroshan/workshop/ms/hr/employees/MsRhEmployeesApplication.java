package com.kuroshan.workshop.ms.hr.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsRhEmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRhEmployeesApplication.class, args);
	}

}
