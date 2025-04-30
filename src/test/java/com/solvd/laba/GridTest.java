package com.solvd.laba;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridTest {
    public static void main(String[] args) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();

        URL gridUrl = new URL("http://192.168.0.55:4444/wd/hub");

        WebDriver driver = new RemoteWebDriver(gridUrl, options);
        driver.get("https://www.google.com");

        System.out.println("Title: " + driver.getTitle());

        driver.quit();
    }
}
