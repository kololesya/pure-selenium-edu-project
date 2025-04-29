package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.solvd.laba.models.User;
import com.solvd.laba.pages.HomePage;
import com.solvd.laba.pages.LoginPage;
import com.solvd.laba.projectConstants.ErrorMessages;
import com.solvd.laba.utils.UserFactory;

public class LoginUserTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws Exception {
        return new Object[][] {
                { UserFactory.buildUserForLogin(), true, null },
                { UserFactory.buildUserForRegistration(), false, ErrorMessages.INVALID_LOGIN }
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(User user, boolean isSuccessfulLogin, ErrorMessages expectedErrorMessage) {
        HomePage homePage = openHomePage();
        logger.info("Open Login Page");
        LoginPage loginPage = homePage.getHeaderMenuComponent().clickSignupLoginButton();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page should be opened");

        logger.info("Login with credentials");
        loginPage.logIn(user.getEmail(), user.getPassword());

        if (isSuccessfulLogin) {
            logger.info("Verify login was successful");
            String expectedUsername = user.getName();
            String actualText = loginPage.getHeaderMenuComponent().getLoggedInUsernameText();
            Assert.assertTrue(actualText.contains(expectedUsername),
                    "Logged in username should contain the expected name from test data");
            Assert.assertTrue(homePage.isPageOpened(), "Home page should be opened after login");
        } else {
            logger.info("Verify login was not successful");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(expectedErrorMessage.getText()),
                    "The error message should be displayed for invalid login.");
        }
    }
}
