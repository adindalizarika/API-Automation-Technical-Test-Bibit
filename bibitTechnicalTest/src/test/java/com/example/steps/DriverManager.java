package com.example.steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static AndroidDriver driver;
    private static WebDriverWait wait;
    
    public static AndroidDriver getDriver() {
        if (driver == null) {
            try {
                UiAutomator2Options options = new UiAutomator2Options()
                        .setDeviceName("emulator-5554")
                        .setPlatformName("Android")
                        .setAppPackage("com.saucelabs.mydemoapp.android")
                        .setAppActivity("com.saucelabs.mydemoapp.android.view.activities.SplashActivity")
                        .setAutomationName("UiAutomator2")
                        .amend("appWaitActivity", "*");

                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }
    
    public static WebDriverWait getWait() {
        if (wait == null) {
            getDriver(); // This will initialize wait too
        }
        return wait;
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }
}