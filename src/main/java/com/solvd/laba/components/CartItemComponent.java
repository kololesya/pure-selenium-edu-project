package com.solvd.laba.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class CartItemComponent {

    @FindBy(xpath = ".//td[@class='cart_description']//a")
    private WebElement productNameElement;

    @FindBy(className = "cart_price")
    private WebElement priceCell;

    @FindBy(className = "cart_quantity")
    private WebElement quantityCell;

    @FindBy(className = "cart_total")
    private WebElement totalPriceCell;

    public CartItemComponent(WebElement rootElement) {
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    public String getProductName () {
        return productNameElement.getText().trim();
    }

    public String getPrice() {
        return priceCell.getText().trim();
    }

    public String getQuantity() {
        return quantityCell.getText().trim();
    }

    public String getTotalPrice() {
        return totalPriceCell.getText().trim();
    }
}
