package com.kuroshan.workshop.ms.hr.security.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class MsHrSecurityServerApplication implements CommandLineRunner {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MsHrSecurityServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "123456";
		for (int i = 0; i < 2; i++) {
			log.info(passwordEncoder.encode(password));
		}
	}

}
