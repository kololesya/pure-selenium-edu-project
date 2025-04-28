package com.solvd.laba.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.solvd.laba.config.TestDataConfig;

public class SignupPage extends AbstractPage {

    @FindBy(xpath = "//b[text()='Enter Account Information']")
    private WebElement enterAccountInfoTitle;

    @FindBy(id = "id_gender1")
    private WebElement mrTitleRadio;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "days")
    private WebElement daysDropdown;

    @FindBy(id = "months")
    private WebElement monthsDropdown;

    @FindBy(id = "years")
    private WebElement yearsDropdown;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement offersCheckbox;

    @FindBy(id = "first_name")
    private WebElement firstNameField;

    @FindBy(id = "last_name")
    private WebElement lastNameFiled;

    @FindBy(id = "company")
    private WebElement companyField;

    @FindBy(id = "address1")
    private WebElement address1Field;

    @FindBy(id = "address2")
    private WebElement address2Field;

    @FindBy(id = "country")
    private WebElement countryDropdown;

    @FindBy(id = "state")
    private WebElement stateField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "zipcode")
    private WebElement zipcodeField;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberField;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    private WebElement createAccountButton;

    private static final TestDataConfig testData = new TestDataConfig();

    public SignupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isOpened() {
        return isElementDisplayed(enterAccountInfoTitle);
    }

    /**
     * Fills the registration details section with the given user information.
     *
     * @param password User's password
     * @param day      User's birth day
     * @param month    User's birth month
     * @param year     User's birth year
     */

    public void fillRegistrationDetails(String password, String day, String month, String year) {
        click(mrTitleRadio);
        sendKeysTo(passwordField, password);
        new Select(daysDropdown).selectByValue(day);
        new Select(monthsDropdown).selectByVisibleText(month);
        new Select(yearsDropdown).selectByValue(year);
        click(newsletterCheckbox);
        click(offersCheckbox);
    }

    /**
     * Fills the address details section with the given user information.
     *
     * @param firstName User's first name
     * @param lastName  User's last name
     * @param company   User's company name
     * @param address1  User's primary address
     * @param address2  User's secondary address
     * @param country   User's country
     * @param state     User's state
     * @param city      User's city
     * @param zip       User's zip code
     * @param mobile    User's mobile number
     */

    public void fillAddressDetails(String firstName, String lastName, String company,
                                   String address1, String address2, String country,
                                   String state, String city, String zip, String mobile) {
        sendKeysTo(firstNameField, firstName);
        sendKeysTo(lastNameFiled, lastName);
        sendKeysTo(companyField, company);
        sendKeysTo(address1Field, address1);
        sendKeysTo(address2Field, address2);
        new Select(countryDropdown).selectByVisibleText(country);
        sendKeysTo(stateField, state);
        sendKeysTo(cityField, city);
        sendKeysTo(zipcodeField, zip);
        sendKeysTo(mobileNumberField, mobile);
    }

    public AccountCreatedPage submitRegistrationForm() {
        click(createAccountButton);
        return new AccountCreatedPage(driver);
    }
}
