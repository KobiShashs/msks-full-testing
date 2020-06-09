package com.shasha.msksfulltesting.web.model;

import com.shasha.msksfulltesting.domain.Presentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by kobis on 07 Jun, 2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebinarDto {

    @Null
    private String id;

    @NotNull
    private String code;

    @NotNull
    private String subject;

    @Positive
    @NotNull
    private int seats;

    @NotNull
    private Presentation presentation;

    @NotNull
    private boolean isClosed;
}
