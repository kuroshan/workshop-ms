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
public class LocationResponse {
	  private long locationId;
	  private String streetAddress;
	  private String postalCode;
	  private String city;
	  private String stateProvince;
	  private CountryResponse country;
}
