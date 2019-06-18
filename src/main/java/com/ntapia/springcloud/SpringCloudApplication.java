package com.ntapia.springcloud;

import com.ntapia.springcloud.dao.PersonRepository;
import com.ntapia.springcloud.model.Gender;
import com.ntapia.springcloud.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.stream.IntStream;

@Slf4j
@SpringBootApplication
public class SpringCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudApplication.class, args);
    }


    @Autowired PersonRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        IntStream.range(1, 100).forEach(i -> {
            Person person = new Person();
            person.setAge(i);
            person.setFirstName("name " + i);
            person.setGender(i % 2 == 0 ? Gender.FEMALE : Gender.MALE);
            person.setLastName("lastName " + i);

            log.info("persist : {}", person);
            repository.save(person);
        });
    }

}
