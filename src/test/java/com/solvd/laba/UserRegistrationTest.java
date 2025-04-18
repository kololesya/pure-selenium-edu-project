package com.solvd.laba;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.pages.*;
import com.solvd.laba.utils.UserFactory;

public class UserRegistrationTest extends BaseTest {

    @Test
    public void testUserCanStartRegistration() {
        Map<String, String> userData = UserFactory.createUserData();

        logger.info("🌐 Step 1: Opening Home Page");
        openHomePage();

        logger.info("➡️ Step 2: Navigating Login Page");
        LoginPage loginPage = homePage.header().clickSignupLogin();
        Assert.assertTrue(loginPage.isOpened(), "Login page should be opened");

        logger.info("📝 Step 3: Entering name and email for registration");
        loginPage.signUp(userData.get("name"), userData.get("email"));

        SignupPage signupPage = new SignupPage(driver);
        Assert.assertTrue(signupPage.isOpened(), "Signup page should be opened");

        logger.info("🧾 Step 4: Filling in account information");
        signupPage.fillAccountInfo(
                userData.get("password"),
                userData.get("birth_day"),
                userData.get("birth_month"),
                userData.get("birth_year")
        );

        logger.info("🏠 Step 5: Filling in address and contact information");
        signupPage.fillAddressInfo(
                userData.get("name"),
                userData.get("last_name"),
                userData.get("company"),
                userData.get("address1"),
                userData.get("address2"),
                userData.get("country"),
                userData.get("state"),
                userData.get("city"),
                userData.get("zipcode"),
                userData.get("mobile")
        );

        signupPage.clickCreateAccount();

        logger.info("✅ Step 6: Verifying that the account was successfully created");
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        Assert.assertTrue(accountCreatedPage.isOpened(), "'ACCOUNT CREATED!' should be visible");

        logger.info("🏁 Step 7: Returning to the Home Page");
        homePage = accountCreatedPage.clickContinue();
        Assert.assertTrue(homePage.isOpened(), "Home page should be visible after registration");
    }
}
