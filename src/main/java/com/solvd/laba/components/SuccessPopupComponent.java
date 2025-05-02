package com.solvd.laba.components;

import java.time.Duration;


import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.laba.pages.CartPage;

public class SuccessPopupComponent {

    private final WebDriver driver;

    private static final Logger logger = LoggerFactory.getLogger(SuccessPopupComponent.class);

    @FindBy(id = "cartModal")
    private WebElement cartModalElement;

    @FindBy(css = "a[href='/view_cart']")
    private WebElement viewCartButton;

    public SuccessPopupComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isOpened() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(cartModalElement));
            return cartModalElement.isDisplayed();
        } catch (Exception e) {
            logger.warn("Modal not visible: {}", e.getMessage());
            return false;
        }
    }

    public CartPage clickViewCartButton() {
        logger.info("Clicking 'View Cart' button in success popup");
        try {
            viewCartButton.click();
        } catch (ElementClickInterceptedException e) {
            logger.warn("Click intercepted, using JavaScript click as fallback");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartButton);
        }
        return new CartPage(driver);
    }
}
