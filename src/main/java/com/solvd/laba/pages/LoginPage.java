package com.solvd.laba.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.solvd.laba.components.HeaderComponent;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    private WebElement nameField;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement signupEmailField;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement loginEmailField;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signupButton;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginButton;

    private final String errorMessage = "//p[contains(text(),'%s')]";

    @FindBy(xpath = "//h2[contains(text(),'Login to your account')]")
    private WebElement loginTitle;

    private HeaderComponent headerComponent;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        headerComponent = new HeaderComponent(driver);
    }

    public HeaderComponent getHeaderMenuComponent() {
        return headerComponent;
    }

    @Override
    public boolean isPageOpened () {
        return isElementDisplayed(loginTitle);
    }

    public SignupPage signUp(String name, String email) {
        sendKeysTo(nameField, name);
        sendKeysTo(signupEmailField, email);
        click(signupButton);
        return new SignupPage(driver);
    }

    public HomePage logIn (String email, String password) {
        sendKeysTo(loginEmailField, email);
        sendKeysTo(passwordField, password);
        click(loginButton);
        return new HomePage(driver);
    }

    public boolean isErrorMessageDisplayed (String messageText) {
        String locator = String.format(errorMessage, messageText);
        try {
            WebElement errorElement = driver.findElement(By.xpath(locator));
            return errorElement.isDisplayed();
        } catch (NoSuchElementException e) {
            logger.warn("Error message not found: {}", messageText);
            return false;
        }
    }
}
