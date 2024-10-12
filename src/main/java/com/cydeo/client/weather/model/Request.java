
package com.cydeo.client.weather.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Request {

    @JsonProperty("type")
    public String type;
    @JsonProperty("query")
    public String query;
    @JsonProperty("language")
    public String language;
    @JsonProperty("unit")
    public String unit;

}
