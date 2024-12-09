package com.nekosuki.multieditor.process.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nekosuki.multieditor.MainApp;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AppConfigManager {

    @Setter
    @Getter
    private AppConfig appConfig;

    private static final Path filePath = Paths.get("./AppConfig.json");

    public AppConfigManager() {
        this.appConfig = loadAppConfig();
    }

    /**
     * コンフィグファイル(JSON)読み込み
     * @return AppConfig
     */
    private AppConfig loadAppConfig() {
        ObjectMapper mapper = new ObjectMapper();
        AppConfig config = null;

        try{
            config = mapper.readValue(filePath.toFile(), AppConfig.class);
        } catch (IOException e) {
            System.out.println("Failed to load ConfigFile json");
        }
        return config;
    }

    /**
     * AppConfig.jsonファイルがあるかチェック
     * @return true or false
     */
    private boolean checkJSONFile() {
        return Files.exists(filePath);
    }

    private String readTempConfig() {
        String config;

        InputStream tempData = Objects.requireNonNull(MainApp.class.getResourceAsStream("tempConfig"))
    }

    private boolean createConfigJson() {
        boolean isSuccess = false;

        try{

        }
    }
}
