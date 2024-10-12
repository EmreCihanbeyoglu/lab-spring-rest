
package com.cydeo.client.weather.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class WeatherResponse {

    @JsonProperty("request")
    public Request request;

    @JsonProperty("location")
    public Location location;

    @JsonProperty("current")
    public Current current;

}
