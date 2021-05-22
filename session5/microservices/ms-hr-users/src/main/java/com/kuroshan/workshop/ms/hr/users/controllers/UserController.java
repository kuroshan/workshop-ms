package com.kuroshan.workshop.ms.hr.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuroshan.workshop.ms.hr.users.controllers.response.UserResponse;
import com.kuroshan.workshop.ms.hr.users.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "")
	public UserResponse findByUsername(@RequestParam("username") String username) {
		return userService.findByUsername(username);
	}
}
