package com.kuroshan.workshop.ms.dummy.services;

import com.kuroshan.workshop.ms.dummy.controllers.request.PersonRequest;
import com.kuroshan.workshop.ms.dummy.controllers.response.PersonResponse;

import java.util.List;

public interface PersonService {

  PersonResponse createPerson(PersonRequest request);
  List<PersonResponse> findAllPeople();
  PersonResponse findByIdPerson(Long id);
  PersonResponse updatePerson(PersonRequest request);
  void deletePerson(Long id);

}
