package com.example.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerifyGenderSelectionTest {
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
    public void testGenderSelection() {
        WebElement maleOption = driver.findElement(By.xpath("//label[text()='Male']/preceding-sibling::input"));
        WebElement femaleOption = driver.findElement(By.xpath("//label[text()='Female']/preceding-sibling::input"));
        WebElement otherOption = driver.findElement(By.xpath("//label[text()='Other']/preceding-sibling::input"));

        // Scroll to the "Male" radio button and select it
        scrollToElement(maleOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", maleOption);

        // Verify the "Male" option is selected
        assertTrue(maleOption.isSelected(), "Error: Male option not selected.");

        // Select "Female" option and check if only it is selected
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", femaleOption);
        assertTrue(femaleOption.isSelected(), "Error: Female option not selected.");
        assertFalse(maleOption.isSelected(), "Error: Multiple gender options selected.");

        // Verify that "Other" is not selected
        assertFalse(otherOption.isSelected(), "Error: 'Other' option should not be selected.");
    }

    // Utility method to scroll to an element
    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500); // Optional: allow some time for the scrolling animation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Properly handle the interrupt
        }
    }
}
