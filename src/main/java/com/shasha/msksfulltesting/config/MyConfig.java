package com.shasha.msksfulltesting.config;

import com.shasha.msksfulltesting.converters.AddressReadConverter;
import com.shasha.msksfulltesting.converters.AddressWriteConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kobis on 05 Jun, 2020
 */
@Configuration
public class MyConfig {
    @Bean
    public MongoCustomConversions mongoCustomConversions(){
        List<Converter<?,?>> converters = new ArrayList<>();
        converters.add(new AddressReadConverter());
        converters.add(new AddressWriteConverter());
        return new MongoCustomConversions(converters);
    }
}
