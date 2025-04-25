package com.solvd.laba.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.solvd.laba.config.TestDataConfig;

public class SignupPage extends BasePage {

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
        return enterAccountInfoTitle.isDisplayed();
    }

    public void fillAccountInfo(String password, String day, String month, String year) {
        mrTitleRadio.click();
        passwordField.sendKeys(password);
        new Select(daysDropdown).selectByValue(day);
        new Select(monthsDropdown).selectByVisibleText(month);
        new Select(yearsDropdown).selectByValue(year);
        newsletterCheckbox.click();
        offersCheckbox.click();
    }

    public void fillAddressInfo(String firstName, String lastName, String company,
                                String address1, String address2, String country,
                                String state, String city, String zip, String mobile) {
        firstNameField.sendKeys(firstName);
        lastNameFiled.sendKeys(lastName);
        companyField.sendKeys(company);
        address1Field.sendKeys(address1);
        address2Field.sendKeys(address2);
        new Select(countryDropdown).selectByVisibleText(country);
        stateField.sendKeys(state);
        cityField.sendKeys(city);
        zipcodeField.sendKeys(zip);
        mobileNumberField.sendKeys(mobile);
    }

    public AccountCreatedPage clickCreateAccount() {
        createAccountButton.click();
        return new AccountCreatedPage(driver);
    }

    public static String generateUniqueEmail() {
        return "test" + System.currentTimeMillis() + "@gmail.com";
    }
}
