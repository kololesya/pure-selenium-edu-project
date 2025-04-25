package com.solvd.laba.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.solvd.laba.models.ContactForm;

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

    public void fillContactForm(ContactForm form) {
        File file = new File(form.getFilePath());
        if (!file.exists()) {
            throw new RuntimeException("Upload file not found at path: " + form.getFilePath());
        }

        nameInput.sendKeys(form.getName());
        emailInput.sendKeys(form.getEmail());
        subjectInput.sendKeys(form.getSubject());
        messageInput.sendKeys(form.getMessage());
        uploadFile.sendKeys(form.getFilePath());
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
