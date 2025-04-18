package com.solvd.laba;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.solvd.laba.config.Config;
import com.solvd.laba.config.TestDataConfig;
import com.solvd.laba.pages.LoginPage;
import com.solvd.laba.utils.WebDriverFactory;
import com.solvd.laba.pages.HomePage;

public abstract class BaseTest {
    protected WebDriver driver;
    protected Config config;
    protected HomePage homePage;
    protected final Logger logger = LogManager.getLogger(getClass());

    protected TestDataConfig testData = new TestDataConfig();

    protected String email = testData.getProperty("email");
    protected String password = testData.getProperty("password");

    @BeforeClass
    public void setUp() {
        logger.info("🔧 Launching the browser and initializing data");
        driver = WebDriverFactory.getDriver();
        config = new Config();
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown() {
        logger.info("🧹 Closing the browser session");
        WebDriverFactory.quitDriver();
    }

    public void openHomePage() {
        String url = config.getProperty("url");
        homePage.open(url);
    }

    public void loginOnSite(){
        LoginPage loginPage = homePage.header().clickSignupLogin();
        loginPage.loginOnSite(email, password);
    }
}
