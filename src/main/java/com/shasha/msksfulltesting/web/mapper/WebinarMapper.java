package com.shasha.msksfulltesting.web.mapper;

import com.shasha.msksfulltesting.domain.Webinar;
import com.shasha.msksfulltesting.web.model.WebinarDto;
import org.mapstruct.Mapper;

/**
 * Created by kobis on 07 Jun, 2020
 */
@Mapper(uses = DateMapper.class)
public interface WebinarMapper {
    WebinarDto webinarToWebinarDto(Webinar webinar);
    Webinar webinarDtoToWebinar(WebinarDto dto);

}
