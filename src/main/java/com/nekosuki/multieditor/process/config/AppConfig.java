package com.nekosuki.multieditor.process.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nekosuki.multieditor.process.config.index.CodeArea;
import com.nekosuki.multieditor.process.config.index.Directory;
import com.nekosuki.multieditor.process.config.index.Root;
import lombok.Data;

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
