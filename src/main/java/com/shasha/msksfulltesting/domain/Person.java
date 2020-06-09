package com.shasha.msksfulltesting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kobis on 02 Jun, 2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Person {

    @Id private String id;
    private String name;
    private int age;
    private Address address;

}

