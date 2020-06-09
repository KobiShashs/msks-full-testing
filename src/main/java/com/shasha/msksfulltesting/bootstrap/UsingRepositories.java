package com.shasha.msksfulltesting.bootstrap;

import com.shasha.msksfulltesting.domain.Presentation;
import com.shasha.msksfulltesting.domain.Webinar;
import com.shasha.msksfulltesting.repo.WebinarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.UUID;

/**
 * Created by kobis on 06 Jun, 2020
 */
@RequiredArgsConstructor
//@Component
@Order(7)

public class UsingRepositories implements CommandLineRunner {

    private final WebinarRepository webinarRepository;
    private static int COUNTER = 0;

    @Override
    public void run(String... args) throws Exception {
        // Find all
        List<Webinar> webinars = webinarRepository.findAll();

        // Delete all
        webinarRepository.deleteAll();

        // Insert
        webinarRepository.insert(Webinar.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(25)
                .subject("Spring Data MongoDB")
                .presentation(Presentation.builder().isReady(true).build())
                .build());


    }
}
