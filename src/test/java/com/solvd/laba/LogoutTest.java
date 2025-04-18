package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.pages.LoginPage;

public class LogoutTest extends BaseTest{

    @Test
    public void testLogout(){
        logger.info("🌐 Step 1: Opening home page");
        openHomePage();

        logger.info("🔐 Step 2: Logging in");
        loginOnSite();

        logger.info("🚪 Step 3: Clicking logout");
        homePage.header().clickLogout();

        logger.info("✅ Step 4: Verifying redirection to Login page");
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isOpened());
    }
}
