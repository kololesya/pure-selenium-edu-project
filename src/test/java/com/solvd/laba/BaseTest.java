package com.solvd.laba;

import com.solvd.laba.config.EnvironmentConfig;
import com.solvd.laba.models.User;
import com.solvd.laba.pages.HomePage;
import com.solvd.laba.services.LoginService;
import com.solvd.laba.utils.UserFactory;
import com.solvd.laba.utils.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    protected EnvironmentConfig environmentConfig;
    protected final Logger logger = LogManager.getLogger(getClass());
    protected WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        logger.info("Launching browser: {}", browser);
        environmentConfig = new EnvironmentConfig();
        driver = WebDriverFactory.getDriver(browser);
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Closing the browser session");
        WebDriverFactory.quitDriver();
    }

    public HomePage openHomePage() {
        String url = environmentConfig.getProperty("url");
        logger.info("Open Home Page: {}", url);
        HomePage homePage = new HomePage(driver);
        homePage.open(url);
        return homePage;
    }

    public HomePage login() throws Exception {
        User loginUser = UserFactory.buildUserForLogin();
        logger.info("Logging in with user: {}", loginUser.getEmail());
        return new LoginService(driver).login(loginUser);
    }
}
