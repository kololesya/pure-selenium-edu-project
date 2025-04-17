package com.solvd.laba.pages;

import org.openqa.selenium.WebDriver;

import com.solvd.laba.components.HeaderComponent;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public HeaderComponent header() {
        return new HeaderComponent(driver);
    }
}
