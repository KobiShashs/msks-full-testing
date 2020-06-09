package com.shasha.msksfulltesting.bootstrap;

import com.shasha.msksfulltesting.domain.*;
import com.shasha.msksfulltesting.service.WebinarInformationService;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.lock.PessimisticEntityLockException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by kobis on 05 Jun, 2020
 */
@RequiredArgsConstructor
@Component
@Order(1)
public class InitDB implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;
    private static int COUNTER = 0;

    @Override
    public void run(String... args) throws Exception {

        mongoTemplate.dropCollection(Webinar.class);
        mongoTemplate.dropCollection(WebinarInformation.class);
        mongoTemplate.dropCollection(Profile.class);

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

        Profile profile1 = Profile.builder()
                .id(UUID.randomUUID().toString())
                .name("Moshe")
                .title("Java Developer")
                .aboutMe("I am a father")
                .build();

        Profile profile2 = Profile.builder()
                .id(UUID.randomUUID().toString())
                .name("Yakov")
                .title("JS Developer")
                .aboutMe("I am a husband")
                .build();

        Profile profile3 = Profile.builder()
                .id(UUID.randomUUID().toString())
                .name("Dana")
                .title("SW Developer")
                .aboutMe("I am a mother")
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

        List<Profile> profiles = Arrays.asList(profile1,profile2,profile3);
        for (Profile profile:profiles){
            System.out.println(profile);
            mongoTemplate.insert(profile);
        }
    }
}
