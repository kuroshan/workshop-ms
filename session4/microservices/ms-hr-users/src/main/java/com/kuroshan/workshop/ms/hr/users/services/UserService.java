package com.kuroshan.workshop.ms.hr.users.services;

import com.kuroshan.workshop.ms.hr.users.controllers.response.UserResponse;

public interface UserService {
	UserResponse findByUsername(String username);
}
