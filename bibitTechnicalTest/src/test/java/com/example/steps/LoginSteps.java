package com.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginSteps {
    
    @Before
    public static void setUp() {

    }

    @After
    public static void tearDown() {
        DriverManager.quitDriver();
    }

    @Given("I am on the catalog page")
    public void verifyCatalogPage() {
    DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/menuIV")));
    System.out.println("Catalog page is displayed");
    }

    @And("I click the sidebar button")
    public void clickSidebarButton() {
        WebElement menuButton = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/menuIV")));
        menuButton.click();
        System.out.println("Sidebar button clicked");
    }   

    @Then("I should see the sidebar")
    public void verifySidebar() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/menuRV")));
        System.out.println("Sidebar is displayed");
    }

    @When("I click the login button")
    public void clickLoginButton() {
        WebElement loginButton = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(
            By.xpath("//android.widget.TextView[@content-desc=\"Login Menu Item\"]")));
        loginButton.click();
        System.out.println("Login button clicked");
    }

    @Then("I should see the login form")
    public void verifyLoginForm() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/loginTV")));
        System.out.println("Login form is displayed");
    }

    @When("I fill in the login form with valid credentials")
    public void fillLoginForm() {
        WebElement usernameField = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/nameET")));
        WebElement passwordField = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/passwordET")));
        
        usernameField.sendKeys("bod@example.com");
        passwordField.sendKeys("10203040");
        System.out.println("User successfully entered the login form");
    }

    @And("I click the submit button")
    public void clickSubmitButton() {
        WebElement submitButton = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/loginBtn")));
        submitButton.click();
        System.out.println("User successfully clicked the submit button");
    }

    @Then("I should see the homepage")
        public void checkRedirectToHomePage() {
            DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/productTV")));
            System.out.println("Homepage is displayed");
    }

    @And("I want to see logout button")
    public void verifyLogoutButton() {
        WebElement menuButton = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/menuIV")));
        menuButton.click();
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@content-desc=\"Logout Menu Item\"]")));
        System.out.println("Logout button is displayed");
    }
}
