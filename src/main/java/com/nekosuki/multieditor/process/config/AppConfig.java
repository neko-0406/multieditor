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

    public static AppConfig loadConfigFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        AppConfig appConfig;
        File file = new File("./AppConfig.json");

        try{
            appConfig = objectMapper.readValue(file, AppConfig.class);
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return appConfig;
    }

    public static boolean StoreAppConfig(AppConfig appConfig) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File("./AppConfig.json");

        try{
            objectMapper.writeValue(file, appConfig);
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return true;
    }
}
