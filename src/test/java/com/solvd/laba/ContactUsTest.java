package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.models.ContactForm;
import com.solvd.laba.pages.ContactUsPage;
import com.solvd.laba.utils.ContactFormFactory;

public class ContactUsTest extends BaseTest {

    @Test
    public void testContactWithUs() throws Exception {
        openHomePage();
        logger.info("Navigate to Contact Us Page");
        ContactUsPage contactUsPage = homePage.header().clickContactUs();
        Assert.assertTrue(contactUsPage.isOpened(), "Contact Us page should be opened");
        logger.info("Build Contact Form and fill it");
        ContactForm contactForm = ContactFormFactory.buildContactForm();
        contactUsPage.fillContactForm(contactForm);
        logger.info("Submit the form");
        contactUsPage.submitForm();
        logger.info("Verify success message");
        Assert.assertTrue(contactUsPage.isSuccessMessageDisplayed(), "Success message should be displayed");
        logger.info("Return to Home Page");
        homePage = contactUsPage.clickHomeButton();
        Assert.assertTrue(homePage.isOpened(), "Home page should be opened after returning from Contact Us");
    }
}
