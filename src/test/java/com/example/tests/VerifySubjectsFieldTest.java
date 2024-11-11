package com.example.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerifySubjectsFieldTest {
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
    public void testSubjectsField() {
        WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));

        // Scroll to the subjects input field to ensure visibility
        scrollToElement(subjectsInput);

        // Add "Maths" as a subject
        subjectsInput.sendKeys("Maths");
        subjectsInput.sendKeys(Keys.ENTER);

        // Verify that "Maths" was added
        WebElement addedSubject = driver.findElement(By.xpath("//div[contains(@class, 'subjects-auto-complete__multi-value__label') and text()='Maths']"));
        assertTrue(addedSubject.isDisplayed(), "Error: Subject 'Maths' not added.");

        // Add "Physics" as a second subject
        subjectsInput.sendKeys("Physics");
        subjectsInput.sendKeys(Keys.ENTER);

        // Verify that "Physics" was added
        WebElement secondSubject = driver.findElement(By.xpath("//div[contains(@class, 'subjects-auto-complete__multi-value__label') and text()='Physics']"));
        assertTrue(secondSubject.isDisplayed(), "Error: Subject 'Physics' not added.");
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