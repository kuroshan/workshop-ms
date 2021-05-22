package com.kuroshan.workshop.ms.hr.users.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Entity
@Table(name = "USERS")
public class User implements Serializable {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 100)
	private String password;

	@Column(unique = true, length = 100)
	private String email;

	private Boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_roles", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id"), 
			uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "role_id" }) })
	private List<Role> roles;

}
