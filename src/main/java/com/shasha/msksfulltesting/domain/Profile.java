package com.shasha.msksfulltesting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * Created by kobis on 04 Jun, 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Profile {
    @Id private String id;
    private String name;
    @TextIndexed private String title;
    @TextIndexed(weight = 2) private String aboutMe;
}
