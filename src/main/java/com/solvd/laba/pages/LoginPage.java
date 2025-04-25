package com.solvd.laba.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    @FindBy(name = "name")
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

    @FindBy(xpath = "//p[contains(text(),'Your email or password is incorrect!')]")
    private WebElement loginErrorMessage;

    @FindBy(xpath = "//p[contains(text(),'Email Address already exist!')]")
    private WebElement signupErrorMessage;

    @FindBy(xpath = "//h2[contains(text(),'Login to your account')]")
    private WebElement loginTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isOpened() {
        return isElementDisplayed(loginTitle);
    }


    public SignupPage signUp(String name, String email) {
        sendKeysTo(nameField, name);
        sendKeysTo(signupEmailField, email);
        click(signupButton);
        return new SignupPage(driver);
    }

    public HomePage loginOnSite(String email, String password) {
        sendKeysTo(loginEmailField, email);
        sendKeysTo(passwordField, password);
        click(loginButton);
        return new HomePage(driver);
    }

    public boolean isLoginErrorVisible() {
        return isElementDisplayed(loginErrorMessage);
    }

    public boolean isSignupErrorVisible() {
        return isElementDisplayed(signupErrorMessage);
    }
}
