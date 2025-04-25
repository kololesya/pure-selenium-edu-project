package com.solvd.laba;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.solvd.laba.config.Config;
import com.solvd.laba.pages.HomePage;
import com.solvd.laba.pages.LoginPage;
import com.solvd.laba.utils.UserFactory;
import com.solvd.laba.utils.WebDriverFactory;
import com.solvd.laba.models.User;

public abstract class BaseTest {

    private WebDriver driver;
    private Config config;
    protected HomePage homePage;

    protected final Logger logger = LogManager.getLogger(getClass());

    @BeforeClass
    public void setUp() {
        logger.info("Launching the browser and initializing data");
        driver = WebDriverFactory.getDriver();
        config = new Config();
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void closeBrowserSession() {
        logger.info("Closing the browser session");
        WebDriverFactory.quitDriver();
    }

    public void openHomePage() {
        String url = config.getProperty("url");
        homePage.open(url);
    }

    public HomePage loginOnSite() {
        openHomePage();
        User loginUser = UserFactory.buildUserForLogin();
        LoginPage loginPage = homePage.header().clickSignupLoginButton();
        loginPage.loginOnSite(loginUser.getEmail(), loginUser.getPassword());
        return new HomePage(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
