package com.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailFieldValidationTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Virge\\Desktop\\Woman go Tech\\Dishaga kohtumised\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");  // URL of the registration form

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testEmailFieldValidation() {
        // Locate the email field and enter an invalid email address
        WebElement emailField = driver.findElement(By.id("userEmail"));
        emailField.sendKeys("invalid-email");

        // Scroll to make the submit button visible
        WebElement submitButton = driver.findElement(By.id("submit"));
        scrollToElement(submitButton);

        // Wait for the submit button to be clickable and click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        // Get the validation message from the email field
        String actualErrorMessage = emailField.getAttribute("validationMessage");

        // Assert that the validation message contains the relevant part (to support browser variations)
        assertTrue(actualErrorMessage.contains("match the requested format") ||
                        actualErrorMessage.contains("include an '@'"),
                "The validation message should indicate an invalid email format.");
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }

    // Helper method to scroll to an element
    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(1000); // Short pause for scroll animation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}