package com.shasha.msksfulltesting.db;

import com.shasha.msksfulltesting.domain.Presentation;
import com.shasha.msksfulltesting.domain.Webinar;
import com.shasha.msksfulltesting.repo.WebinarRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by kobis on 08 Jun, 2020
 */
@DataMongoTest
@RunWith(SpringRunner.class)
public class DatabaseIntegrationEmbeddedDatabaseTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    WebinarRepository webinarRepository;
    private static int COUNTER = 0;
    List<Webinar> webinars;

    @Before
    public void setup() {
        Webinar webinar1 = Webinar.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(25)
                .subject("Spring Data MongoDB")
                .presentation(Presentation.builder().isReady(true).build())
                .build();

        Webinar webinar2 = Webinar.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(15)
                .subject("Spring Cloud")
                .presentation(Presentation.builder().isReady(true).build())
                .build();

        Webinar webinar3 = Webinar.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(20)
                .subject("WTF is TS?!")
                .presentation(Presentation.builder().isReady(true).build())
                .build();

        Webinar webinar4 = Webinar.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(60)
                .subject("Angular 9 New Features")
                .presentation(Presentation.builder().isReady(true).build())
                .build();

        Webinar webinar5 = Webinar.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(10)
                .subject("React Native Vs. Flutter")
                .presentation(Presentation.builder().isReady(true).build())
                .build();

        webinars = Arrays.asList(webinar1, webinar2, webinar3, webinar4, webinar5);
        this.mongoTemplate.insertAll(webinars);

    }

    @Test
    public void findAllWebinars() {
        assertEquals(5, mongoTemplate.findAll(Webinar.class).size());
    }

    @Test
    public void findBySeatsBetween() {
        assertEquals(2, webinarRepository.findBySeatsBetween(15, 30).size());
    }

    @Test
    public void findSmallMeetups(){
        assertEquals(4, webinarRepository.findSmallMeetups().size());
    }


    @Test
    public void testSubjectName(){
        Webinar fromDb = this.webinarRepository.findById(webinars.get(0).getId()).get();
        assertEquals(this.webinarRepository.findById(webinars.get(0).getId()).get().getSubject(),"Spring Data MongoDB");
    }
    @After
    public void clear() {
        this.mongoTemplate.dropCollection(Webinar.class);
    }

}
