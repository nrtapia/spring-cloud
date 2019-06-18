package com.ntapia.springcloud.service;

import com.ntapia.springcloud.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Optional<Person> get(String id);

    Person save(Person person);

    void delete(String id);

    List<Person> findAll();

    List<Person> findByLastName(String lastName);

    List<Person> findByAgeGreaterThan(int age);

}
