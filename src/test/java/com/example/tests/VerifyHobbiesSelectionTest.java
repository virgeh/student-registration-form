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

import static org.junit.jupiter.api.Assertions.assertTrue;


public class VerifyHobbiesSelectionTest {
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
    public void testHobbiesSelection() {
        // Verify "Sports" checkbox
        WebElement sportsCheckbox = driver.findElement(By.id("hobbies-checkbox-1"));
        scrollToElement(sportsCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sportsCheckbox);

        assertTrue(sportsCheckbox.isSelected(), "Sports checkbox should be selected after clicking.");

        // Verify "Reading" checkbox
        WebElement readingCheckbox = driver.findElement(By.id("hobbies-checkbox-2"));
        scrollToElement(readingCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", readingCheckbox);
        assertTrue(readingCheckbox.isSelected(), "Reading checkbox should be selected after clicking.");

        // Verify "Music" checkbox
        WebElement musicCheckbox = driver.findElement(By.id("hobbies-checkbox-3"));
        scrollToElement(musicCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", musicCheckbox);
        assertTrue(musicCheckbox.isSelected(), "Music checkbox should be selected after clicking.");
    }
    /**
     * Utility method to scroll to an element and click it.
     * @param element WebElement to be scrolled into view and clicked.
     */
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