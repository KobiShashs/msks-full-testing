package com.shasha.msksfulltesting.bootstrap;

import com.shasha.msksfulltesting.domain.Presentation;
import com.shasha.msksfulltesting.domain.Webinar;
import com.shasha.msksfulltesting.domain.WebinarInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.UUID;

/**
 * Created by kobis on 05 Jun, 2020
 */

@RequiredArgsConstructor
//@Component
@Order(5)
public class UpdatingDocuments implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;
    private static int COUNTER = 0;

    @Override
    public void run(String... args) throws Exception {
        mongoTemplate.dropCollection(Webinar.class);

        // Init some data
        Webinar webinar1 = Webinar.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(25)
                .subject("Spring Data MongoDB")
                .presentation(Presentation.builder().isReady(true).build())
                .build();

        mongoTemplate.insert(webinar1);

        // Update using the save method
        webinar1.setSubject("Spring Data Umbrella Project Overview ");
        webinar1.setSeats(10);
        webinar1.setPresentation(Presentation.builder().isReady(false).build());
        mongoTemplate.save(webinar1);

        // Multi Update
        Query webinarsByJohnBryceTLV = Query.query(
                Criteria.where("city").is("TLV")
        );

        Update setCityHaifa = Update.update("city","Haifa");
        mongoTemplate.updateMulti(webinarsByJohnBryceTLV,setCityHaifa,WebinarInformation.class);

    }
}
