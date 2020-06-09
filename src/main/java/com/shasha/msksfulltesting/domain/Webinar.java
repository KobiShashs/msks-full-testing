package com.shasha.msksfulltesting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Created by kobis on 02 Jun, 2020
 */
@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Webinar {

    @Id private String id;
    @Indexed(unique = true) private String code;
    private String subject;
    private int seats;
    private Presentation presentation;
    private boolean isClosed;



}

