package com.shasha.msksfulltesting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by kobis on 05 Jun, 2020
 */
public @Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

class Address{
    private String city;
    private String country;
}
