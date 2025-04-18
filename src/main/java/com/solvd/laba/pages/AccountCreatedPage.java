package com.solvd.laba.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatedPage extends BasePage{
    @FindBy(xpath = "//h2[@data-qa='account-created']")
    private WebElement accountCreatedMessage;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isOpened() {
        return accountCreatedMessage.isDisplayed()
                && accountCreatedMessage.getText().equalsIgnoreCase("Account Created!");
    }

    public HomePage clickContinue() {
        continueButton.click();
        return new HomePage(driver);
    }
}
