package com.kuroshan.workshop.ms.hr.employees.services;

import java.util.List;

import com.kuroshan.workshop.ms.hr.employees.controllers.response.EmployeeResponse;

public interface EmployeeService {

  List<EmployeeResponse> findAllEmployees();
  
  EmployeeResponse findEmployeeById(long id);

}
