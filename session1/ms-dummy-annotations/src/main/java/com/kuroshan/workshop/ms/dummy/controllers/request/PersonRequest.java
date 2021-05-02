package com.kuroshan.workshop.ms.dummy.controllers.request;

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
public class PersonRequest {
	
	@JsonProperty("codigo")
	private Long id;

	@JsonProperty("first-name")
	private String firstName;

	@JsonProperty("last-name")
	private String lastName;
	
	private int age;

	@JsonProperty("type-document")
	private String typeDocument;
	
	@JsonProperty("codigo-document")
	private String idDocument;

}
