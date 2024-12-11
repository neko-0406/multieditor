package com.nekosuki.multieditor.process.config.index;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Root implements ConfigIndex {
    @JsonProperty("clickCount")
    private int clickCount;

    @JsonProperty("theme")
    private String theme;
}
