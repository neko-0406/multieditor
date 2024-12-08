package com.nekosuki.multieditor.process.config.index;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Directory {

    @JsonProperty("current")
    private String currentDir;

    @JsonProperty("last")
    private String lastOpenDir;

    @JsonProperty("history")
    private List<String> history;
}
