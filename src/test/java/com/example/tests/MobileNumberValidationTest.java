package com.example.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MobileNumberValidationTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Virge\\Desktop\\Woman go Tech\\Dishaga kohtumised\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testMobileNumberValidation_LessThan10Digits() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Retry method to get the mobile number field
        WebElement mobileNumberField;
        mobileNumberField = findElementWithRetry(By.id("userNumber"));
        mobileNumberField.sendKeys("12345"); // Enter less than 10 digits

        // Scroll to and click the submit button with retry
        WebElement submitButton = findElementWithRetry(By.id("submit"));
        scrollToElement(submitButton);
        submitButton.click();

        // Scroll back to the top to view validation errors
        scrollUp();

        // Re-check the element to avoid stale reference after form submission
        mobileNumberField = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("userNumber"))));

        // Get the border color after validation
        String borderColor = mobileNumberField.getCssValue("border-color");
        String expectedColor = "rgb(220, 53, 69)"; // Expected color for error (red border)

        assertEquals(expectedColor, borderColor, "Error: Expected red border for invalid mobile number input.");
    }

    @Test
    public void testMobileNumberValidation_NonDigitCharacters() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find the mobile number field and enter input with non-digit characters
        WebElement mobileNumberField = findElementWithRetry(By.id("userNumber"));
        mobileNumberField.sendKeys("12345abc!@#"); // Input with non-digit characters

        // Scroll to and click the submit button
        WebElement submitButton = findElementWithRetry(By.id("submit"));
        scrollToElement(submitButton);
        submitButton.click();

        // Scroll back to the top to view validation errors
        scrollUp();

        // Wait for the element to refresh to avoid stale references
        mobileNumberField = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("userNumber"))));

        // Get the border color after validation
        String borderColor = mobileNumberField.getCssValue("border-color");
        String expectedColor = "rgb(220, 53, 69)"; // Expected color for error (red border)

        assertEquals(expectedColor, borderColor, "Error: Expected red border for invalid mobile number input with non-digit characters.");
    }

    private WebElement findElementWithRetry(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                return driver.findElement(locator);
            } catch (StaleElementReferenceException e) {
                attempts++;
                try {
                    Thread.sleep(1000); // Wait before retrying
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        throw new RuntimeException("Element not found after multiple attempts: " + locator.toString());
    }

    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(1000); // Delay for scroll animation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        try {
            Thread.sleep(1000); // Delay to ensure scroll completes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}