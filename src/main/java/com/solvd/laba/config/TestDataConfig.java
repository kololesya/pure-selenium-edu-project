package com.solvd.laba.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataConfig {
    private Properties properties;

    public TestDataConfig() {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/test/resources/testdata.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
