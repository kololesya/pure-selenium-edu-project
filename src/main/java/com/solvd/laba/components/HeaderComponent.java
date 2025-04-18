package com.solvd.laba.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.solvd.laba.pages.HomePage;
import com.solvd.laba.pages.LoginPage;

public class HeaderComponent {
    private WebDriver driver;

    @FindBy(css = "a[href='/login']")
    private WebElement signupLoginButton;

    @FindBy(css = "a[href='/logout']")
    private WebElement logoutButton;

    @FindBy(css = "a[href='/']")
    private WebElement homeButton;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInText;

    @FindBy(xpath = "//*[@id='header']//a[@style='color: orange;']")
    private WebElement activeTab;

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage clickHome() {
        homeButton.click();
        return new HomePage(driver);
    }

    public LoginPage clickSignupLogin() {
        signupLoginButton.click();
        return new LoginPage(driver);
    }

    public LoginPage clickLogout() {
        logoutButton.click();
        return new LoginPage(driver);
    }

    public boolean isUserLoggedIn(String username) {
        return loggedInText.isDisplayed() && loggedInText.getText().contains(username);
    }

    public String getActiveTabText() {
        return activeTab.getText().trim();
    }
}
