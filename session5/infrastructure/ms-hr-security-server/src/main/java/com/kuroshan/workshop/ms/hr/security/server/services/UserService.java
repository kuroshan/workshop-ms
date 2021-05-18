package com.kuroshan.workshop.ms.hr.security.server.services;

import com.kuroshan.workshop.ms.hr.security.server.controllers.response.UserResponse;

public interface UserService {
	UserResponse findByUsername(String username);
}
