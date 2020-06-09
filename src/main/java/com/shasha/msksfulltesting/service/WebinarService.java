package com.shasha.msksfulltesting.service;

import com.shasha.msksfulltesting.web.exception.NotFoundException;
import com.shasha.msksfulltesting.web.model.WebinarDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * Created by kobis on 07 Jun, 2020
 */
public interface WebinarService {

    List<WebinarDto> findBySeatsBetween(@NotNull @Positive int min, @NotNull @Positive int max);
    List<WebinarDto> findSmallMeetups();

    WebinarDto getWebinarByID(String webinarID) throws NotFoundException;

    WebinarDto saveNewWebinar(WebinarDto webinarDto);

    WebinarDto updateWebinar(String webinarID, WebinarDto webinarDto);

    void deleteById(String webinarID);
}
