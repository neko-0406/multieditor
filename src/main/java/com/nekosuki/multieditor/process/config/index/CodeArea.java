package com.nekosuki.multieditor.process.config.index;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CodeArea {

    @JsonProperty("textSize")
    private int textSize;
}
