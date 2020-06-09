package com.shasha.msksfulltesting.bootstrap;

import com.shasha.msksfulltesting.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by kobis on 05 Jun, 2020
 */

@RequiredArgsConstructor
//@Component
@Order(4)
public class InsertingDocuments implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
        mongoTemplate.dropCollection(Person.class);
        //Mongo will create id automatically
        Person p1 = Person.builder()
                .name("Kobi")
                .age(32)
                .build();
        mongoTemplate.insert(p1);

        //Mongo will not generate id for us
        Person p2 = Person.builder()
                .id(UUID.randomUUID().toString())
                .name("Kobi")
                .age(32)
                .build();
        mongoTemplate.insert(p2);

        //Inserting multiple documents - works but inefficient
        //3 round trip to the database
        Person p3 = Person.builder()
                .name("Kobi")
                .age(32)
                .build();

        Person p4 = Person.builder()
                .name("Nofar")
                .age(29)
                .build();

        Person p5 = Person.builder()
                .name("John")
                .age(36)
                .build();
        mongoTemplate.insert(p3);
        mongoTemplate.insert(p4);
        mongoTemplate.insert(p5);

        //Better Solution using Batch Insert
        //onr round trip to the database
        List<Person> people = Arrays.asList(p3,p4,p5);
        mongoTemplate.insertAll(people);



    }
}
