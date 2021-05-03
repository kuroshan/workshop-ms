package com.kuroshan.workshop.ms.hr.employees.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuroshan.workshop.ms.hr.employees.controllers.response.DepartmentResponse;
import com.kuroshan.workshop.ms.hr.employees.feignclient.AreaFeignClient;
import com.kuroshan.workshop.ms.hr.employees.services.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaFeignClient areaFeignClient;

	@Override
	public DepartmentResponse getDepartment(long id) {
		return areaFeignClient.getDepartment(id);
	}

}
