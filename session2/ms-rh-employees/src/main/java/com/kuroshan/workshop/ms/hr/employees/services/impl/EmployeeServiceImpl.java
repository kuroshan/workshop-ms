package com.kuroshan.workshop.ms.hr.employees.services.impl;

import com.kuroshan.workshop.ms.hr.employees.EmployeeException;
import com.kuroshan.workshop.ms.hr.employees.controllers.response.EmployeeResponse;
import com.kuroshan.workshop.ms.hr.employees.models.Employee;
import com.kuroshan.workshop.ms.hr.employees.repositories.EmployeeRepository;
import com.kuroshan.workshop.ms.hr.employees.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeResponse> findAllEmployees() {
		return employeeRepository.findAll().stream().map(this::buildResponse).collect(Collectors.toList());
	}

	@Override
	public EmployeeResponse findEmployeeById(long id) {
		return buildResponse(employeeRepository.findById(id).orElseThrow(EmployeeException::new));
	}

	private EmployeeResponse buildResponse(Employee e) {
		return EmployeeResponse.builder().employeeId(e.getEmployeeId()).firstName(e.getFirstName())
				.lastName(e.getLastName()).email(e.getEmail()).phoneNumber(e.getPhoneNumber()).hireDate(e.getHireDate())
				.salary(e.getSalary()).commissionPct(e.getCommissionPct()).departmentId(e.getDepartmentId()).build();
	}

}
