package com.solvd.laba.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.solvd.laba.pages.HomePage;
import com.solvd.laba.pages.LoginPage;
import com.solvd.laba.pages.ContactUsPage;
import com.solvd.laba.pages.ProductsPage;

public class HeaderComponent {
    private WebDriver driver;

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
        homeButton.click();
        return new HomePage(driver);
    }

    public LoginPage clickSignupLoginButton() {
        signupLoginButton.click();
        return new LoginPage(driver);
    }

    public LoginPage clickLogout() {
        logoutButton.click();
        return new LoginPage(driver);
    }

    public ContactUsPage clickContactUs() {
        contactUsButton.click();
        return new ContactUsPage(driver);
    }

    public ProductsPage clickProducts() {
        productsTab.click();
        return new ProductsPage(driver);
    }

    public String getUserName () {
        if (loggedInText.isDisplayed()) {
            return loggedInText.getText().trim();
        } else {
            throw new IllegalStateException("Logged in text is not visible on the page.");
        }
    }
}
