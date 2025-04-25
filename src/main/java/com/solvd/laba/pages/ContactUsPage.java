package com.solvd.laba.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.solvd.laba.models.ContactForm;

public class ContactUsPage extends AbstractPage {

    @FindBy(name = "name")
    private WebElement nameField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "subject")
    private WebElement subjectField;

    @FindBy(id = "message")
    private WebElement messageField;

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
        logger.info("Checking if Contact Us page is opened");
        return header().getActiveTabText().equalsIgnoreCase("Contact us");
    }

    public void fillContactForm(ContactForm form) {
        logger.info("Filling Contact Us form");

        File file = new File(form.getFilePath());
        if (!file.exists()) {
            throw new RuntimeException("Upload file not found at path: " + form.getFilePath());
        }

        sendKeysTo(nameField, form.getName());
        sendKeysTo(emailField, form.getEmail());
        sendKeysTo(subjectField, form.getSubject());
        sendKeysTo(messageField, form.getMessage());
        sendKeysTo(uploadFile, form.getFilePath());
    }

    public void submitForm() {
        logger.info("Submitting contact form");
        click(submitButton);
        driver.switchTo().alert().accept();
        logger.info("Alert accepted after form submission");
    }

    public boolean isSuccessMessageDisplayed() {
        logger.info("Checking if success message is displayed");
        return isElementDisplayed(successMessage);
    }

    public HomePage clickHomeButton() {
        logger.info("Clicking on 'Home' button from Contact Us page");
        click(homeButtonFromContactPage);
        return new HomePage(driver);
    }
}
