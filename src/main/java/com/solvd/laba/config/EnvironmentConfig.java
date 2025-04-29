package com.solvd.laba.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentConfig {
    private Properties properties;

    public EnvironmentConfig () {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
