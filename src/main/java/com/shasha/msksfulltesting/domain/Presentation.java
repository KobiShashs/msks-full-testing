package com.shasha.msksfulltesting.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kobis on 03 Jun, 2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Presentation {
    private boolean isReady;
}
