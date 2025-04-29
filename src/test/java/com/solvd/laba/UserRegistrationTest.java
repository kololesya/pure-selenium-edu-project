package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.solvd.laba.models.User;
import com.solvd.laba.pages.*;
import com.solvd.laba.projectConstants.ErrorMessages;
import com.solvd.laba.utils.UserFactory;

public class UserRegistrationTest extends BaseTest {

    @DataProvider(name = "registrationData")
    public Object[][] registrationData() throws Exception {
        return new Object[][]{
                { UserFactory.buildUserForRegistration(), true, null },
                { UserFactory.buildUserForLogin(), false, ErrorMessages.EMAIL_ALREADY_EXISTS }
        };
    }

    @Test(dataProvider = "registrationData")
    public void testUserRegistration(User user, boolean isNewUser, ErrorMessages expectedErrorMessage) {
        HomePage homePage = openHomePage();
        logger.info("Go to Login Page");
        LoginPage loginPage = homePage.getHeaderMenuComponent().clickSignupLoginButton();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page should be opened");

        logger.info("Enter user name and email");
        SignupPage signupPage = loginPage.signUp(user.getName(), user.getEmail());

        if (isNewUser) {
            logger.info("Fill in signup form");
            Assert.assertTrue(signupPage.isPageOpened(), "Signup page should be opened");
            signupPage.completeSignUpForm(user);
            logger.info("Submit registration");
            AccountCreatedPage accountCreatedPage = signupPage.submitRegistrationForm();
            Assert.assertTrue(accountCreatedPage.isPageOpened(), "'ACCOUNT CREATED!' message should be visible");

            logger.info("Go to Home Page");
            homePage = accountCreatedPage.clickContinue();
            Assert.assertTrue(homePage.isPageOpened(), "Home page should be visible after registration");
        } else {
            logger.info("Verify error message for existing email");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(expectedErrorMessage.getText()),
                    "The error message should be displayed for invalid registration.");
        }
    }
}
