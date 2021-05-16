package com.kuroshan.workshop.ms.hr.areas.services;

import java.util.List;

import com.kuroshan.workshop.ms.hr.areas.controllers.response.DepartmentResponse;

public interface DepartmentService {

	List<DepartmentResponse> findAll();

	DepartmentResponse findById(long id);

	DepartmentResponse findByIdCustom(long id);

	List<DepartmentResponse> findByDepartmentName(String name);

	List<DepartmentResponse> searchByDepartmentName(String name);

}
