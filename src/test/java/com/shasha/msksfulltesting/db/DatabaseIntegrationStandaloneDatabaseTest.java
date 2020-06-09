package com.shasha.msksfulltesting.db;

import com.shasha.msksfulltesting.domain.Webinar;
import com.shasha.msksfulltesting.repo.WebinarRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by kobis on 09 Jun, 2020
 */
@Ignore
@DataMongoTest
@RunWith(SpringRunner.class)
@Import(RealDBTestConfiguration.class)
public class DatabaseIntegrationStandaloneDatabaseTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    WebinarRepository webinarRepository;


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

}
