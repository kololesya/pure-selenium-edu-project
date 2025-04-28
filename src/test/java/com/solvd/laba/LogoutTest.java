package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.pages.LoginPage;
import com.solvd.laba.pages.HomePage;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() throws Exception {
        HomePage homePage = loginOnSite();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is opened");
        logger.info("Clicking logout");
        LoginPage loginPage = homePage.header().clickLogout();
        logger.info("Verifying redirection to Login page");
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is opened");
    }
}
