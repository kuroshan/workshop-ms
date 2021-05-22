package com.kuroshan.workshop.ms.hr.users.controllers.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserResponse {
	private long id;
	private String username;
	private String password;
	private String email;
	private Boolean enabled;
	private List<RoleResponse> roles;
}
