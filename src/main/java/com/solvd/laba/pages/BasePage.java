package com.solvd.laba.pages;

import org.openqa.selenium.WebDriver;

import com.solvd.laba.components.HeaderComponent;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public abstract boolean isOpened();

    public HeaderComponent header() {
        return new HeaderComponent(driver);
    }
}
