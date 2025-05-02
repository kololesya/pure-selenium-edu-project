package com.solvd.laba.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.solvd.laba.components.CartItemComponent;

public class CartPage extends AbstractPage{
    @FindBy(css = ".btn.btn-default.check_out")
    private WebElement proceedToCheckout;

    @FindBy(css = "[id*='product']")
    private List<WebElement> cartItemComponents;

    public CartPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened(){
        return isElementDisplayed(proceedToCheckout);
    }

    public List<CartItemComponent> getCartItems() {
        return cartItemComponents.stream()
                .map(CartItemComponent::new)
                .collect(Collectors.toList());
    }

    public boolean isProductInCart(String productName) {
        List<String> names = getCartItems().stream()
                .map(CartItemComponent::getProductName)
                .toList();

        System.out.println("Найденные названия товаров в корзине: " + names);
        System.out.println("Ожидаемое название: " + productName);

        return names.stream()
                .anyMatch(name -> name.toLowerCase().contains(productName.toLowerCase()));
    }

}
