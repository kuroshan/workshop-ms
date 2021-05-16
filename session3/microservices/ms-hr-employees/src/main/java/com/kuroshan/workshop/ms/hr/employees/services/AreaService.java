package com.kuroshan.workshop.ms.hr.employees.services;

import com.kuroshan.workshop.ms.hr.employees.controllers.response.DepartmentResponse;

public interface AreaService {
	DepartmentResponse getDepartment(long id);
}
