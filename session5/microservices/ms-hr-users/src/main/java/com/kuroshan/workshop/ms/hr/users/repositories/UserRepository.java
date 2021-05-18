package com.kuroshan.workshop.ms.hr.users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kuroshan.workshop.ms.hr.users.exceptions.UserException;
import com.kuroshan.workshop.ms.hr.users.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username) throws UserException;

}
