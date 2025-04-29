package com.solvd.laba.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.solvd.laba.models.ContactForm;

public class ContactUsPage extends AbstractPage {

    @FindBy(css = "input[data-qa='name']")
    private WebElement nameField;

    @FindBy(id = "message")
    private WebElement messageField;

    @FindBy(css = "input[data-qa='email']")
    private WebElement emailField;

    @FindBy(css = "input[data-qa='subject']")
    private WebElement subjectField;

    @FindBy(css = "input[name='upload_file']")
    private WebElement uploadFile;

    @FindBy(css = "input[data-qa='submit-button']")
    private WebElement submitButton;

    @FindBy(css = "div.status.alert-success")
    private WebElement successMessage;

    @FindBy(xpath = "//a[contains(@class, 'btn') and contains(@class, 'btn-success')]")
    private WebElement homeButton;

    public ContactUsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened () {
        return isElementDisplayed(submitButton);
     }

    public void fillContactForm(ContactForm form) {
        logger.info("Filling Contact Us form");
        File file = new File(form.getFilePath());
        if (!file.exists()) {
            throw new RuntimeException("Upload file not found at path: " + form.getFilePath());
        }
        sendKeys(nameField, form.getName());
        sendKeys(emailField, form.getEmail());
        sendKeys(subjectField, form.getSubject());
        sendKeys(messageField, form.getMessage());
        sendKeys(uploadFile, form.getFilePath());
    }

    public void submitForm() {
        logger.info("Submitting the contact form");
        click(submitButton);
        driver.switchTo().alert().accept();
        logger.info("Alert accepted after form submission");
    }

    public boolean isMessageTextCorrect(String expectedMessage) {
        logger.info("Verify success message");
        return successMessage.getText().contains(expectedMessage);
    }

    public HomePage clickHomeButton() {
        logger.info("Return to Home Page");
        click(homeButton);
        return new HomePage(driver);
    }
}
