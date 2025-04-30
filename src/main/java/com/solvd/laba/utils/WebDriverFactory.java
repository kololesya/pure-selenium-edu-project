package com.solvd.laba.utils;

import java.net.URL;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.solvd.laba.config.EnvironmentConfig;

public class WebDriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

    public static WebDriver getDriver(String browser) {
        EnvironmentConfig config = new EnvironmentConfig();
        String runMode = config.getProperty("run.mode", "local").toLowerCase();

        try {
            if (runMode.equals("grid")) {
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
            } else {
                switch (browser.toLowerCase()) {
                    case "chrome" -> {
                        WebDriverManager.chromedriver().setup();
                        driver.set(new ChromeDriver());
                        logger.info("Local Chrome launched");
                    }
                    case "firefox" -> {
                        WebDriverManager.firefoxdriver().setup();
                        driver.set(new FirefoxDriver());
                        logger.info("Local Firefox launched");
                    }
                    default -> throw new RuntimeException("Unsupported browser: " + browser);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Driver initialization failed", e);
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
