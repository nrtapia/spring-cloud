package com.ntapia.springcloud.service.impl;

import com.ntapia.springcloud.dao.PersonRepository;
import com.ntapia.springcloud.model.Person;
import com.ntapia.springcloud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Person> get(String id) {
        return repository.findById(id);
    }

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public void delete(String id) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            repository.delete(person.get());
        }
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    @Override
    public List<Person> findByAgeGreaterThan(int age) {
        return repository.findByAgeGreaterThan(age);
    }
}
