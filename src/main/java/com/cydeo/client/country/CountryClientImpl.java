package com.cydeo.client.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CountryClientImpl{

    private final CountryClient countryClient;

    @Autowired
    public CountryClientImpl(CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    public String retrieveFlagByCountryName(String countryName) {
        List<Object> countryClientResponse = countryClient.getCountryByName(countryName);

        if(countryClientResponse.isEmpty()) {
            return null;
        }

        Map<String, Object> responseItem = (Map<String, Object>) countryClientResponse.get(0);
        Map<String, Object> flagsMapObject =  (Map<String, Object>)  responseItem.get("flags");
        return (String) flagsMapObject.get("png");
    }


}
