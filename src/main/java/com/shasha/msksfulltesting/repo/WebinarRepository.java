package com.shasha.msksfulltesting.repo;

import com.shasha.msksfulltesting.domain.Webinar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by kobis on 06 Jun, 2020
 */
public interface WebinarRepository extends MongoRepository<Webinar,String> {

    // Build query methods for Numeric properties

    List<Webinar> findBySeatsBetween(int min, int max);
    List<Webinar> findBySeatsGreaterThan(int value);
    List<Webinar> findBySeatsGreaterThanEqual(int value);
    List<Webinar> findBySeatsLessThanEqual(int value);

    // Build query methods for String properties

    List<Webinar> findBySubjectLike(String subject);
    List<Webinar> findBySubjectNotNull();
    List<Webinar> findBySubjectNull();

    Optional<Webinar> findBySubject(String subject);
    //Webinar findBySubject(String subject);

    // Build query methods for Boolean properties
    List<Webinar> findByIsClosedTrue();
    List<Webinar> findByIsClosedFalse();

    // Build complex query methods
    List<Webinar> findByIsClosedTrueAndSeatsGreaterThan(int min);

    // Declaring custom Queries
    @Query("{'subject' : ?0}")
    List<Webinar> findByKokoriko(String subject);//method name not relevant

    @Query("{'seats' : {$lte : 30 }}")
    List<Webinar> findSmallMeetups();

    @Query("{'subject' : ?0}")
    List<Webinar> bySubject(String subject);

}
