package com.solvd.laba.components;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;

public class ProductItemComponent {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final Logger logger = LoggerFactory.getLogger(ProductItemComponent.class);

    @FindBy(css = ".productinfo p")
    private WebElement productName;

    @FindBy(css = ".productinfo .price")
    private WebElement productPrice;

    @FindBy(css = ".product-overlay")
    private WebElement productOverlay;

    @FindBy(css = ".product-overlay .add-to-cart")
    private WebElement overlayAddToCartButton;

    public ProductItemComponent(WebDriver driver, WebElement rootElement) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public String getProductName() {
        return productName.getText().trim();
    }

    public SuccessPopupComponent hoverAndClickAddToCart() {
        logger.info("Hover and click 'Add to Cart' for product: {}", getProductName());
        Actions actions = new Actions(driver);
        actions.moveToElement(productOverlay).pause(Duration.ofMillis(500)).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(overlayAddToCartButton));
        logger.info("Clicking 'Add to Cart' button using JavaScript");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", overlayAddToCartButton);
        return new SuccessPopupComponent(driver);
    }
}
