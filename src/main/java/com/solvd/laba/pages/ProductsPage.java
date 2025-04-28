package com.solvd.laba.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends AbstractPage {

    @FindBy(id = "search_product")
    private WebElement searchInput;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened () {
        return isElementDisplayed(searchInput);
    }
}
