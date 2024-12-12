package com.nekosuki.multieditor.process.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.process.config.index.CodeArea;
import com.nekosuki.multieditor.process.config.index.Directory;
import com.nekosuki.multieditor.process.config.index.Root;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

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

    @JsonIgnore
    private final static Path filePath = Paths.get("./AppConfig.json");

    /**
     * configファイルへ書き込み
     */
    @JsonIgnore
    public void storeConfig() {
        if (Files.exists(filePath)) {
            try{
                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                mapper.writeValue(filePath.toFile(), this);
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
     * コンフィグを読み込み
     * @return AppConfig
     */
    @JsonIgnore
    public static AppConfig loadAppConfig() {
        ObjectMapper mapper = new ObjectMapper();
        AppConfig config = null;
        createConfigJson();
        try {
            config = mapper.readValue(Objects.requireNonNull(filePath).toFile(), AppConfig.class);
        } catch (IOException e) {
            System.out.println("Failed to load ConfigFile json");
            throw new RuntimeException(e.getMessage());
        }
        return config;
    }

    /**
     * コンフィグのテンプレ文字列を返すやつ
     * @return String for config template
     */
    @JsonIgnore
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
     * コンフィグファイルが無ければ作成
     */
    @JsonIgnore
    private static void createConfigJson() {
        if (!Files.exists(filePath)) { // configFile無し
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
