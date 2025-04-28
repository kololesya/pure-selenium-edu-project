package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.pages.HomePage;
import com.solvd.laba.pages.ProductsPage;

public class ProductsPageTest extends BaseTest {

    @Test
    public void testProductsPageIsOpened() {
        HomePage homePage = openHomePage();
        logger.info("Navigate to Products Page");
        ProductsPage productsPage = homePage.getHeaderMenuComponent().clickProducts();
        logger.info("Assert that Products Page is opened");
        Assert.assertTrue(productsPage.isPageOpened(), "Products page should be opened and visible");
    }
}
