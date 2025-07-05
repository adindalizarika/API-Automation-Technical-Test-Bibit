package com.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before; 
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Map;
import java.util.HashMap;

public class BuyItem {
    
    @Before
    public void setUp() {
        
    }

    @After
    public static void tearDown() {
        DriverManager.quitDriver();
    }
    @Given("User already login to the account")
    public void verifyLogin() {
        WebElement menuButton = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/menuIV")));
        menuButton.click();
        WebElement loginButton = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(
            By.xpath("//android.widget.TextView[@content-desc=\"Login Menu Item\"]")));
        loginButton.click();
        WebElement usernameField = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/nameET")));
        WebElement passwordField = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/passwordET")));
        
        usernameField.sendKeys("bod@example.com");
        passwordField.sendKeys("10203040");
        WebElement submitButton = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/loginBtn")));
        submitButton.click();
        System.out.println("User successfully logged in");
    }
    @And("I click first item")
    public void clickFirstItem() {
        WebElement firstItem = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/productIV")));
        firstItem.click();
        System.out.println("User successfully clicked the first item");
    }
    @Then("I should see the item detail")
    public void verifyItemDetails() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/productTV")));
        System.out.println("Item detail page is displayed");
    }

    @When("I scroll on the item detail page")
    public void scrollItemDetailPage() {
        try {

            try {
                DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/cartBt")));
                return; // Element found, no need to scroll
            } catch (Exception e) {
                System.out.println("Element not visible initially, attempting to scroll...");
            }
    
            try {
                DriverManager.getDriver().findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                    "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/cartBt\"))"));
                System.out.println("UiAutomator2 scroll successful");
            } catch (Exception e) {
                System.out.println("UiAutomator2 scroll failed: " + e.getMessage());
                
                int height = DriverManager.getDriver().manage().window().getSize().height;
                int width = DriverManager.getDriver().manage().window().getSize().width;
    
                Map<String, Object> scrollParams = new HashMap<>();
                scrollParams.put("left", width / 2 - 50);
                scrollParams.put("top", (int) (height * 0.7));
                scrollParams.put("width", 100);
                scrollParams.put("height", (int) (height * 0.3));
                scrollParams.put("direction", "down");
                scrollParams.put("percent", 0.75);
                
                DriverManager.getDriver().executeScript("mobile: scrollGesture", scrollParams);
                System.out.println("Gesture scroll completed");
            }
    

            DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/cartBt")));

        } catch (Exception e) {
            System.out.println("Scroll failed: " + e.getMessage());

            try {
                DriverManager.getDriver().findElement(By.xpath("//*[contains(@resource-id, 'cart')]"));
                System.out.println("Found element with 'cart' in ID");
            } catch (Exception ex) {
                System.out.println("No cart-related elements found");
            }
            throw e;
        }
    }

    @Then("I should see the add to card button")
    public void verifyAddToCardButton() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/cartBt")));
        System.out.println("Add to card button is displayed");
    }

    @When("I click the increment button")
    public void clickIncrementButton() {
        WebElement incrElement = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/plusIV")));
        incrElement.click();
        System.out.println("User successfully clicked the increment button");
    }

    @Then("I should see the item becomes two")
    public void verifyItemBecomesTwo() {
        WebElement quantityElement = DriverManager.getDriver().findElement(AppiumBy.androidUIAutomator(
            "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/noTV\").text(\"2\")"));
        

        if (!quantityElement.isDisplayed()) {
            throw new AssertionError("Quantity element with text '2' is not displayed");
        }
        System.out.println("Item becomes two");
    }
    @And("I click the add to cart button")
    public void addToCartButton() {
        WebElement addToCarElement = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/cartBt")));
        addToCarElement.click();
        System.out.println("User successfully clicked the add to cart button");
    }

    @And("I click the cart button")
    public void clickCartButton() {
        WebElement clickCarElement = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/cartIV")));
        clickCarElement.click();
        System.out.println("User successfully clicked the cart button");
    }
    @Then("I should see the checkout detail page")
    public void verifyCheckoutDetailPage() {
        WebElement cartTitleElement = DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(
        By.id("com.saucelabs.mydemoapp.android:id/productTV")));
    

         DriverManager.getWait().until(ExpectedConditions.textToBePresentInElement(cartTitleElement, "My Cart"));
         System.out.println("Checkout detail page is displayed");
    }

    @And("I click the proceed to checkout button")
    public void clickCheckoutBtn() {
        WebElement checkoutBtn = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/cartBt")));
        checkoutBtn.click();
        System.out.println("User successfully clicked the proceed to checkout button");
    }

    @Then("I should see the checkout form page")
    public void verifyCheckoutFormPage() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/checkoutTitleTV")));
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/enterShippingAddressTV")));
        System.out.println("Checkout form page is displayed");
    }

    @And("I enter the full name field")
    public void enterFullNameField() {
        WebElement fullNameField = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/fullNameET")));
        fullNameField.sendKeys("John Doe");
        System.out.println("User successfully entered the full name field");
    }
    @And("I enter the address name 1 field")
    public void enterAddressName1Field() {
        WebElement addressName1Field = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/address1ET")));
        addressName1Field.sendKeys("123 Main St");
        System.out.println("User successfully entered the address name 1 field");
    }
    @And("I enter the address name 2 field")
    public void enterAddressName2Field() {
        WebElement addressName2Field = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/address2ET")));
        addressName2Field.sendKeys("Apt 1");
        System.out.println("User successfully entered the address name 2 field");
    }
    @And("I enter the city field")
    public void enterCityNameField() {
        WebElement cityNameField = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/cityET")));
        cityNameField.sendKeys("New York");
        System.out.println("User successfully entered the city field");
    }
    @And("I enter the state or region field")
    public void enterStateRegionField() {
        WebElement stateRegionField = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/stateET")));
        stateRegionField.sendKeys("NY");
        System.out.println("User successfully entered the state or region field");
    }
    @And("I enter the zip code field")
    public void enterZipCodeField() {
        WebElement zipCodeField = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/zipET")));
        zipCodeField.sendKeys("424234");
        System.out.println("User successfully entered the zip code field");
    }
    @And("I enter the country field")
    public void enterCountryField() {
        WebElement countryField = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/countryET")));
        countryField.sendKeys("United States");
        System.out.println("User successfully entered the country field");
    }
    @And("I scroll until find the payment button")
    public void scrollUntilFindPaymentButton() {
        try {

            try {
                DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/paymentBtn")));
                return; 
            } catch (Exception e) {
                System.out.println("Element not visible initially, attempting to scroll...");
            }
    
            try {
                DriverManager.getDriver().findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(" +
                    "new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/paymentBtn\"))"));
                System.out.println("UiAutomator2 scroll successful");
            } catch (Exception e) {
                System.out.println("UiAutomator2 scroll failed: " + e.getMessage());
                
                int height = DriverManager.getDriver().manage().window().getSize().height;
                int width = DriverManager.getDriver().manage().window().getSize().width;
    
                Map<String, Object> scrollParams = new HashMap<>();
                scrollParams.put("left", width / 2 - 50);
                scrollParams.put("top", (int) (height * 0.7));
                scrollParams.put("width", 100);
                scrollParams.put("height", (int) (height * 0.3));
                scrollParams.put("direction", "down");
                scrollParams.put("percent", 0.75);
                
                DriverManager.getDriver().executeScript("mobile: scrollGesture", scrollParams);
                System.out.println("Gesture scroll completed");
            }
    

            DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/paymentBtn")));

        } catch (Exception e) {
            System.out.println("Scroll failed: " + e.getMessage());

            try {
                DriverManager.getDriver().findElement(By.xpath("//*[contains(@resource-id, 'payment')]"));
                System.out.println("Found element with 'Payment' in ID");
            } catch (Exception ex) {
                System.out.println("No cart-related elements found");
            }
            throw e;
        }
    }
    @And("I click the to payment button")
    public void clickPaymentButton() {
        WebElement paymentBtn = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/paymentBtn")));
        paymentBtn.click();
        System.out.println("User successfully clicked the to payment button");
    }
    @Then("I should see the payment page")
    public void verifyPaymentMethodPage() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/enterPaymentMethodTV")));
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/paymentDetailsTV")));
        System.out.println("Payment page is displayed");
    }
    @And("I enter the full name payment field")
    public void enterFullNameFieldPayment() {
        WebElement fullNameElement = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/nameET")));
        fullNameElement.sendKeys("John Doe");
        System.out.println("User successfully entered the full name payment field");
    }
    @And("I enter the card number field")
    public void enterCardNumberField() {
        WebElement cardNumberElement = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/cardNumberET")));
        cardNumberElement.sendKeys("1234567890");
        System.out.println("User successfully entered the card number field");
    }
    @And("I enter the expiration date field")
    public void enterCardExpirationDateField() {
        WebElement cardExpirationDateElement = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/expirationDateET")));
        cardExpirationDateElement.sendKeys("1225");
        System.out.println("User successfully entered the expiration date field");
    }
    @And("I enter the security code field")
    public void enterSecurityCodeField() {
        WebElement cvvElement = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/securityCodeET")));
        cvvElement.sendKeys("123");
        System.out.println("User successfully entered the security code field");
    }
    @And("I click the review order button")
    public void clickReviewOrderButton() {
        WebElement clickReviewBtn = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/paymentBtn")));
        clickReviewBtn.click();
        System.out.println("User successfully clicked the review order button");
    }
    @Then("I should see the review order page")
    public void verifyReviewOrderPage() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/checkoutTitleTV")));
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/enterShippingAddressTV")));
        System.out.println("Review order page is displayed");
    }
    @And("I click the place order button")
    public void clickPlaceOrderButton() {
        WebElement clickPlaceOrderBtn = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/paymentBtn")));
        clickPlaceOrderBtn.click();
        System.out.println("User successfully clicked the place order button");
    }
    @Then("I should see the checkout complete page")
    public void verifyOrderConfirmationPage() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/completeTV")));
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/thankYouTV")));
        System.out.println("Checkout complete page is displayed");
    }
    @And("I click the continue shopping button")
    public void clickContinueShoppingButton() {
        WebElement clickContinueShoppingBtn = DriverManager.getWait().until(ExpectedConditions.elementToBeClickable(By.id("com.saucelabs.mydemoapp.android:id/shoopingBt")));
        clickContinueShoppingBtn.click();
        System.out.println("User successfully clicked the continue shopping button");
    }
    @Then("I should see the catalog page")
    public void verifyCatalogPage() {
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/productTV")));
        DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/productIV")));
        System.out.println("Catalog page is displayed");
    }
}
