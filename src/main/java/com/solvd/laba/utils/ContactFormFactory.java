package com.solvd.laba.utils;

import java.io.File;

import com.solvd.laba.config.TestDataConfig;
import com.solvd.laba.models.ContactForm;

public class ContactFormFactory {

    private static final TestDataConfig testData = new TestDataConfig();

    public static ContactForm buildContactForm() {
        return new ContactForm.ContactFormBuilder()
                .setName(testData.getProperty("name"))
                .setEmail(testData.getProperty("email"))
                .setSubject(testData.getProperty("contact_subject"))
                .setMessage(testData.getProperty("contact_message"))
                .setFilePath(getFilePath())
                .build();
    }

    private static String getFilePath() {
        String fileName = testData.getProperty("contact_file_path");
        File file = new File("src/test/resources/" + fileName);
        return file.getAbsolutePath();
    }
}
