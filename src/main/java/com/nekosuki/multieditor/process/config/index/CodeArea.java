package com.nekosuki.multieditor.process.config.index;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CodeArea implements ConfigIndex{

    @JsonProperty("textSize")
    private int textSize;

    @JsonProperty("theme")
    private String theme;
}
