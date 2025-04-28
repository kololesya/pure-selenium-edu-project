package com.solvd.laba.pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.laba.config.EnvironmentConfig;
import com.solvd.laba.components.HeaderComponent;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final EnvironmentConfig ENVIRONMENT_CONFIG = new EnvironmentConfig();

    private static final long EXPLICIT_TIMEOUT;

    static {
        String timeoutFromConfig = ENVIRONMENT_CONFIG.getProperty("explicit_timeout");
        if (timeoutFromConfig != null && !timeoutFromConfig.isEmpty()) {
            EXPLICIT_TIMEOUT = Long.parseLong(timeoutFromConfig);
        } else {
            throw new RuntimeException("explicit_timeout is missing in config.properties file");
        }
    }

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_TIMEOUT));
    }

    public void open(String url) {
        driver.get(url);
    }

    public abstract boolean isPageOpened ();

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    protected void click(WebElement element) {
        waitForVisibility(element);
        logger.info("Clicking on element: {}", element);
        element.click();
    }

    protected void clearField(WebElement element) {
        waitForVisibility(element);
        element.clear();
        logger.info("Cleared text in element: {}", element);
    }

    protected void sendKeysTo(WebElement element, String text) {
        logger.info("Typing '{}' into element: {}", text, element);
        element.sendKeys(text);
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element is visible: {}", element);
            return element.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("Element is not visible after waiting: {}", element);
            return false;
        }
    }
}
