package com.kuroshan.workshop.ms.hr.employees.models;

import java.sql.Time;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "EMPLOYEES", schema = "HR", catalog = "")
public class Employee {

	@Id
	@Column(name = "EMPLOYEE_ID")
	private long employeeId;

	@Basic
	@Column(name = "FIRST_NAME")
	private String firstName;

	@Basic
	@Column(name = "LAST_NAME")
	private String lastName;

	@Basic
	@Column(name = "EMAIL")
	private String email;

	@Basic
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Basic
	@Column(name = "HIRE_DATE")
	private Time hireDate;

	@Basic
	@Column(name = "SALARY")
	private Long salary;

	@Basic
	@Column(name = "COMMISSION_PCT")
	private Long commissionPct;

	@Basic
	@Column(name = "DEPARTMENT_ID")
	private Long departmentId;

}
