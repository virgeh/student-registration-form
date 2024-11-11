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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerifyAddressFieldsTest {

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
    public void testAddressFields() {
        // Verify Current Address field
        WebElement currentAddressField = driver.findElement(By.id("currentAddress"));
        scrollToElement(currentAddressField);
        String currentAddress = "123 Main Street, Springfield";
        currentAddressField.sendKeys(currentAddress);
        assertEquals(currentAddress, currentAddressField.getAttribute("value"), "Current Address should match the input value.");

        // Verify State dropdown (assuming it requires scrolling and interaction)
        WebElement stateDropdown = driver.findElement(By.id("state"));
        scrollToElement(stateDropdown);
        stateDropdown.click();
        WebElement selectState = driver.findElement(By.xpath("//div[contains(text(), 'NCR')]")); // Example: Selecting 'NCR'
        selectState.click();
        assertEquals("NCR", stateDropdown.getText(), "State should be set to 'NCR'.");

        // Verify City dropdown (assuming it requires scrolling and interaction)
        WebElement cityDropdown = driver.findElement(By.id("city"));
        scrollToElement(cityDropdown);
        cityDropdown.click();
        WebElement selectCity = driver.findElement(By.xpath("//div[contains(text(), 'Delhi')]")); // Example: Selecting 'Delhi'
        selectCity.click();
        assertEquals("Delhi", cityDropdown.getText(), "City should be set to 'Delhi'.");
    }

    /**
     * Utility method to scroll to an element.
     * @param element WebElement to be scrolled into view.
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