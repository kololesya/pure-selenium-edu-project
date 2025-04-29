package com.solvd.laba;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.components.ProductItemComponent;
import com.solvd.laba.components.SuccessPopupComponent;
import com.solvd.laba.pages.CartPage;
import com.solvd.laba.pages.HomePage;
import com.solvd.laba.pages.ProductsPage;
import com.solvd.laba.projectConstants.Products;

public class ProductsPageTest extends BaseTest {

    @Test
    public void testProductComponentsDisplayCorrectly() {
        HomePage homePage = openHomePage();
        ProductsPage productsPage = homePage.getHeaderMenuComponent().clickProducts();
        Assert.assertTrue(productsPage.isPageOpened(), "Products page should be opened");
        List<ProductItemComponent> products = productsPage.getProductItems();
        Assert.assertFalse(products.isEmpty(), "Product list should not be empty");
        for (ProductItemComponent product : products) {
            String name = product.getProductName();
            Assert.assertNotNull(name, "Product name should not be null");
            Assert.assertFalse(name.isBlank(), "Product name should not be blank");
        }
    }

    @Test
    public void testSearchProductByName() {
        HomePage homePage = openHomePage();
        ProductsPage productsPage = homePage.getHeaderMenuComponent().clickProducts();
        Assert.assertTrue(productsPage.isPageOpened(), "Products page should be opened");
        productsPage.searchProduct(Products.WHITE_TOP.getText());
        Assert.assertTrue(productsPage.isProductDisplayed(Products.WHITE_TOP.getText()),
                "Expected product '" + Products.WHITE_TOP.getText() + "' was not found in the search results.");
    }

    @Test
    public void testAddProductToCartWithoutSearch() {
        HomePage homePage = openHomePage();
        ProductsPage productsPage = homePage.getHeaderMenuComponent().clickProducts();
        SuccessPopupComponent successPopupComponent = productsPage.hoverAndAddProductToCartByPartialName(Products.WHITE_TOP.getText());
        Assert.assertTrue(successPopupComponent.isOpened());
        CartPage cartPage = successPopupComponent.clickViewCartButton();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page should be opened");
        Assert.assertTrue(cartPage.isProductInCart(Products.WHITE_TOP.getText()),
                "Expected product was not found in the cart: " + Products.WHITE_TOP.getText());
    }
}