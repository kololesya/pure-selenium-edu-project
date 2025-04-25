package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.pages.*;
import com.solvd.laba.utils.UserFactory;
import com.solvd.laba.models.User;

public class UserRegistrationTest extends BaseTest {
    private User signUpUser = UserFactory.buildUserForRegistration();


    @Test
    public void testUserRegistration() throws Exception {
        logger.info("Step 1: Open Home Page");
        openHomePage();
        logger.info("Step 2: Go to Login Page");
        LoginPage loginPage = homePage.header().clickSignupLoginButton();
        Assert.assertTrue(loginPage.isOpened(), "Login page should be opened");
        logger.info("Step 3: Enter user name and email");
        SignupPage signupPage = loginPage.signUp(signUpUser.getName(), signUpUser.getEmail());
        Assert.assertTrue(signupPage.isOpened(), "Signup page should be opened");
        logger.info("Step 4: Fill in account information");
        signupPage.fillAccountInfo(signUpUser.getPassword(), signUpUser.getBirthDay(), signUpUser.getBirthMonth(), signUpUser.getBirthYear());
        logger.info("Step 5: Fill in address information");
        signupPage.fillAddressInfo(
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
        logger.info("Step 6: Click Create Account");
        AccountCreatedPage accountCreatedPage = signupPage.clickCreateAccount();
        Assert.assertTrue(accountCreatedPage.isOpened(), "'ACCOUNT CREATED!' message should be visible");
        logger.info("Step 7: Go to Home Page");
        homePage = accountCreatedPage.clickContinue();
        Assert.assertTrue(homePage.isOpened(), "Home page should be visible after registration");
    }

    @Test
    public void testUserRegistrationWithAlreadyExistEmail() throws Exception {
        User loginUser = UserFactory.buildUserForLogin();
        openHomePage();
        logger.info("Step 1: Navigating Login Page");
        LoginPage loginPage = homePage.header().clickSignupLoginButton();
        Assert.assertTrue(loginPage.isOpened(), "Login page should be opened");
        logger.info("Step 2: Entering name and email for registration");
        loginPage.signUp(loginUser.getName(), loginUser.getEmail());
        Assert.assertTrue(loginPage.isSignupErrorVisible(),
                "The message 'Email Address already exist!' is visible");
    }
}
