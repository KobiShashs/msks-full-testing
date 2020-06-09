package com.shasha.msksfulltesting.converters;



import com.shasha.msksfulltesting.domain.Address;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by kobis on 05 Jun, 2020
 */
public class AddressWriteConverter implements Converter<Address,String> {

    @Override
    public String convert(Address address) {
        return address.getCity()+" "+address.getCountry();
    }
}
