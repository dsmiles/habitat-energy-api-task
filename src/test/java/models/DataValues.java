package models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataValues {

    @JsonProperty("dnoRegion")
    private String dnoRegion;

    @JsonProperty("voltageLevel")
    private String voltageLevel;

    @JsonProperty("data")
    private List<Datum> dataList = null;
}