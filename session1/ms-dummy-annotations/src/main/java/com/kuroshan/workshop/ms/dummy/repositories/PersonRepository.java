package com.kuroshan.workshop.ms.dummy.repositories;

import com.kuroshan.workshop.ms.dummy.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
