package com.solvd.laba.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.solvd.laba.components.ProductItemComponent;
import com.solvd.laba.components.SuccessPopupComponent;

public class ProductsPage extends AbstractPage {

    @FindBy(id = "search_product")
    private WebElement searchInput;

    @FindBy(id = "submit_search")
    private WebElement searchButton;

    @FindBy(css = ".product-image-wrapper")
    private List<WebElement> productContainers;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageOpened() {
        return isElementDisplayed(searchInput);
    }

    public List<ProductItemComponent> getProductItems() {
        return productContainers.stream()
                .map(container -> new ProductItemComponent(driver, container))
                .collect(Collectors.toList());
    }

    public void searchProduct(String productName) {
        logger.info("Searching for product with name: {}", productName);
        sendKeys(searchInput, productName);
        click(searchButton);
    }

    public boolean isProductDisplayed(String partialName) {
        boolean found = getProductItems().stream()
                .anyMatch(product -> product.getProductName().toLowerCase().contains(partialName.toLowerCase()));
        if (found) {
            logger.info("Product containing '{}' was found", partialName);
        } else {
            logger.warn("Product containing '{}' was NOT found", partialName);
        }
        return found;
    }

    public ProductItemComponent findProductByPartialName(String partialName) {
        return getProductItems().stream()
                .filter(p -> p.getProductName().toLowerCase().contains(partialName.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Product not found containing: " + partialName));
    }

    public SuccessPopupComponent hoverAndAddProductToCartByPartialName(String partialName) {
        return findProductByPartialName(partialName)
                .hoverAndClickAddToCart();
    }
}
