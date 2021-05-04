package com.kuroshan.workshop.ms.hr.employees.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kuroshan.workshop.ms.hr.employees.controllers.response.DepartmentResponse;

@FeignClient(name = "areas-service", url = "${api.support.ms-human-resources-areas.url}")
public interface AreaFeignClient {
	
	@GetMapping(value = "/departments/custom/{id}")
	DepartmentResponse getDepartment(@PathVariable long id);
	
}
