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

    public void selectMaleTitle() {
        click(mrTitleRadio);
    }

    public void fillPassword(String password) {
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

    public void fillFirstName(String firstName) {
        sendKeys(firstNameField, firstName);
    }

    public void fillLastName(String lastName) {
        sendKeys(lastNameFiled, lastName);
    }

    public void fillCompany(String company) {
        sendKeys(companyField, company);
    }

    public void fillAddress1(String address1) {
        sendKeys(address1Field, address1);
    }

    public void fillAddress2(String address2) {
        sendKeys(address2Field, address2);
    }

    public void selectCountry(String country) {
        new Select(countryDropdown).selectByVisibleText(country);
    }

    public void fillState(String state) {
        sendKeys(stateField, state);
    }

    public void fillCity(String city) {
        sendKeys(cityField, city);
    }

    public void fillZipCode(String zip) {
        sendKeys(zipcodeField, zip);
    }

    public void fillMobileNumber(String mobile) {
        sendKeys(mobileNumberField, mobile);
    }

    public void completeSignUpForm(User user) {
        logger.info("Fill in signup form");
        selectMaleTitle();
        fillPassword(user.getPassword());
        selectBirthDay(user.getBirthDay());
        selectBirthMonth(user.getBirthMonth());
        selectBirthYear(user.getBirthYear());
        subscribeToNewsletter();
        subscribeToOffers();
        fillFirstName(user.getName());
        fillLastName(user.getLastName());
        fillCompany(user.getCompany());
        fillAddress1(user.getAddress1());
        fillAddress2(user.getAddress2());
        selectCountry(user.getCountry());
        fillState(user.getState());
        fillCity(user.getCity());
        fillZipCode(user.getZipcode());
        fillMobileNumber(user.getMobilePhone());
    }

    public AccountCreatedPage submitRegistrationForm() {
        logger.info("Submit registration");
        click(createAccountButton);
        return new AccountCreatedPage(driver);
    }
}
