package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.solvd.laba.pages.HomePage;
import com.solvd.laba.pages.ProductsPage;

public class ProductsPageTest extends BaseTest {

    @Test
    public void testProductsPageIsOpened() {
        HomePage homePage = openHomePage();
        ProductsPage productsPage = homePage.getHeaderMenuComponent().clickProducts();
        Assert.assertTrue(productsPage.isPageOpened(), "Products page should be opened and visible");
    }
}
