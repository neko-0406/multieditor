package com.nekosuki.multieditor.process.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppConfig {
    @JsonProperty("root")
    private Root root;

    @JsonProperty("codeArea")
    private CodeArea codeArea;

    @JsonProperty("dir")
    private Directory directory;

    public static class Root {}
    public static class CodeArea {}
    public static class Directory {}


}
