package com.solvd.laba.utils;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.laba.config.EnvironmentConfig;

public class WebDriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            try {
                EnvironmentConfig config = new EnvironmentConfig();
                URL gridUrl = new URL(config.getProperty("selenium.grid.url"));

                switch (browser.toLowerCase()) {
                    case "chrome" -> {
                        ChromeOptions options = new ChromeOptions();
                        driver.set(new RemoteWebDriver(gridUrl, options));
                        logger.info("Remote Chrome launched on Grid");
                    }
                    case "firefox" -> {
                        FirefoxOptions options = new FirefoxOptions();
                        driver.set(new RemoteWebDriver(gridUrl, options));
                        logger.info("Remote Firefox launched on Grid");
                    }
                    default -> throw new RuntimeException("Unsupported browser: " + browser);
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize RemoteWebDriver", e);
            }
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
