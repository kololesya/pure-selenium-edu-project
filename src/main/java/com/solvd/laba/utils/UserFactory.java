package com.solvd.laba.utils;

import java.util.HashMap;
import java.util.Map;

import com.solvd.laba.config.TestDataConfig;

public class UserFactory {
    private static final TestDataConfig testData = new TestDataConfig();
    private static Map<String, String> lastCreatedUser;

    public static Map<String, String> createUserData() {
        Map<String, String> user = new HashMap<>();
        user.put("name", testData.getProperty("name"));
        user.put("email", generateUniqueEmail());
        user.put("password", testData.getProperty("password"));
        user.put("birth_day", testData.getProperty("birth_day"));
        user.put("birth_month", testData.getProperty("birth_month"));
        user.put("birth_year", testData.getProperty("birth_year"));
        user.put("last_name", testData.getProperty("last_name"));
        user.put("company", testData.getProperty("company"));
        user.put("address1", testData.getProperty("address1"));
        user.put("address2", testData.getProperty("address2"));
        user.put("country", testData.getProperty("country"));
        user.put("state", testData.getProperty("state"));
        user.put("city", testData.getProperty("city"));
        user.put("zipcode", testData.getProperty("zipcode"));
        user.put("mobile", testData.getProperty("mobile"));

        lastCreatedUser = user;
        return user;
    }

    public static Map<String, String> getLastCreatedUser() {
        return lastCreatedUser;
    }

    private static String generateUniqueEmail() {
        return "test" + System.currentTimeMillis() + "@gmail.com";
    }
}

