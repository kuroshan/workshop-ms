package com.kuroshan.workshop.ms.dummy.services.impl;

import com.kuroshan.workshop.ms.dummy.controllers.request.PersonRequest;
import com.kuroshan.workshop.ms.dummy.controllers.response.PersonResponse;
import com.kuroshan.workshop.ms.dummy.models.Person;
import com.kuroshan.workshop.ms.dummy.repositories.PersonRepository;
import com.kuroshan.workshop.ms.dummy.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public PersonResponse createPerson(PersonRequest request) {
		return buildResponse(personRepository.save(buildEntity(request)));
	}

	@Override
	public List<PersonResponse> findAllPeople() {
		return personRepository.findAll().stream().map(this::buildResponse).collect(Collectors.toList());
	}

	@Override
	public PersonResponse findByIdPerson(Long id) {
		return buildResponse(personRepository.findById(id).orElse(Person.builder().build()));
	}

	@Override
	public PersonResponse updatePerson(PersonRequest request) {
		Optional<Person> optEntity = personRepository.findById(request.getId());
		if (optEntity.isPresent()) {
			Person entity = optEntity.get();
			entity.setFirstName(request.getFirstName());
			entity.setLastName(request.getLastName());
			entity = personRepository.save(entity);
			return buildResponse(entity);
		} else {
			return PersonResponse.builder().build();
		}
	}

	@Override
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
	
	private Person buildEntity(PersonRequest request) {
		return Person.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.age(request.getAge())
				.typeDocument(request.getTypeDocument())
				.idDocument(request.getIdDocument())
				.build();
	}

	private PersonResponse buildResponse(Person entity) {
		return PersonResponse.builder().id(entity.getId()).firstName(entity.getFirstName())
				.lastName(entity.getLastName()).age(entity.getAge()).typeDocument(entity.getTypeDocument())
				.idDocument(entity.getIdDocument()).build();
	}
}
