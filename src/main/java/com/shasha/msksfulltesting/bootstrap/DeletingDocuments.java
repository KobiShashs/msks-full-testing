package com.shasha.msksfulltesting.bootstrap;

import com.shasha.msksfulltesting.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by kobis on 05 Jun, 2020
 */
@RequiredArgsConstructor
//@Component
//@Order(6)
public class DeletingDocuments implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;
    private static int COUNTER = 0;

    @Override
    public void run(String... args) throws Exception {
        mongoTemplate.dropCollection(Webinar.class);
        mongoTemplate.dropCollection(WebinarInformation.class);

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
                .seats(15)
                .subject("WTF is TS?!")
                .presentation(Presentation.builder().isReady(true).build())
                .build();

        WebinarInformation webinarInformation1 = WebinarInformation.builder()
                .id(UUID.randomUUID().toString())
                .hostCity("TLV")
                .hostName("John Bryce")
                .isFree(true)
                .webinar(webinar1)
                .description("Learn about Spring Data MongoDB...")
                .durationMin(120)
                .internalId("beta" + (COUNTER++))
                .createdAt(LocalDate.now())
                .webinarType(WebinarType.ZOOM)
                .build();

        WebinarInformation webinarInformation2 = WebinarInformation.builder()
                .id(UUID.randomUUID().toString())
                .hostCity("TLV")
                .hostName("John Bryce")
                .isFree(true)
                .webinar(webinar1)
                .description("Learn about Spring Data Project & MongoDB...")
                .durationMin(90)
                .internalId("beta" + (COUNTER++))
                .createdAt(LocalDate.now().plusDays(7))
                .webinarType(WebinarType.ZOOM)
                .build();

        WebinarInformation webinarInformation3 = WebinarInformation.builder()
                .id(UUID.randomUUID().toString())
                .hostCity("Haifa")
                .hostName("John Bryce")
                .isFree(true)
                .webinar(webinar2)
                .description("How Netflix contributed the Spring Cloud community")
                .durationMin(120)
                .internalId("beta" + (COUNTER++))
                .createdAt(LocalDate.now())
                .webinarType(WebinarType.ZOOM)
                .build();

        WebinarInformation webinarInformation4 = WebinarInformation.builder()
                .id(UUID.randomUUID().toString())
                .hostCity("Haifa")
                .hostName("John Bryce")
                .isFree(true)
                .webinar(webinar2)
                .description("We love Spring Cloud")
                .durationMin(45)
                .internalId("beta" + (COUNTER++))
                .createdAt(LocalDate.now().plusDays(7))
                .webinarType(WebinarType.ZOOM)
                .build();

        WebinarInformation webinarInformation5 = WebinarInformation.builder()
                .id(UUID.randomUUID().toString())
                .hostCity("Jerusalem")
                .hostName("John Bryce")
                .isFree(true)
                .webinar(webinar3)
                .description("TypeScript Most New Features.")
                .durationMin(60)
                .internalId("beta" + (COUNTER++))
                .createdAt(LocalDate.now())
                .webinarType(WebinarType.ZOOM)
                .build();

        WebinarInformation webinarInformation6 = WebinarInformation.builder()
                .id(UUID.randomUUID().toString())
                .hostCity("Jerusalem")
                .hostName("John Bryce")
                .isFree(true)
                .webinar(webinar3)
                .description("TS everywhere")
                .durationMin(60)
                .internalId("beta" + (COUNTER++))
                .createdAt(LocalDate.now().plusDays(7))
                .webinarType(WebinarType.ZOOM)
                .build();


        List<Webinar> webinars = Arrays.asList(webinar1, webinar2, webinar3);
        for (Webinar webinar : webinars) {
            System.out.println(webinar.toString());
            mongoTemplate.insert(webinar);
        }

        List<WebinarInformation> webinarInformations =
                Arrays.asList(webinarInformation1, webinarInformation2, webinarInformation3,
                        webinarInformation4, webinarInformation5, webinarInformation6);


        for (WebinarInformation webinarInformation:webinarInformations) {
            System.out.println(webinarInformation);
            mongoTemplate.insert(webinarInformation);
        }

        System.out.println("---------------------------------------------------------");

        // Delete Single
        mongoTemplate.remove(webinar1);

        // Delete Many
        Query jerusalemWebinars = Query.query(
                Criteria.where("city").is("Jerusalem")
        );
        mongoTemplate.findAllAndRemove(jerusalemWebinars,WebinarInformation.class);

        //Delete All
        mongoTemplate.dropCollection(WebinarInformation.class);


    }

}
