package com.solvd.laba.pages;

import com.solvd.laba.config.TestDataConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignupPage extends BasePage{

    @FindBy(xpath = "//b[text()='Enter Account Information']")
    private WebElement enterAccountInfoTitle;

    @FindBy(id = "id_gender1")
    private WebElement mrTitleRadio;

    @FindBy(id = "password")
    private WebElement passwordInput;

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
    private WebElement firstNameInput;

    @FindBy(id = "last_name")
    private WebElement lastNameInput;

    @FindBy(id = "company")
    private WebElement companyInput;

    @FindBy(id = "address1")
    private WebElement address1Input;

    @FindBy(id = "address2")
    private WebElement address2Input;

    @FindBy(id = "country")
    private WebElement countryDropdown;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "zipcode")
    private WebElement zipcodeInput;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberInput;

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
        passwordInput.sendKeys(password);
        new Select(daysDropdown).selectByValue(day);
        new Select(monthsDropdown).selectByVisibleText(month);
        new Select(yearsDropdown).selectByValue(year);
        newsletterCheckbox.click();
        offersCheckbox.click();
    }

    public void fillAddressInfo(String firstName, String lastName, String company,
                                String address1, String address2, String country,
                                String state, String city, String zip, String mobile) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        companyInput.sendKeys(company);
        address1Input.sendKeys(address1);
        address2Input.sendKeys(address2);
        new Select(countryDropdown).selectByVisibleText(country);
        stateInput.sendKeys(state);
        cityInput.sendKeys(city);
        zipcodeInput.sendKeys(zip);
        mobileNumberInput.sendKeys(mobile);
    }

    public AccountCreatedPage clickCreateAccount() {
        createAccountButton.click();
        return new AccountCreatedPage(driver);
    }

    public static String generateUniqueEmail() {
        return "test" + System.currentTimeMillis() + "@gmail.com";
    }
}
