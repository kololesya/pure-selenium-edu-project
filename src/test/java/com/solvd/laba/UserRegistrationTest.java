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
        openHomePage();
        logger.info("Go to Login Page");
        LoginPage loginPage = homePage.header().clickSignupLoginButton();
        Assert.assertTrue(loginPage.isOpened(), "Login page should be opened");
        logger.info("Enter user name and email");
        SignupPage signupPage = loginPage.signUp(signUpUser.getName(), signUpUser.getEmail());
        Assert.assertTrue(signupPage.isOpened(), "Signup page should be opened");
        logger.info("Fill in account information");
        signupPage.fillRegistrationDetails(signUpUser.getPassword(), signUpUser.getBirthDay(), signUpUser.getBirthMonth(), signUpUser.getBirthYear());
        logger.info("Fill in address information");
        signupPage.fillAddressDetails(
                signUpUser.getName(),
                signUpUser.getLastName(),
                signUpUser.getCompany(),
                signUpUser.getAddress1(),
                signUpUser.getAddress2(),
                signUpUser.getCountry(),
                signUpUser.getState(),
                signUpUser.getCity(),
                signUpUser.getZipcode(),
                signUpUser.getMobile()
        );
        logger.info("Click Create Account button");
        AccountCreatedPage accountCreatedPage = signupPage.submitRegistrationForm();
        Assert.assertTrue(accountCreatedPage.isOpened(), "'ACCOUNT CREATED!' message should be visible");
        logger.info("Go to Home Page");
        homePage = accountCreatedPage.clickContinue();
        Assert.assertTrue(homePage.isOpened(), "Home page should be visible after registration");
    }

    @Test
    public void testUserRegistrationWithAlreadyExistEmail() throws Exception {
        User loginUser = UserFactory.buildUserForLogin();
        openHomePage();
        logger.info("Go to Login Page");
        LoginPage loginPage = homePage.header().clickSignupLoginButton();
        Assert.assertTrue(loginPage.isOpened(), "Login page should be opened");
        logger.info("Entering name and email for registration");
        loginPage.signUp(loginUser.getName(), loginUser.getEmail());
        Assert.assertTrue(loginPage.isSignupErrorVisible(),
                "The message 'Email Address already exist!' is visible");
    }
}
