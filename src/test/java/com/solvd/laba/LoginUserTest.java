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
        LoginPage loginPage = homePage.getHeaderMenuComponent().clickSignupLoginButton();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page should be opened");
        loginPage.logIn(user.getEmail(), user.getPassword());
        if (isSuccessfulLogin) {
            Assert.assertTrue(
                    loginPage.getHeaderMenuComponent().getUserName().contains(user.getName()),
                    "'Logged in as' username is not visible");
        } else {
            Assert.assertEquals(
                    loginPage.getDisplayedErrorMessage(), expectedErrorMessage.getText(),
                    "Login verification failed");
        }
    }
}
