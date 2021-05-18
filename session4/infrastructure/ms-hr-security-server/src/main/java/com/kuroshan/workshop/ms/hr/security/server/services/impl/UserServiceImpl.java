package com.kuroshan.workshop.ms.hr.security.server.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kuroshan.workshop.ms.hr.security.server.controllers.response.UserResponse;
import com.kuroshan.workshop.ms.hr.security.server.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Value("${api.users-service.name}")
	private String apiUsersServiceName;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public UserResponse findByUsername(String username) {
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		return restTemplate.getForObject("http://" + apiUsersServiceName + "/users?username={username}", UserResponse.class, params);
	}

	// SPRING-SECURITY (1)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserResponse userResponse = findByUsername(username);

		if (userResponse == null) {
			log.error("No exist user: " + username);
			throw new UsernameNotFoundException("No exist user: " + username);
		}

		List<GrantedAuthority> authorities = userResponse.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> log.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());

		return new User(userResponse.getUsername(), 
						userResponse.getPassword(), 
						userResponse.getEnabled(), 
						true, 
						true,
						true, 
						authorities);
	}

}
