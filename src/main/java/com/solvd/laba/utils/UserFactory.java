package com.solvd.laba.utils;

import com.solvd.laba.config.TestDataConfig;
import com.solvd.laba.models.User;

public class UserFactory {

    private static final TestDataConfig testData = new TestDataConfig();

    public static User buildUserForRegistration() throws Exception {
        return new User.UserBuilder()
                .setName(testData.getProperty("name"))
                .setEmail(generateUniqueEmail())
                .setPassword(EncryptionUtils.decrypt(testData.getProperty("password_encrypted")))
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

    public static User buildUserForLogin() throws Exception {
        return new User.UserBuilder()
                .setName(testData.getProperty("name"))
                .setEmail(EncryptionUtils.decrypt(testData.getProperty("email_encrypted")))
                .setPassword(EncryptionUtils.decrypt(testData.getProperty("password_encrypted")))
                .build();
    }

    private static String generateUniqueEmail() {
        return "test" + System.currentTimeMillis() + "@gmail.com";
    }
}
