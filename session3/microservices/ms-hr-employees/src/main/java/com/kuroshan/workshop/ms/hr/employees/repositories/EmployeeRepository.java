package com.kuroshan.workshop.ms.hr.employees.repositories;

import com.kuroshan.workshop.ms.hr.employees.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {



}
