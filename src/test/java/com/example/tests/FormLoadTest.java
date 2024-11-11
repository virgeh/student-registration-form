package com.example.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormLoadTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Make sure to set the path to your chromedriver.exe or add it to your system PATH
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Virge\\Desktop\\Woman go Tech\\Dishaga kohtumised\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testFormLoad() {
        driver.get("https://demoqa.com/automation-practice-form");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Check if some elements are visible on the form (for example, first name input field)
        assertTrue(driver.findElement(By.id("firstName")).isDisplayed(), "First Name field is not displayed");
        assertTrue(driver.findElement(By.id("lastName")).isDisplayed(), "Last Name field is not displayed");
        assertTrue(driver.findElement(By.id("userEmail")).isDisplayed(), "Email field is not displayed");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}