package com.shasha.msksfulltesting.service;

import com.shasha.msksfulltesting.domain.WebinarInformation;
import com.shasha.msksfulltesting.domain.WebinarType;

import java.util.List;

/**
 * Created by kobis on 04 Jun, 2020
 */
public interface WebinarInformationService {

    List<WebinarInformation> findAll(String field,int pageNumber,int pageSize);
    WebinarInformation findSingleById(String id);
    long countZoom();
    List<WebinarInformation> findByCity(String city);
    List<WebinarInformation> findByDurationBetween(int minMinutes, int maxMinutes);
    List<WebinarInformation> findFreeByType(WebinarType webinarType);
    List<WebinarInformation> findByWebinarSubject(String webinarSubject);
    List<WebinarInformation> findByFreeText(String text);




}
