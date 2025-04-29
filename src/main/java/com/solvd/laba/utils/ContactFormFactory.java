package com.solvd.laba.utils;

import java.io.File;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.laba.components.HeaderComponent;
import com.solvd.laba.config.TestDataReader;
import com.solvd.laba.models.ContactForm;

public class ContactFormFactory {
    private static final Logger logger = LoggerFactory.getLogger(ContactFormFactory.class);
    private static final TestDataReader testData = new TestDataReader();

    public static ContactForm buildContactForm() throws Exception {
        logger.info("Build Contact Form and fill it");
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
