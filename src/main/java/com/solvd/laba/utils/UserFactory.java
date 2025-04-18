package com.solvd.laba.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.solvd.laba.config.TestDataConfig;

public class UserFactory {
    private static final TestDataConfig testData = new TestDataConfig();

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

        return user;
    }

    public static Map<String, String> createContactFormData() {
        Map<String, String> contactData = new HashMap<>();
        contactData.put("name", testData.getProperty("name"));
        contactData.put("email", testData.getProperty("email"));

        contactData.put("contact_subject", testData.getProperty("contact_subject"));
        contactData.put("contact_message", testData.getProperty("contact_message"));
        contactData.put("contact_file_path", getTestFilePath());

        return contactData;
    }

    private static String getTestFilePath() {
        String fileName = testData.getProperty("contact_file_path");
        File file = new File("src/test/resources/" + fileName);
        return file.getAbsolutePath();
    }

    private static String generateUniqueEmail() {
        return "test" + System.currentTimeMillis() + "@gmail.com";
    }
}

