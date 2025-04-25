package com.solvd.laba;

import com.solvd.laba.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.pages.LoginPage;

public class LogoutTest extends BaseTest{

    @Test
    public void testLogout(){
        logger.info("Step 1: Logging in");
        HomePage homePage = loginOnSite();
        Assert.assertTrue(homePage.isOpened());
        logger.info("Step 2: Clicking logout");
        LoginPage loginPage = homePage.header().clickLogout();
        logger.info("Step 3: Verifying redirection to Login page");
        Assert.assertTrue(loginPage.isOpened());
    }
}
