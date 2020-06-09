package com.shasha.msksfulltesting.service;

import com.shasha.msksfulltesting.domain.Webinar;
import com.shasha.msksfulltesting.repo.WebinarRepository;
import com.shasha.msksfulltesting.web.exception.NotFoundException;
import com.shasha.msksfulltesting.web.mapper.WebinarMapper;
import com.shasha.msksfulltesting.web.model.WebinarDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kobis on 07 Jun, 2020
 */
@RequiredArgsConstructor
@Service
public class WebinarServiceImpl implements WebinarService {

    private final WebinarRepository webinarRepository;
    private final WebinarMapper webinarMapper;
    @Override
    public List<WebinarDto> findBySeatsBetween(int min, int max) {

        List<Webinar> resultsWebinar =  webinarRepository.findBySeatsBetween(min,max);
        List<WebinarDto> resultsWebinarDto = new ArrayList<>();
        for (Webinar webinar:resultsWebinar) {
            resultsWebinarDto.add(webinarMapper.webinarToWebinarDto(webinar));
        }
        return resultsWebinarDto;
    }

    @Override
    public List<WebinarDto> findSmallMeetups() {

        List<Webinar> resultsWebinar =  webinarRepository.findSmallMeetups();
        List<WebinarDto> resultsWebinarDto = new ArrayList<>();
        for (Webinar webinar:resultsWebinar) {
            resultsWebinarDto.add(webinarMapper.webinarToWebinarDto(webinar));
        }
        return resultsWebinarDto;
    }

    @Override
    public WebinarDto getWebinarByID(String webinarID) {
        return webinarMapper.webinarToWebinarDto(webinarRepository.findById(webinarID).orElseThrow(NotFoundException::new));

    }

    @Override
    public WebinarDto saveNewWebinar(WebinarDto webinarDto) {
        return webinarMapper.webinarToWebinarDto(webinarRepository.save(webinarMapper.webinarDtoToWebinar(webinarDto)));
    }

    @Override
    public WebinarDto updateWebinar(String webinarID, WebinarDto webinarDto) {
        Webinar webinar = webinarRepository.findById(webinarID).orElseThrow(NotFoundException::new);
        webinar.setSubject(webinarDto.getSubject());
        webinar.setSeats(webinarDto.getSeats());
        webinar.setPresentation(webinarDto.getPresentation());
        webinar.setClosed(webinarDto.isClosed());
        return webinarMapper.webinarToWebinarDto(webinar);
    }

    @Override
    public void deleteById(String webinarID) {
        Webinar webinar = webinarRepository.findById(webinarID).orElseThrow(NotFoundException::new);
        webinarRepository.deleteById(webinarID);
    }
}
