package com.shasha.msksfulltesting.bootstrap;

import com.shasha.msksfulltesting.domain.Webinar;
import com.shasha.msksfulltesting.domain.WebinarType;
import com.shasha.msksfulltesting.service.WebinarInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by kobis on 05 Jun, 2020
 */
@RequiredArgsConstructor
//@Component
@Order(3)
public class CheckingTheService implements CommandLineRunner {

    private final WebinarInformationService webinarInformationService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("----------------------Count all Zoom webinars----------------------");
        System.out.println(webinarInformationService.countZoom());
        System.out.println("-------------Find all webinars @ John Bryce Jerusalem--------------");
        System.out.println(webinarInformationService.findByCity("Jerusalem"));
        System.out.println("---------Find all webinars that are 60 to 90 minutes long----------");
        System.out.println(webinarInformationService.findByDurationBetween(60,90));
        System.out.println("--------------Find all FREE webinars in Zoom platform--------------");
        System.out.println(webinarInformationService.findFreeByType(WebinarType.ZOOM));
        System.out.println("--------Find all webinars by Subject : Spring Data MongoDB---------");
        System.out.println(webinarInformationService.findByWebinarSubject("Spring Data MongoDB"));
        System.out.println("----------------Find a single webinar by webinar id----------------");
        System.out.println(webinarInformationService.findSingleById(UUID.randomUUID().toString()));
        System.out.println("---------------------Find all webinars by City---------------------");
        System.out.println(webinarInformationService.findAll("city",0,3));
        System.out.println("-----------------Find all webinars by Description------------------");
        System.out.println(webinarInformationService.findAll("description",0,3));
       // System.out.println(webinarInformationService.findByFreeText("TS"));

    }
}
