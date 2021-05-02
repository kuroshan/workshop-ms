package com.kuroshan.workshop.ms.hr.employees.controllers;

import com.kuroshan.workshop.ms.hr.employees.controllers.response.EmployeeResponse;
import com.kuroshan.workshop.ms.hr.employees.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping(value = "")
  @ResponseStatus(HttpStatus.OK)
  public List<EmployeeResponse> listEmployees() {
    return employeeService.findAllEmployees();
  }
  
  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public EmployeeResponse getEmployee(@PathVariable long id) {
    return employeeService.findEmployeeById(id);
  }

}
