package com.shasha.msksfulltesting.converters;

import com.shasha.msksfulltesting.domain.Address;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by kobis on 05 Jun, 2020
 */
public class AddressReadConverter implements Converter<String, Address> {

    @Override
    public Address convert(String s) {
        if(s==null){
            return null;
        }
        String[] parts = s.split(",");
        return new Address(parts[0],parts[1]);
    }
}
