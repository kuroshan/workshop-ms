package com.kuroshan.workshop.ms.dummy.controllers;

import com.kuroshan.workshop.ms.dummy.controllers.request.PersonRequest;
import com.kuroshan.workshop.ms.dummy.controllers.response.PersonResponse;
import com.kuroshan.workshop.ms.dummy.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
