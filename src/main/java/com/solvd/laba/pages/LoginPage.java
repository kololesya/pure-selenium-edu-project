package com.solvd.laba.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

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


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isOpened() {
        String activeTab = header().getActiveTabText();
        return activeTab.trim().equalsIgnoreCase("Signup / Login");
    }

    public SignupPage signUp(String name, String email) {
        nameField.sendKeys(name);
        signupEmailField.sendKeys(email);
        signupButton.click();
        return new SignupPage(driver);
    }

    public HomePage loginOnSite(String email, String password) {
        loginEmailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);
    }

    public boolean isLoginErrorVisible() {
        return loginErrorMessage.isDisplayed();
    }

    public boolean isSignupErrorVisible() {
        return signupErrorMessage.isDisplayed();
    }
}
