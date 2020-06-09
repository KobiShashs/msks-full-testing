package com.shasha.msksfulltesting.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shasha.msksfulltesting.domain.Presentation;
import com.shasha.msksfulltesting.service.WebinarService;
import com.shasha.msksfulltesting.web.model.WebinarDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kobis on 07 Jun, 2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class WebinarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WebinarService webinarService;

    private static final String URI = "/api/v1/webinar/";

    private WebinarDto webinarDto;

    private List<WebinarDto> webinarList;
    private List<WebinarDto> webinarListBtw15To30;
    private List<WebinarDto> webinarListsLte30;

    private static int COUNTER = 0;

    @Before
    public void setUpSingleDto() throws Exception {
        webinarDto = WebinarDto.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(25)
                .subject("Spring Data MongoDB")
                .presentation(Presentation.builder().isReady(true).build())
                .build();
    }


    @Before
    public void setUpList() throws Exception {
        WebinarDto webinar1 = WebinarDto.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(25)
                .subject("Spring Data MongoDB")
                .presentation(Presentation.builder().isReady(true).build())
                .build();

        WebinarDto webinar2 = WebinarDto.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(15)
                .subject("Spring Cloud")
                .presentation(Presentation.builder().isReady(true).build())
                .build();

        WebinarDto webinar3 = WebinarDto.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(20)
                .subject("WTF is TS?!")
                .presentation(Presentation.builder().isReady(true).build())
                .build();

        WebinarDto webinar4 = WebinarDto.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(60)
                .subject("Angular 9 New Features")
                .presentation(Presentation.builder().isReady(true).build())
                .build();

        WebinarDto webinar5 = WebinarDto.builder()
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
        for (WebinarDto webinar:webinarList) {
            if(webinar.getSeats()<=30){
                webinarListsLte30.add(webinar);
            }
            if(15<=webinar.getSeats() && webinar.getSeats()<=30){
                webinarListBtw15To30.add(webinar);
            }

        }

    }



    @Test
    public void getItem() throws Exception {

        String webinarDtoJson = objectMapper.writeValueAsString(webinarDto);
        given(webinarService.getWebinarByID(any())).willReturn(webinarDto);

        ResultActions ra = mockMvc.perform(get(URI + UUID.randomUUID().toString()));
        ra.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(webinarDtoJson));

    }

    @Test
    public void addItem() throws Exception {

        WebinarDto myWebinarDto = webinarDto;
        myWebinarDto.setId(null);

        WebinarDto savedDto = WebinarDto.builder()
                .id(UUID.randomUUID().toString())
                .code("alpha" + (COUNTER++))
                .seats(25)
                .subject("Spring Data MongoDB")
                .presentation(Presentation.builder().isReady(true).build())
                .build();

        String webinarDtoJson = objectMapper.writeValueAsString(myWebinarDto);

        given(webinarService.saveNewWebinar(any())).willReturn(savedDto);

        mockMvc.perform(post(URI).contentType(MediaType.APPLICATION_JSON).content(webinarDtoJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void updateItem() throws Exception {
        WebinarDto myWebinarDto = webinarDto;
        myWebinarDto.setId(null);

        String webinarDtoJson = objectMapper.writeValueAsString(webinarDto);

        mockMvc.perform(put(URI + UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON).content(webinarDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteItem() throws Exception {

        String webinarDtoJson = objectMapper.writeValueAsString(webinarDto);
        mockMvc.perform(delete(URI + webinarDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content((byte[]) null))
                .andExpect(status().isNoContent());

    }

    @Test
    public void getWebinarsBySeats() throws Exception{

        String webinarDtoJson = objectMapper.writeValueAsString(webinarListBtw15To30);
        given(webinarService.findBySeatsBetween(15,30)).willReturn(webinarListBtw15To30);

        ResultActions ra = mockMvc.perform(get(URI + "seats/15/30/"));
        ra.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(webinarDtoJson));
    }

    @Test
    public void getSmallMeetups() throws Exception {

        String webinarDtoJson = objectMapper.writeValueAsString(webinarListsLte30);
        given(webinarService.findSmallMeetups()).willReturn(webinarListsLte30);

        ResultActions ra = mockMvc.perform(get(URI + "small-meetups/"));
        ra.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(webinarDtoJson));
    }
}