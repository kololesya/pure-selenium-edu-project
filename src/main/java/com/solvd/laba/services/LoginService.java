package com.solvd.laba.services;

import org.openqa.selenium.WebDriver;

import com.solvd.laba.config.EnvironmentConfig;
import com.solvd.laba.models.User;
import com.solvd.laba.pages.HomePage;
import com.solvd.laba.pages.LoginPage;

public class LoginService {

    private final WebDriver driver;
    private final EnvironmentConfig environmentConfig;

    public LoginService (WebDriver driver) {
        this.driver = driver;
        this.environmentConfig = new EnvironmentConfig();
    }

    public HomePage login(User user) {
        HomePage homePage = new HomePage(driver);
        homePage.open(environmentConfig.getProperty("url"));
        LoginPage loginPage = homePage.getHeaderMenuComponent().clickSignupLoginButton();
        loginPage.logIn(user.getEmail(), user.getPassword());
        return new HomePage(driver);
    }
}
