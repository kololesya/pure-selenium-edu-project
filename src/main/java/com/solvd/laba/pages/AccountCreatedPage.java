package com.solvd.laba.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreatedPage extends AbstractPage {

    @FindBy(xpath = "//h2[@data-qa='account-created']")
    private WebElement accountCreatedMessage;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened () {
        logger.info("Checking if 'Account Created' page is opened");
        return isElementDisplayed(accountCreatedMessage)
                && accountCreatedMessage.getText().equalsIgnoreCase("Account Created!");
    }

    public HomePage clickContinue() {
        logger.info("Clicking on 'Continue' button and redirect to Home Page");
        click(continueButton);
        return new HomePage(driver);
    }
}
