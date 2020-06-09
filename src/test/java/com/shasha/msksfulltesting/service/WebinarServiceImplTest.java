package com.shasha.msksfulltesting.service;

import com.shasha.msksfulltesting.domain.Presentation;
import com.shasha.msksfulltesting.domain.Webinar;
import com.shasha.msksfulltesting.repo.WebinarRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by kobis on 07 Jun, 2020
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WebinarServiceImplTest {

    @Autowired
    private WebinarService webinarService;

    @MockBean
    private WebinarRepository webinarRepository;

    private List<Webinar> webinarList;
    private List<Webinar> webinarListBtw15To30;
    private List<Webinar> webinarListsLte30;
    private static int COUNTER = 0;

    @Before
    public void setUp() throws Exception {
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
        webinarList = new ArrayList<>();
        webinarList.addAll(Arrays.asList(webinar1,webinar2,webinar3,webinar4,webinar5));

        webinarListBtw15To30 = new ArrayList<>();
        webinarListsLte30 = new ArrayList<>();
        for (Webinar webinar:webinarList) {
            if(webinar.getSeats()<=30){
                webinarListsLte30.add(webinar);
            }
            if(15<=webinar.getSeats() && webinar.getSeats()<=30){
                webinarListBtw15To30.add(webinar);
            }

        }

    }
    @Ignore
    @Test
    public void findBySeatsBetweenWithIllegalValuesShouldNotPass(){
        //Not relevant due to Spring Validation usage :)
    }
    @Test
    public void findBySeatsBetween() {

        when(webinarRepository.findBySeatsBetween(15,30)).thenReturn(webinarListBtw15To30);
        assertEquals(3, webinarService.findBySeatsBetween(15,30).size());
    }

    @Test
    public void findSmallMeetups() {
        when(webinarRepository.findSmallMeetups()).thenReturn(webinarListsLte30);
        assertEquals(4,webinarService.findSmallMeetups().size());
    }

    @Ignore
    @Test
    public void getWebinarByID() {
        //Spring Framework already checked the CRUD...
        //If you want to test a service... check only custom query based functionality
    }

    @Ignore
    @Test
    public void saveNewWebinar() {
        //Spring Framework already checked the CRUD...
        //If you want to test a service... check only custom query based functionality
    }

    @Ignore
    @Test
    public void updateWebinar() {
        //Spring Framework already checked the CRUD...
        //If you want to test a service... check only custom query based functionality
    }

    @Ignore
    @Test
    public void deleteById() {
        //Spring Framework already checked the CRUD...
        //If you want to test a service... check only custom query based functionality
    }
}