package com.solvd.laba;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.solvd.laba.pages.HomePage;

public abstract class BaseTest {
    protected WebDriver driver;
    protected Config config;
    protected HomePage homePage;
    protected final Logger logger = LogManager.getLogger(getClass());

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
}
