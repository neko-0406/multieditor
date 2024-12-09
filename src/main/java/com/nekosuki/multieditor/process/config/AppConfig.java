package com.nekosuki.multieditor.process.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nekosuki.multieditor.process.config.index.CodeArea;
import com.nekosuki.multieditor.process.config.index.Directory;
import com.nekosuki.multieditor.process.config.index.Root;
import lombok.Data;

import java.io.File;
import java.io.IOException;

@Data
public class AppConfig {
    // field-----------------------
    @JsonProperty("root")
    private Root root;

    @JsonProperty("codeArea")
    private CodeArea codeArea;

    @JsonProperty("dir")
    private Directory directory;
    //-------------------------------
}
