package com.solvd.laba.utils;

import com.solvd.laba.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            Config config = new Config();
            String chromeDriverPath = config.getProperty("chrome_driver_path");

            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver.set(new ChromeDriver());
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
