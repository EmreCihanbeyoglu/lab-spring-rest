package com.cydeo.client.weather;

import com.cydeo.client.weather.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeatherClientImpl {


    private final WeatherClient weatherClient;


    @Value("${integration.weatherClient.accessKey}")
    private String accessKey;


    public WeatherClientImpl(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public WeatherResponse getWeatherByCity(String city) {
        return weatherClient.getCurrentWeather(accessKey, city);
    }

    public Integer retrieveTemperatureByCity(String city) {
        WeatherResponse weatherResponse = getWeatherByCity(city);

        if(weatherResponse == null) {
            return null;
        }

        if(weatherResponse.current == null || weatherResponse.current.temperature == null) {
            return null;
        }

        return weatherResponse.current.temperature;
    }





}
