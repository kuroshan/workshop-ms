package com.kuroshan.workshop.ms.hr.employees.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kuroshan.workshop.ms.hr.employees.controllers.response.DepartmentResponse;
import com.kuroshan.workshop.ms.hr.employees.services.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	
	@Value("${api.support.ms-human-resources-areas.url:http://localhost:8082/ms-hr-areas/v1}")
	private String apiSupportMsHumanResourcesAreas;
	
	@Value("${api.areas-service.name}")
	private String apiAreasServiceName;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public DepartmentResponse getDepartment(long id) {
		Map<String, Object> vars = new HashMap<>();
	    vars.put("id", id);
		return restTemplate.getForObject("http://" + apiAreasServiceName + "/departments/custom/{id}", DepartmentResponse.class, vars);
	}

}
