package com.kuroshan.workshop.ms.hr.employees.controllers.response;

import java.sql.Time;

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
public class EmployeeResponse {
	private long employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Time hireDate;
	private Long salary;
	private Long commissionPct;
	private Long departmentId;
}
