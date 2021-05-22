package com.kuroshan.workshop.ms.hr.security.server.controllers.response;

import java.io.Serializable;
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
public class UserResponse implements Serializable {
	private long id;
	private String username;
	private String password;
	private String email;
	private Boolean enabled;
	private List<RoleResponse> roles;
}
