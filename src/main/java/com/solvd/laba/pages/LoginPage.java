package com.solvd.laba.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    @FindBy(css = "h2.title.text-center")
    private WebElement loginTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        return loginTitle.isDisplayed() &&
                loginTitle.getText().contains("Login to your account");
    }
}
