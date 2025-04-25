package com.solvd.laba.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.solvd.laba.components.HeaderComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open(String url) {
        driver.get(url);
    }

    public abstract boolean isOpened();

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
