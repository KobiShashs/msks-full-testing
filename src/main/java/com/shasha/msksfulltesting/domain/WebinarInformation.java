package com.shasha.msksfulltesting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;

/**
 * Created by kobis on 02 Jun, 2020
 */
@Document("webinars")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WebinarInformation {

    @Id
    private String id;

    @Indexed(unique = true)
    private String internalId;

    @TextIndexed
    @Field("host")
    private String hostName;
    @TextIndexed
    @Field("city")
    private String hostCity;
    @TextIndexed(weight = 2)
    private String description;
    private WebinarType webinarType;
    private boolean isFree;
    private int durationMin;
    private Webinar webinar;
    @Transient
    private LocalDate createdAt;



}
