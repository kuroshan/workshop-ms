package com.kuroshan.workshop.ms.dummy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuroshan.workshop.ms.dummy.controllers.request.PersonRequest;
import com.kuroshan.workshop.ms.dummy.controllers.response.PersonResponse;
import com.kuroshan.workshop.ms.dummy.services.PersonService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping(value = "")
	public ResponseEntity<PersonResponse> registerPerson(@RequestBody PersonRequest request) {
		return new ResponseEntity<PersonResponse>(personService.createPerson(request), HttpStatus.CREATED);
	}

	@GetMapping(value = "")
	@ApiOperation(value = "View a list of persons", response = PersonResponse.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") 
	})
	public List<PersonResponse> listPeople() {
		return personService.findAllPeople();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PersonResponse> getPerson(@PathVariable long id) {
		PersonResponse response = personService.findByIdPerson(id);
		if (response.getId() != null) {
			return new ResponseEntity<PersonResponse>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<PersonResponse> updatePeople(@PathVariable long id, @RequestBody PersonRequest request) {
		request.setId(id);
		return new ResponseEntity<PersonResponse>(personService.updatePerson(request), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PersonResponse> deletePeople(@PathVariable long id) {
		personService.deletePerson(id);
		return new ResponseEntity<PersonResponse>(HttpStatus.OK);
	}

}
