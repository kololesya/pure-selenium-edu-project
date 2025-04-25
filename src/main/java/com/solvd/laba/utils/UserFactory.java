package com.solvd.laba.utils;

import java.io.File;

import com.solvd.laba.config.TestDataConfig;
import com.solvd.laba.models.User;

public class UserFactory {

    private static final TestDataConfig testData = new TestDataConfig();

    public static User buildUserForRegistration() {
        return new User.UserBuilder()
                .setName(testData.getProperty("name"))
                .setEmail(generateUniqueEmail())
                .setPassword(testData.getProperty("password"))
                .setBirthDay(testData.getProperty("birth_day"))
                .setBirthMonth(testData.getProperty("birth_month"))
                .setBirthYear(testData.getProperty("birth_year"))
                .setLastName(testData.getProperty("last_name"))
                .setCompany(testData.getProperty("company"))
                .setAddress1(testData.getProperty("address1"))
                .setAddress2(testData.getProperty("address2"))
                .setCountry(testData.getProperty("country"))
                .setState(testData.getProperty("state"))
                .setCity(testData.getProperty("city"))
                .setZipcode(testData.getProperty("zipcode"))
                .setMobile(testData.getProperty("mobile"))
                .build();
    }

    public static User buildUserForLogin() {
        return new User.UserBuilder()
                .setName(testData.getProperty("name"))
                .setEmail(testData.getProperty("email"))
                .setPassword(testData.getProperty("password"))
                .build();
    }


    public static String getMessageSubject() {
        return testData.getProperty("contact_subject");
    }

    public static String getMessage() {
        return testData.getProperty("contact_message");
    }

    public static String getFilePath() {
        String fileName = testData.getProperty("contact_file_path");
        File file = new File("src/test/resources/" + fileName);
        return file.getAbsolutePath();
    }

    private static String generateUniqueEmail() {
        return "test" + System.currentTimeMillis() + "@gmail.com";
    }
}
