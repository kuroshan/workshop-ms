package com.kuroshan.workshop.ms.dummy.controllers.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
	@NotNull(message = "First Name cannot be null")
	private String firstName;

	@JsonProperty("last-name")
	private String lastName;

	@Min(value = 18, message = "Age should not be less than 18")
	@Max(value = 65, message = "Age should not be greater than 65")
	private int age;

	@JsonProperty("type-document")
	private String typeDocument;

	@JsonProperty("codigo-document")
	private String idDocument;

}
