package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.pages.*;
import com.solvd.laba.utils.UserFactory;
import com.solvd.laba.models.User;

public class UserRegistrationTest extends BaseTest {

    @Test
    public void testUserRegistration() throws Exception {
        User signUpUser = UserFactory.buildUserForRegistration();
        HomePage homePage = openHomePage();
        logger.info("Go to Login Page");
        LoginPage loginPage = homePage.getHeaderMenuComponent().clickSignupLoginButton();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page should be opened");
        logger.info("Enter user name and email");
        SignupPage signupPage = loginPage.signUp(signUpUser.getName(), signUpUser.getEmail());
        Assert.assertTrue(signupPage.isPageOpened(), "Signup page should be opened");
        logger.info("Fill in signup form");
        signupPage.completeSignUpForm(signUpUser);
        logger.info("Submit registration");
        AccountCreatedPage accountCreatedPage = signupPage.submitRegistrationForm();
        Assert.assertTrue(accountCreatedPage.isPageOpened(), "'ACCOUNT CREATED!' message should be visible");
        logger.info("Go to Home Page");
        homePage = accountCreatedPage.clickContinue();
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be visible after registration");
    }

    @Test
    public void testUserRegistrationWithAlreadyExistEmail() throws Exception {
        User loginUser = UserFactory.buildUserForLogin();
        String textErrorMessageForLogin = "Email Address already exist!";
        HomePage homePage = openHomePage();
        logger.info("Go to Login Page");
        LoginPage loginPage = homePage.getHeaderMenuComponent().clickSignupLoginButton();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page should be opened");
        logger.info("Entering name and email for registration");
        loginPage.signUp(loginUser.getName(), loginUser.getEmail());
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(textErrorMessageForLogin),
                "The error message should be displayed for invalid registration.");
    }
}
