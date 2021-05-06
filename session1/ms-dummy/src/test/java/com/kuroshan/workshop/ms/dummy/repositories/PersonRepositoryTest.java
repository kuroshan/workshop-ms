package com.kuroshan.workshop.ms.dummy.repositories;

import com.kuroshan.workshop.ms.dummy.models.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonRepositoryTest {

  @Autowired
  private PersonRepository personRepository;

  @Test
  @DisplayName("Create Person Test ")
  void createPersonTest() {
    Person created = personRepository.save(Person
        .builder()
        .firstName("Elvis")
        .lastName("Argume")
        .age(32)
        .typeDocument("dni")
        .idDocument("123456789")
        .build());
    Assertions.assertTrue(created != null);
  }

}
