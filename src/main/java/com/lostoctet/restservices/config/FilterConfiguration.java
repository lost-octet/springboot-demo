package com.lostoctet.restservices.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class FilterConfiguration {

    public ObjectMapper FilterConfiguration1 () {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<String> fields = new HashSet<String>();
        fields.add("userid");
        fields.add("username");
        fields.add("ssn");
        fields.add("email");
        fields.add("orders");
        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(true);
        simpleFilterProvider.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));

        objectMapper.setFilterProvider(simpleFilterProvider);
        return objectMapper;
    }
//
//    public ObjectMapper FilterConfiguration2 (Set<String> fields) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(true);
//        simpleFilterProvider.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
//
//        objectMapper.setFilterProvider(simpleFilterProvider);
//        return objectMapper;
//    }

}