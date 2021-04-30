package com.kuroshan.workshop.ms.dummy.controllers.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class PersonResponse {
	
	@JsonProperty("codigo")
	private Long id;

	@JsonProperty("first-name")
	private String firstName;

	@JsonProperty("last-name")
	private String lastName;
	
	private int age;

	@JsonIgnore
	private String typeDocument;
	
	@JsonIgnore
	private String idDocument;

}
