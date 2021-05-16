package com.kuroshan.workshop.ms.hr.employees.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class DepartmentResponse {

	private long departmentId;
	private String departmentName;
	private LocationResponse location;

	private long locationId;
	private String streetAddress;
	private String countryId;
	private String countryName;
	private long regionId;
	private String regionName;
	
}
