package com.nekosuki.multieditor.process.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nekosuki.multieditor.MainApp;
import lombok.Getter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AppConfigManager {

    @Getter
    private static final AppConfig appConfig = loadAppConfig();
    private static final Path filePath = Paths.get("./AppConfig.json");

    /**
     * configファイルに設定書き込み
     */
    public static void storeConfig() {
        if (Files.exists(filePath)) {
            try{
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                mapper.writeValue(filePath.toFile(), appConfig);
            }catch (IOException e) {
                System.out.println("設定ファイルの書き込みが失敗しました。");
                throw new RuntimeException(e.getMessage());
            }
        }
        else {
            System.out.println("configファイルが見つかりません。");
        }
    }

    /**
     * コンフィグファイル(JSON)読み込み
     * @return AppConfig
     */
    private static AppConfig loadAppConfig() {
        ObjectMapper mapper = new ObjectMapper();
        AppConfig config = null;
        createConfigJson();
        try {
            config = mapper.readValue(Objects.requireNonNull(filePath).toFile(), AppConfig.class);
        } catch (IOException e) {
            System.out.println("Failed to load ConfigFile json");
        }
        return config;
    }

    /**
     * AppConfig.jsonファイルがあるかチェック
     * @return true or false
     */
    private static boolean checkJSONFile() {
        return Files.exists(filePath);
    }

    /**
     * コンフィグファイルのテンプレ読み込み
     * @return String
     */
    private static String readTempConfig() {
        String config;
        InputStream tempData = Objects.requireNonNull(MainApp.class.getResourceAsStream("tempConfig.json"));

        try(BufferedReader br = new BufferedReader(new InputStreamReader(tempData))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            config = sb.toString();
        }catch (IOException e) {
            System.out.println("ファイルテンプレの読み込みに失敗しました。");
            throw new RuntimeException(e.getMessage());
        }
        return config;
    }

    /**
     * コンフィグファイルが無ければ作成するやつｆ
     */
    private static void createConfigJson() {
        if (!checkJSONFile()) { // configFile無し
            try {
                String jsonText = readTempConfig();
                Files.writeString(filePath, jsonText);
                System.out.println("コンフィグファイル作成");
            }catch (IOException e) {
                System.out.println("コンフィグファイルの作成に失敗しました。");
                throw new RuntimeException(e.getMessage());
            }
        }
    }

}
