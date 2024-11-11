package com.example.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RequiredFieldValidationTest {
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
       driver.quit();
    }

    @Test
    public void testRequiredFieldValidation() {
        // Scroll to and click the submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        scrollToElement(submitButton);

        // Use WebDriverWait to ensure the button is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));

        // Click the submit button
        submitButton.click();

        // Scroll back to the top of the page to see validation errors
        scrollUp();

        // Validation checks: Ensure the required fields show error indicators
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        WebElement lastNameField = driver.findElement(By.id("lastName"));
        WebElement mobileNumberField = driver.findElement(By.id("userNumber"));
        WebElement genderField = driver.findElement(By.id("gender-radio-1"));

        // Check that the required fields have the "form-control error" class (or similar)
        assertTrue(firstNameField.getAttribute("class").contains("form-control"),
                "First name field should have 'form-control' class.");
        assertTrue(lastNameField.getAttribute("class").contains("form-control"),
                "Last name field should have 'form-control' class.");
        assertTrue(mobileNumberField.getAttribute("class").contains("form-control"),
                "Mobile number field should have 'form-control' class.");
        assertTrue(genderField.getAttribute("class").contains("custom-control-input"),
                "Gender field should have 'custom-control-input' class.");
    }


    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(3000); // Allow some time for the scrolling animation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll the page back to the top
        js.executeScript("window.scrollTo(0, 0);");
        try {
            Thread.sleep(2000); // Add a small delay to ensure scrolling completes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}