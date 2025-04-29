package com.solvd.laba.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.solvd.laba.config.TestDataReader;
import com.solvd.laba.models.User;

public class SignupPage extends AbstractPage {

    @FindBy(xpath = "//b[text()='Enter Account Information']")
    private WebElement accountInformationTitle;

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

    private static final TestDataReader testData = new TestDataReader();

    public SignupPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened () {
        return isElementDisplayed(accountInformationTitle);
    }

    public void maleRadioButton () {
        click(mrTitleRadio);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void selectBirthDay(String day) {
        new Select(daysDropdown).selectByValue(day);
    }

    public void selectBirthMonth(String month) {
        new Select(monthsDropdown).selectByVisibleText(month);
    }

    public void selectBirthYear(String year) {
        new Select(yearsDropdown).selectByValue(year);
    }

    public void subscribeToNewsletter() {
        click(newsletterCheckbox);
    }

    public void subscribeToOffers() {
        click(offersCheckbox);
    }

    public void enterFirstName(String firstName) {
        sendKeys(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        sendKeys(lastNameFiled, lastName);
    }

    public void enterCompany(String company) {
        sendKeys(companyField, company);
    }

    public void enterAddress1(String address1) {
        sendKeys(address1Field, address1);
    }

    public void enterAddress2(String address2) {
        sendKeys(address2Field, address2);
    }

    public void selectCountry(String country) {
        new Select(countryDropdown).selectByVisibleText(country);
    }

    public void enterState(String state) {
        sendKeys(stateField, state);
    }

    public void enterCity(String city) {
        sendKeys(cityField, city);
    }

    public void enterZipCode(String zip) {
        sendKeys(zipcodeField, zip);
    }

    public void enterMobileNumber(String mobile) {
        sendKeys(mobileNumberField, mobile);
    }

    public void completeSignUpForm(User user) {
        logger.info("Fill in signup form");
        maleRadioButton();
        enterPassword(user.getPassword());
        selectBirthDay(user.getBirthDay());
        selectBirthMonth(user.getBirthMonth());
        selectBirthYear(user.getBirthYear());
        subscribeToNewsletter();
        subscribeToOffers();
        enterFirstName(user.getName());
        enterLastName(user.getLastName());
        enterCompany(user.getCompany());
        enterAddress1(user.getAddress1());
        enterAddress2(user.getAddress2());
        selectCountry(user.getCountry());
        enterState(user.getState());
        enterCity(user.getCity());
        enterZipCode(user.getZipcode());
        enterMobileNumber(user.getMobile());
    }

    public AccountCreatedPage submitRegistrationForm() {
        logger.info("Submit registration");
        click(createAccountButton);
        return new AccountCreatedPage(driver);
    }
}
