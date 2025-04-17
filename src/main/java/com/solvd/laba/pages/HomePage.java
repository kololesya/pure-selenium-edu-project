package com.solvd.laba.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isOpened() {
        String activeTab = header().getActiveTabText();
        return activeTab.equalsIgnoreCase("Home");
    }

}
