package com.example.steps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class LogoutSelenium {
    WebDriver driver;

    @Given("I am on the bibit home page")
    public void goToHomePage() {
        driver = new ChromeDriver();
        driver.get("https://app.bibit.id/");
    }

    @And("I click the profile button")
    public void clickProfileButton() {
        driver.findElement(By.cssSelector("[data-testid='tabbar-profile']")).click();
    }

    @Then("I should see the profile page")
    public void verifyProfilePage() {
        assertTrue(driver.getCurrentUrl().contains("/profile"));
        driver.quit();
    }

    @And("I scroll to the bottom of the page")
    public void scrollBottomPage() {
        driver.findElement(By.tagName("body")).sendKeys(Keys.END);
        WebElement logoutBtn = driver.findElement(By.xpath("//*[contains(text(), 'Logout')]"));
        if (logoutBtn.isDisplayed()) {
            System.out.println("Element is visible");
        } else {
            System.out.println("Element is not visible");
        }
    }

    @And("I click the logout button")
    public void clickLogoutButton() {
        driver.findElement(By.xpath("//*[contains(text(), 'Logout')]")).click();
    }

    @And("I should see the login button")
    public void checkRedirectToLoginPage() {
        WebElement loginBtn = driver.findElement(By.xpath("//*[contains(text(), 'Login')]"));
        if (loginBtn.isDisplayed()) {
            System.out.println("Element is visible");
        } else {
            System.out.println("Element is not visible");
        }
        driver.quit();
    }
}