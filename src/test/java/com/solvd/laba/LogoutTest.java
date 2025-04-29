package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.pages.LoginPage;
import com.solvd.laba.pages.HomePage;

public class LogoutTest extends BaseTest {

    @Test
    public void testLogout() throws Exception {
        HomePage homePage = login();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is opened");
        LoginPage loginPage = homePage.getHeaderMenuComponent().clickLogout();
        Assert.assertTrue(loginPage.isPageOpened(), "Login page is opened");
    }
}
