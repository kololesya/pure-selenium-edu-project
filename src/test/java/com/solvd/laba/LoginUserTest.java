package com.solvd.laba;

import java.util.Map;

import com.solvd.laba.config.TestDataConfig;
import com.solvd.laba.pages.LoginPage;
import com.solvd.laba.utils.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginUserTest extends BaseTest{

    @Test
    public void testLoginWithCorrectUserCredentials(){
        openHomePage();

        logger.info("➡️ Open Login Page");
        LoginPage loginPage = homePage.header().clickSignupLogin();
        Assert.assertTrue(loginPage.isOpened(), "Login page should be opened");

        logger.info("🔐Login with credentials from testdata.properties");
        loginPage.loginOnSite(email, password);

        logger.info("✅ Verify login was successful");
        Assert.assertTrue(loginPage.header().isUserLoggedIn(testData.getProperty("name")));
        Assert.assertTrue(homePage.isOpened(), "Home page should be opened after login");
    }

    @Test
    public void testLoginWithIncorrectUserCredentials(){
        openHomePage();

        logger.info("➡️ Open Login Page");
        LoginPage loginPage = homePage.header().clickSignupLogin();
        Assert.assertTrue(loginPage.isOpened(), "Login page should be opened");

        logger.info("🔐Login with credentials from testdata.properties");
        loginPage.loginOnSite(email, "password");

        logger.info("✅ Verify login was not successful");
        Assert.assertTrue(loginPage.isLoginErrorVisible(),
                "The message Your email or password is incorrect! is visible");
    }
}
