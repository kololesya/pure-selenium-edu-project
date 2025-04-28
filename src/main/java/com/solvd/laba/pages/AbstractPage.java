package com.solvd.laba.pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.laba.config.Config;
import com.solvd.laba.components.HeaderComponent;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private static final Config config = new Config();
    private static final long EXPLICIT_TIMEOUT;

    static {
        String timeoutFromConfig = config.getProperty("explicit_timeout");
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

    public HeaderComponent header() {
        return new HeaderComponent(driver);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        logger.info("Clicking on element: {}", element);
        element.click();
    }

    protected void sendKeysTo(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        logger.info("Typing '{}' into element: {}", text, element);
        element.clear();
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
