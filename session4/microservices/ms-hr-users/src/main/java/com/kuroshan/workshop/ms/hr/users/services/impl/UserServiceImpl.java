package com.kuroshan.workshop.ms.hr.users.services.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuroshan.workshop.ms.hr.users.controllers.response.RoleResponse;
import com.kuroshan.workshop.ms.hr.users.controllers.response.UserResponse;
import com.kuroshan.workshop.ms.hr.users.exceptions.UserException;
import com.kuroshan.workshop.ms.hr.users.models.User;
import com.kuroshan.workshop.ms.hr.users.repositories.UserRepository;
import com.kuroshan.workshop.ms.hr.users.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserResponse findByUsername(String username) {
		return buildResponse(userRepository.findByUsername(username).orElseThrow(() -> new UserException("No se encontro al usuario " + username)));
	}
	
	private UserResponse buildResponse(User e) {
		return UserResponse.builder()
				.id(e.getId())
				.username(e.getUsername())
				.password(e.getPassword())
				.email(e.getEmail())
				.enabled(e.getEnabled())
				.roles(e.getRoles().stream().map(r -> RoleResponse.builder()
														.id(r.getId())
														.name(r.getName())
														.build()).collect(Collectors.toList()))
				.build();
	}

}
