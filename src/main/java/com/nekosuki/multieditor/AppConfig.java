package com.nekosuki.multieditor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.io.InputStream;

public class AppConfig {

    public static final String CODE_TEXT_SIZE = "code_text_size";
    public static final String CURRENT_DIR = "current_dir";
    public static final String DISPLAY_THEME = "display_theme";
    public static final String LAST_OPEN_DIR = "last_open_dir";
    public static final String CLICK_COUNT = "click_count";

    private static final String configFile = "./AppConfig.properties";
    private final Properties properties;

    public AppConfig() {
        properties = loadPropertiesFile();
    }

    public String getProperty(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

    public void addProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

    public void replaceProperty(String key, String value) {
        this.properties.replace(key, value);
    }

    public void writeProperties() {
        try(OutputStream out = Files.newOutputStream(Paths.get(configFile))) {
            String comment = """
                             code_text_size: Text size of the code
                             current_dir: Currently open folder
                             display_theme: Current Color Theme
                             last_open_dir: Last opened folder
                             click_count: click count for explorer
                             """;
            this.properties.store(out, comment);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private Properties loadPropertiesFile() {
        Properties properties = new Properties();
        try {
            InputStream in = Files.newInputStream(Paths.get(configFile));
            properties.load(in);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
