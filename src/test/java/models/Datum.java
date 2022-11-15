package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Datum {
    @JsonProperty("Overall")
    public Float overall;

    @JsonProperty("unixTimestamp")
    public Integer unixTimestamp;

    @JsonProperty("Timestamp")
    public String timestamp;
}