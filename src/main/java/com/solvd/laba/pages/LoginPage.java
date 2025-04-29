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

    @FindBy(xpath = "//p[@style='color: red;']")
    private WebElement errorMessageElement;

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
        logger.info("Verifying redirection to Login page");
        return isElementDisplayed(loginTitle);
    }

    public SignupPage signUp(String name, String email) {
        logger.info("Enter user name and email");
        sendKeys(nameField, name);
        sendKeys(signupEmailField, email);
        click(signupButton);
        return new SignupPage(driver);
    }

    public HomePage logIn (String email, String password) {
        logger.info("Login with valid credentials");
        sendKeys(loginEmailField, email);
        sendKeys(passwordField, password);
        click(loginButton);
        return new HomePage(driver);
    }

    public String getDisplayedErrorMessage() {
        return errorMessageElement.getText().trim();
    }

}
