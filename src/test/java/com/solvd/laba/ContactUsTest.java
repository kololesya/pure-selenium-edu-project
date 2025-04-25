package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.models.ContactForm;
import com.solvd.laba.pages.ContactUsPage;
import com.solvd.laba.utils.ContactFormFactory;

public class ContactUsTest extends BaseTest {

    @Test
    public void testContactWithUs() throws Exception {
        logger.info("Step 1: Open Home Page");
        openHomePage();
        logger.info("Step 2: Navigate to Contact Us Page");
        ContactUsPage contactUsPage = homePage.header().clickContactUs();
        Assert.assertTrue(contactUsPage.isOpened(), "Contact Us page should be opened");
        logger.info("Step 3: Build Contact Form and fill it");
        ContactForm contactForm = ContactFormFactory.buildContactForm();
        contactUsPage.fillContactForm(contactForm);
        logger.info("Step 4: Submit the form");
        contactUsPage.submitForm();
        logger.info("Step 5: Verify success message");
        Assert.assertTrue(contactUsPage.isSuccessMessageDisplayed(), "Success message should be displayed");
        logger.info("Step 6: Return to Home Page");
        homePage = contactUsPage.clickHomeButton();
        Assert.assertTrue(homePage.isOpened(), "Home page should be opened after returning from Contact Us");
    }
}
