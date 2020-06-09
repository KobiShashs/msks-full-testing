package com.shasha.msksfulltesting.service;

import com.shasha.msksfulltesting.domain.WebinarInformation;
import com.shasha.msksfulltesting.domain.WebinarType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kobis on 04 Jun, 2020
 */
@RequiredArgsConstructor
@Service
public class WebinarInformationServiceImpl implements WebinarInformationService {

    private final MongoTemplate mongoTemplate;


    @Override
    public List<WebinarInformation> findAll(String field, int pageNumber, int pageSize) {
        Query allPagedAndOrdered = new Query()
                .with(Sort.by(Sort.Direction.ASC, field))
                .with(PageRequest.of(pageNumber, pageSize));
        return this.mongoTemplate.find(allPagedAndOrdered, WebinarInformation.class);
    }

    @Override
    public WebinarInformation findSingleById(String id) {
        return this.mongoTemplate.findById(id, WebinarInformation.class);
    }

    @Override
    public long countZoom() {
        Query zoom = Query.query(Criteria.where("webinarType")
                .is(WebinarType.ZOOM));
        return this.mongoTemplate.count(zoom, WebinarInformation.class);
    }

    @Override
    public List<WebinarInformation> findByCity(String city) {
        Query byCity = new Query()
                .addCriteria(Criteria.where("city").is(city));
        return this.mongoTemplate.find(byCity, WebinarInformation.class);
    }

    @Override
    public List<WebinarInformation> findByDurationBetween(int minMinutes, int maxMinutes) {
        Query byDurationBetween = Query
                .query(Criteria.where("durationMin")
                        .gte(minMinutes)
                        .lte(maxMinutes))
                .with(Sort.by(Sort.Direction.DESC, "durationMin"));
        return this.mongoTemplate.find(byDurationBetween, WebinarInformation.class);
    }

    @Override
    public List<WebinarInformation> findFreeByType(WebinarType webinarType) {
        Query byWebinarType = Query.query(Criteria
                .where("webinarType").is(webinarType));

        return this.mongoTemplate.find(byWebinarType, WebinarInformation.class);
    }

    @Override
    public List<WebinarInformation> findByWebinarSubject(String webinarSubject) {
        Query byWebinar = Query.query(Criteria.where("webinar.subject").is(webinarSubject));
        return this.mongoTemplate.find(byWebinar, WebinarInformation.class);
    }

    @Override
    public List<WebinarInformation> findByFreeText(String text) {
        TextCriteria textCriteria = TextCriteria
                .forDefaultLanguage()
                .matching(text);

        Query byFreeText = TextQuery.queryText(textCriteria)
                .sortByScore()
                .with(PageRequest.of(0,3));

        return this.mongoTemplate.find(byFreeText,WebinarInformation.class);
    }
}
