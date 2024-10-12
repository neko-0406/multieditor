package com.nekosuki.multieditor;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.io.InputStream;

public class AppConfig {
    private final String configFile;
    private final Properties properties;

    public AppConfig() {
        configFile = "./AppConfig.properties";
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
            this.properties.store(out, null);
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
