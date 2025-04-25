package com.solvd.laba.utils;

import java.io.File;

import com.solvd.laba.config.TestDataConfig;
import com.solvd.laba.models.ContactForm;

public class ContactFormFactory {

    private static final TestDataConfig testData = new TestDataConfig();

    public static ContactForm buildContactForm() throws Exception {
        return new ContactForm.ContactFormBuilder()
                .setName(testData.getProperty("name"))
                .setEmail(EncryptionUtils.decrypt(testData.getProperty("email_encrypted")))
                .setSubject(testData.getProperty("contact_subject"))
                .setMessage(testData.getProperty("contact_message"))
                .setFilePath(getFilePath())
                .build();
    }

    private static String getFilePath() {
        String relativePath = testData.getProperty("file_path");
        File file = new File(relativePath);
        return file.getAbsolutePath();
    }
}
