package com.solvd.laba.pages;

import java.io.File;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage extends BasePage {

    @FindBy(name = "name")
    private WebElement nameInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "subject")
    private WebElement subjectInput;

    @FindBy(id = "message")
    private WebElement messageInput;

    @FindBy(name = "upload_file")
    private WebElement uploadFile;

    @FindBy(name = "submit")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class,'status') and contains(text(),'Success! Your details have been submitted successfully.')]")
    private WebElement successMessage;

    @FindBy(xpath = "//a[contains(@class, 'btn') and contains(@class, 'btn-success')]")
    private WebElement homeButtonFromContactPage;

    public ContactUsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isOpened() {
        return header().getActiveTabText().equalsIgnoreCase("Contact us");
    }

    public void fillContactForm(Map<String, String> contactData) {
        String filePath = contactData.get("contact_file_path");
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("Upload file not found at path: " + filePath);
        }

        nameInput.sendKeys(contactData.get("name"));
        emailInput.sendKeys(contactData.get("email"));
        subjectInput.sendKeys(contactData.get("contact_subject"));
        messageInput.sendKeys(contactData.get("contact_message"));
        uploadFile.sendKeys(filePath);
    }

    public void submitForm() {
        submitButton.click();
        driver.switchTo().alert().accept();
    }

    public boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }

    public HomePage clickHomeButton() {
        homeButtonFromContactPage.click();
        return new HomePage(driver);
    }
}
