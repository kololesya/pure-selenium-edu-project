package com.solvd.laba;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void testHomePageIsOpened() {
        logger.info("🚀 Verifying that the home page is opened");
        String url = config.getProperty("url");
        homePage.open(url);
        Assert.assertTrue(homePage.isOpened());
    }
}
