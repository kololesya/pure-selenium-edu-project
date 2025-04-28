package com.solvd.laba;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.solvd.laba.config.Config;
import com.solvd.laba.models.User;
import com.solvd.laba.pages.HomePage;
import com.solvd.laba.pages.LoginPage;
import com.solvd.laba.utils.UserFactory;
import com.solvd.laba.utils.WebDriverFactory;

public abstract class BaseTest {

    protected Config config;
    protected HomePage homePage;
    protected final Logger logger = LogManager.getLogger(getClass());

    @BeforeClass
    public void setUp() {
        logger.info("Launching the browser and initializing data");
        config = new Config();
        homePage = new HomePage(WebDriverFactory.getDriver());
    }

    @AfterClass
    public void tearDown() {
        logger.info("Closing the browser session");
        WebDriverFactory.quitDriver();
    }

    public void openHomePage() {
        String url = config.getProperty("url");
        logger.info("Open Home Page: {}", url);
        homePage.open(url);
    }

    public HomePage loginOnSite() throws Exception {
        openHomePage();
        User loginUser = UserFactory.buildUserForLogin();
        logger.info("Login on the Site with user: {}", loginUser.getEmail());
        LoginPage loginPage = homePage.header().clickSignupLoginButton();
        loginPage.loginOnSite(loginUser.getEmail(), loginUser.getPassword());
        return new HomePage(WebDriverFactory.getDriver());
    }

    public WebDriver getDriver() {
        return WebDriverFactory.getDriver();
    }
}
