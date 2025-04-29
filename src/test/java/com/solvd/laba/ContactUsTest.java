package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.models.ContactForm;
import com.solvd.laba.pages.ContactUsPage;
import com.solvd.laba.pages.HomePage;
import com.solvd.laba.utils.ContactFormFactory;
import com.solvd.laba.projectConstants.SuccessMessages;

public class ContactUsTest extends BaseTest {

    @Test
    public void testContactWithUs() throws Exception {
        HomePage homePage = openHomePage();
        ContactUsPage contactUsPage = homePage.getHeaderMenuComponent().clickContactUs();
        Assert.assertTrue(contactUsPage.isPageOpened(), "Contact Us page should be opened");
        ContactForm contactForm = ContactFormFactory.buildContactForm();
        contactUsPage.fillContactForm(contactForm);
        contactUsPage.submitForm();
        Assert.assertTrue(
                contactUsPage.isMessageTextCorrect(SuccessMessages.CONTACT_FORM_SUBMITTED.getText()),
                "Success message should be displayed"
        );
        homePage = contactUsPage.clickHomeButton();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be opened after returning from Contact Us");
    }
}
