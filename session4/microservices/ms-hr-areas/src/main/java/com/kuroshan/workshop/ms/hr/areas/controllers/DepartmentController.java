package com.kuroshan.workshop.ms.hr.areas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kuroshan.workshop.ms.hr.areas.controllers.response.DepartmentResponse;
import com.kuroshan.workshop.ms.hr.areas.services.DepartmentService;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departamentService;

	@GetMapping(value = "")
	@ResponseStatus(HttpStatus.OK)
	public List<DepartmentResponse> listDepartaments(@RequestParam(value = "name", required = false) String name) {
		List<DepartmentResponse> list;
		if (name != null) {
			list = departamentService.searchByDepartmentName(name);
		} else {
			list = departamentService.findAll();
		}
		return list;
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DepartmentResponse getDepartment(@PathVariable long id) {
		return departamentService.findById(id);
	}

	@GetMapping(value = "/custom/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DepartmentResponse getDepartmentCustom(@PathVariable long id) {
		return departamentService.findByIdCustom(id);
	}

}
