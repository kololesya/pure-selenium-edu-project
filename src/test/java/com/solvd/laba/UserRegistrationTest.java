package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.solvd.laba.models.User;
import com.solvd.laba.pages.*;
import com.solvd.laba.projectConstants.ErrorMessages;
import com.solvd.laba.projectConstants.SuccessMessages;
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
        LoginPage loginPage = homePage.getHeaderMenuComponent().clickSignupLoginButton();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page should be opened");
        SignupPage signupPage = loginPage.signUp(user.getName(), user.getEmail());
        if (isNewUser) {
            Assert.assertTrue(signupPage.isPageOpened(), "Signup page should be opened");
            signupPage.completeSignUpForm(user);
            AccountCreatedPage accountCreatedPage = signupPage.submitRegistrationForm();
            Assert.assertTrue(accountCreatedPage.isPageOpened(), SuccessMessages.ACCOUNT_CREATED.getText());
            homePage = accountCreatedPage.clickContinue();
            Assert.assertTrue(homePage.isPageOpened(), "Home page should be visible after registration");
        } else {
            Assert.assertEquals(loginPage.getDisplayedErrorMessage(), expectedErrorMessage.getText(),
                    "The error message should be displayed for invalid registration.");
        }
    }
}
