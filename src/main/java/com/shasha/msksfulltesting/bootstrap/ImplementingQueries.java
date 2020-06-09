package com.shasha.msksfulltesting.bootstrap;

import com.shasha.msksfulltesting.domain.Person;
import com.shasha.msksfulltesting.domain.Profile;
import com.shasha.msksfulltesting.domain.Webinar;
import com.shasha.msksfulltesting.domain.WebinarInformation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kobis on 02 Jun, 2020
 */
//@Component
public class ImplementingQueries implements CommandLineRunner {


    private final MongoTemplate mongoTemplate;

    public ImplementingQueries(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {


        Query byAge = Query
                .query(Criteria.where("age").gt(18))
                .with(Sort.by(Sort.Direction.DESC,"age"))
                .with(PageRequest.of(1,10));//1st page, 10 Documents

        // Find All Documents
        List<Person> people = this.mongoTemplate.findAll(Person.class);
        List<Person> people2 = this.mongoTemplate.find(byAge,Person.class);
        Person p = this.mongoTemplate.findOne(byAge,Person.class);
        long count = this.mongoTemplate.count(byAge,Person.class);

        List<Webinar> result = mongoTemplate.findAll(Webinar.class);
        // or
        Query allWebinars = new Query();
        List<Webinar> result2 = mongoTemplate.find(allWebinars,Webinar.class);

        // Find Single Document
        Webinar w = mongoTemplate.findById(123,Webinar.class);
        // or
        Query byCode = Query.query(
                Criteria.where("code").is("acbd1234")
        );
        Webinar w2 = mongoTemplate.findOne(byCode,Webinar.class);

        // Filter with Multiple Operators
        Query numOfSeatsBetween = Query.query(Criteria.where("seats")
        .gt(15).lt(100)
        );
        List<Webinar> result3 = mongoTemplate.find(numOfSeatsBetween,Webinar.class);

        // Multiple Criteria: Or
        Query techWebinarOr200 = Query.query(new Criteria()
        .orOperator(Criteria.where("subject").is("tech"),
                Criteria.where("seats").gte(200))
        );

        List<Webinar> result4 = mongoTemplate.find(techWebinarOr200,Webinar.class);

        // Multiple Criteria: And
        Query techWebinarAnd200 = Query.query(new Criteria()
                .andOperator(Criteria.where("subject").is("tech"),
                        Criteria.where("seats").gte(200))
        );

        List<Webinar> result5 = mongoTemplate.find(techWebinarAnd200,Webinar.class);


        // Filter Subdocuments
        Query presentationNeedsToUpload = Query.query(
          Criteria.where("presentation.isReady").is(false)
        );

        List<Webinar> result6 = mongoTemplate.find(presentationNeedsToUpload,Webinar.class);

        // Sorting The results
        Query moreThan100Seats = Query
        .query(Criteria.where("seats").gt(100))
                .with(Sort.by(Sort.Direction.ASC,"seats"));

        List<Webinar> result7 = mongoTemplate.find(moreThan100Seats,Webinar.class);

        // Paging the results
        Query moreThan150Seats = Query
                .query(Criteria.where("seats").gt(150))
                .with(Sort.by(Sort.Direction.ASC,"seats"))
                .with(PageRequest.of(1,20));

        List<Webinar> result8 = mongoTemplate.find(moreThan150Seats,Webinar.class);

//        TextCriteria textCriteria = TextCriteria
//                .forDefaultLanguage()
//                .matching("Developer")
//
//        Query byFreeText = TextQuery.queryText(textCriteria)
//                .sortByScore() // internal score computed by Mongo
//                .with(PageRequest.of(0,3));
//
//        List<Profile> result9 = mongoTemplate.find(byFreeText, Profile.class);

        System.out.println("------------------------------------------------------");
        System.out.println();

    }
}
