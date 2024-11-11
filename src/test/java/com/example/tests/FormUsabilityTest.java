package com.example.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormUsabilityTest {
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
    public void testFormUsability() {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate and interact with the first field
        WebElement firstField = driver.findElement(By.id("firstName"));
        actions.sendKeys(firstField, "John").sendKeys(Keys.TAB).perform();

        // Explicitly wait for the lastName field to be focused
        WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("lastName")));
        assertEquals(lastNameField.getAttribute("id"), "lastName", "Error: Incorrect field focus.");

        actions.sendKeys("Doe").sendKeys(Keys.TAB).perform();

        // Explicitly wait for the email field to be focused
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("userEmail")));
        assertEquals(emailField.getAttribute("id"), "userEmail", "Error: Incorrect field focus.");
    }
}