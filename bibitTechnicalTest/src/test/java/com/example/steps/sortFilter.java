package com.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before; 
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class sortFilter {
    @Before
    public void setUp() {
        
    }

    @After
    public static void tearDown() {
        DriverManager.quitDriver();
    }

    @Given("User already on the catalog page")
    public void verifyCatalogPage() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/productTV")));
        System.out.println("Catalog page is displayed");
    }

    @And("I click the sort button")
    public void clickSortButton() {
        WebElement sortBtn = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/sortIV")));
        sortBtn.click();
        System.out.println("Sort button is clicked");
    }

    @Then("I should see the sort filter pop up")
    public void verifySortFilterPopUp() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/nameAscCL")));
        System.out.println("Sort filter pop up is displayed");
    }

    @When("I click the sort by name in descending")
    public void clickSortByNameInDescending() {
        WebElement sortByNameDsc = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/nameDesCL")));
        sortByNameDsc.click();
        System.out.println("User successfully clicked the sort by name in descending button");
    }

    @Then("I should see the item sort by name in descending")
    public void verifyItemSortByNameInDescending() {

        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/productTV")));
        
        List<WebElement> productNames = DriverManager.getDriver().findElements(By.id("com.saucelabs.mydemoapp.android:id/productTV"));
        
        if (productNames.size() >= 2) {
            String firstProduct = productNames.get(0).getText();
            String secondProduct = productNames.get(1).getText();
            
            if (firstProduct.compareToIgnoreCase(secondProduct) < 0) {
                throw new AssertionError("Products are not sorted in descending order. First: " + firstProduct + ", Second: " + secondProduct);
            }
        }
        System.out.println("User successfully verified the item sort by name in descending");
    }
    @And("I click the sort button again")
    public void clickSortButtonAgain() {
        WebElement sortBtn = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/sortIV")));
        sortBtn.click();
        System.out.println("User successfully clicked the sort button again");
    }   
    @When("I click the sort by price in ascending")
    public void clickSortByPriceInAscending() {
        WebElement sortByPriceAsc = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/priceAscCL")));
        sortByPriceAsc.click();
        System.out.println("User successfully clicked the sort by price in ascending button");
    }
    @Then("I should see the item sort by price in ascending")
    public void verifyItemSortByPriceInAscending() {

        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/productTV")));
        
        List<WebElement> productPrices = DriverManager.getDriver().findElements(By.id("com.saucelabs.mydemoapp.android:id/priceTV"));

        if (productPrices.size() >= 2) {
            String firstPrice = productPrices.get(0).getText().replace("$", "").trim();
            String secondPrice = productPrices.get(1).getText().replace("$", "").trim();
            

            double firstPriceValue = Double.parseDouble(firstPrice);
            double secondPriceValue = Double.parseDouble(secondPrice);
            
            if (firstPriceValue > secondPriceValue) {
                throw new AssertionError("Products are not sorted in ascending price order. First: " + firstPrice + ", Second: " + secondPrice);
            }
        }
        System.out.println("User successfully verified the item sort by price in ascending");
    }
}
