package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.models.User;
import com.solvd.laba.pages.LoginPage;
import com.solvd.laba.utils.UserFactory;

public class LoginUserTest extends BaseTest {

    @Test
    public void testLoginWithCorrectUserCredentials() throws Exception {
        User loginUser = UserFactory.buildUserForLogin();
        openHomePage();
        logger.info("Open Login Page");
        LoginPage loginPage = homePage.header().clickSignupLoginButton();
        Assert.assertTrue(loginPage.isOpened(), "Login page should be opened");
        logger.info("Login with credentials from testdata.properties");
        loginPage.loginOnSite(loginUser.getEmail(), loginUser.getPassword());
        logger.info("Verify login was successful");
        String expectedUsername = loginUser.getName();
        String actualText = loginPage.header().getLoggedInUsernameText();
        Assert.assertTrue(actualText.contains(expectedUsername),
                "Logged in username should contain the expected name from test data");
        Assert.assertTrue(homePage.isOpened(), "Home page should be opened after login");
    }

    @Test
    public void testLoginWithIncorrectUserCredentials() throws Exception {
        User wrongCredentials = UserFactory.buildUserForRegistration();
        openHomePage();
        logger.info("Open Login Page");
        LoginPage loginPage = homePage.header().clickSignupLoginButton();
        Assert.assertTrue(loginPage.isOpened(), "Login page should be opened");
        logger.info("Login with incorrect credentials");
        loginPage.loginOnSite(wrongCredentials.getEmail(), wrongCredentials.getPassword());
        logger.info("Verify login was not successful");
        Assert.assertTrue(loginPage.isLoginErrorVisible(),
                "The message Your email or password is incorrect! is visible");
    }
}
