package com.kuroshan.workshop.ms.hr.employees.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kuroshan.workshop.ms.hr.employees.controllers.response.DepartmentResponse;
import com.kuroshan.workshop.ms.hr.employees.services.AreaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AreaServiceImpl implements AreaService {
	
	@Value("${api.support.ms-human-resources-areas.url:http://localhost:8082/ms-hr-areas/v1}")
	private String apiSupportMsHumanResourcesAreas;
	
	@Value("${api.areas-service.name}")
	private String apiAreasServiceName;

	@Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public DepartmentResponse getDepartment(long id) {
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("cb-areas-service");
		Map<String, Object> vars = new HashMap<>();
	    vars.put("id", id);
		return circuitBreaker.run(() -> restTemplate.getForObject("http://" + apiAreasServiceName + "/departments/custom/{id}", DepartmentResponse.class, vars), 
								  throwable -> {
									  	log.error(throwable.getMessage(), throwable);
									  	return defaultDepartment();
									  });
	}

	private DepartmentResponse defaultDepartment() {
        return DepartmentResponse.builder()
        		.build();
    }

}
