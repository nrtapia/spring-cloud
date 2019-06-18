package com.ntapia.springcloud.controller;

import com.ntapia.springcloud.model.Person;
import com.ntapia.springcloud.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public List<Person> findAll() {
        log.info("Find all");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable("id") String id) {
        log.info("Find by id: {}", id);
        return service.get(id).<ResponseStatusException>orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Not Found", null);
        });
    }

    @PostMapping
    public Person add(@RequestBody Person person) {
        log.info("Add: {}", person);
        return service.save(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        log.info("Delete by id: {}", id);
        service.delete(id);
    }

    @PutMapping
    public void update(@RequestBody Person person) {
        log.info("Update: {}", person);
        service.save(person);
    }

    /**
     * PAHT:   person/lastname?lastName=lastName 2
     * @param lastName
     * @return
     */
    @GetMapping("/lastname")
    public List<Person> findByLastName(@RequestParam("lastName") String lastName) {
        log.info("Find by lastName: {}", lastName);
        return service.findByLastName(lastName);
    }

    @GetMapping("/age")
    public List<Person> findByAgeGreaterThan(@RequestParam("age") int age) {
        log.info("Find by age: {}", age);
        return service.findByAgeGreaterThan(age);
    }

}