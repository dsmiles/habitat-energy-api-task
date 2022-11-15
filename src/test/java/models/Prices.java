package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Prices {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private DataValues dataValues;
}