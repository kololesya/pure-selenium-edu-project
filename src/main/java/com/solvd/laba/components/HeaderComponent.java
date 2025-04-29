package com.solvd.laba.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.laba.pages.*;

public class HeaderComponent {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(HeaderComponent.class);

    @FindBy(css = "a[href='/login']")
    private WebElement signupLoginButton;

    @FindBy(css = "a[href='/logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[@href='/contact_us']")
    private WebElement contactUsButton;

    @FindBy(xpath = "//a[contains(@class, 'btn') and contains(@class, 'btn-success') and text()='Home']")
    private WebElement homeButton;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInText;

    @FindBy(xpath = "//a[@href='/products']")
    private WebElement productsTab;

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage clickHome() {
        logger.info("Clicking Home button.");
        homeButton.click();
        return new HomePage(driver);
    }

    public LoginPage clickSignupLoginButton() {
        logger.info("Clicking Signup/Login button.");
        signupLoginButton.click();
        return new LoginPage(driver);
    }

    public LoginPage clickLogout() {
        logger.info("Clicking Logout button.");
        logoutButton.click();
        return new LoginPage(driver);
    }

    public ContactUsPage clickContactUs() {
        logger.info("Navigate to Contact Us Page");
        contactUsButton.click();
        return new ContactUsPage(driver);
    }

    public ProductsPage clickProducts() {
        logger.info("Navigate to Products Page");
        productsTab.click();
        return new ProductsPage(driver);
    }

    public String getUserName () {
        if (loggedInText.isDisplayed()) {
            String username = loggedInText.getText().trim();
            logger.info("Logged in user: {}", username);
            return username;
        } else {
            throw new IllegalStateException("Logged in text is not visible on the page.");
        }
    }
}
