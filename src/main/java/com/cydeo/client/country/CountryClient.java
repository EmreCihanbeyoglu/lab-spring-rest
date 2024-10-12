package com.cydeo.client.country;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "https://restcountries.com/v3.1", name = "COUNRTY-CLIENT")
public interface CountryClient {

    @GetMapping("/name/{countryName}")
    List<Object> getCountryByName(@PathVariable("countryName") String countryName);

}
