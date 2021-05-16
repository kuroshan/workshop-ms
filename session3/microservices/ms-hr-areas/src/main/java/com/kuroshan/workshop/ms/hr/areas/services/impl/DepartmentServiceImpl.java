package com.kuroshan.workshop.ms.hr.areas.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kuroshan.workshop.ms.hr.areas.controllers.response.CountryResponse;
import com.kuroshan.workshop.ms.hr.areas.controllers.response.DepartmentResponse;
import com.kuroshan.workshop.ms.hr.areas.controllers.response.LocationResponse;
import com.kuroshan.workshop.ms.hr.areas.controllers.response.RegionResponse;
import com.kuroshan.workshop.ms.hr.areas.daos.AreaDao;
import com.kuroshan.workshop.ms.hr.areas.exceptions.AreaException;
import com.kuroshan.workshop.ms.hr.areas.models.Department;
import com.kuroshan.workshop.ms.hr.areas.repositories.DepartmentRepository;
import com.kuroshan.workshop.ms.hr.areas.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private AreaDao areaDao;

	@Override
	@Transactional(readOnly = true)
	public List<DepartmentResponse> findAll() {
		return departmentRepository.findAll().stream().map(this::buildResponse).collect(Collectors.toList());
	}

	@Override
	public DepartmentResponse findById(long id) {
		return buildResponse(departmentRepository.findById(id).orElseThrow(AreaException::new));
	}

	@Override
	public DepartmentResponse findByIdCustom(long id) {
		return buildResponse(areaDao.findDepartmentByIdCustom(id));
	}

	@Override
	public List<DepartmentResponse> findByDepartmentName(String name) {
		return departmentRepository.findByDepartmentName(name).stream().map(this::buildResponse).collect(Collectors.toList());
	}

	@Override
	public List<DepartmentResponse> searchByDepartmentName(String name) {
		return departmentRepository.searchByDepartmentName(name).stream().map(this::buildResponse).collect(Collectors.toList());
	}

	private DepartmentResponse buildResponse(Department e) {
		return DepartmentResponse.builder()
				.departmentId(e.getDepartmentId())
				.departmentName(e.getDepartmentName())
				.location(LocationResponse.builder()
							.locationId(e.getLocation().getLocationId())
							.streetAddress(e.getLocation().getStreetAddress())
							.postalCode(e.getLocation().getPostalCode())
							.city(e.getLocation().getCity())
							.stateProvince(e.getLocation().getStateProvince())
							.country(CountryResponse.builder()
										.countryId(e.getLocation().getCountry().getCountryId())
										.countryName(e.getLocation().getCountry().getCountryName())
										.region(RegionResponse.builder()
													.regionId(e.getLocation().getCountry().getRegion().getRegionId())
													.regionName(e.getLocation().getCountry().getRegion().getRegionName())
													.build())
										.build())
							.build())
				.build();
	}
	
	private DepartmentResponse buildResponse(Tuple t) {
	    return DepartmentResponse.builder()
		        .departmentId(t.get("departmentId", Long.class))
			    .departmentName(t.get("departmentName", String.class))
			    .locationId(t.get("locationId", Long.class))
			    .streetAddress(t.get("streetAddress", String.class))
			    .countryId(t.get("countryId", String.class))
			    .countryName(t.get("countryName", String.class))
			    .regionId(t.get("regionId", Long.class))
			    .regionName(t.get("regionName", String.class))
		        .build();
	}

}
