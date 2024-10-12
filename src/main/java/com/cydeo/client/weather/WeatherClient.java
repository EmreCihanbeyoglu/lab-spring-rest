package com.cydeo.client.weather;

import com.cydeo.client.weather.model.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://api.weatherstack.com", name = "WEATHER-CLIENT")
public interface WeatherClient {

    @GetMapping("/current")
    WeatherResponse getCurrentWeather(@RequestParam("access_key") String accessKey,
                                      @RequestParam("query") String query);

}
