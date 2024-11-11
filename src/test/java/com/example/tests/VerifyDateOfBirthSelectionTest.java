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

public class VerifyDateOfBirthSelectionTest {
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
    public void testDateOfBirthSelection() {
        String targetDay = "06";
        String targetMonth = "November";
        String targetYear = "2000";

        WebElement dateOfBirthInput = driver.findElement(By.id("dateOfBirthInput"));

        // Scroll to the date input field to ensure visibility
        scrollToElement(dateOfBirthInput);

        // Click to open the date picker
        dateOfBirthInput.click();

        // Select the year
        WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-select"));
        yearDropdown.sendKeys(targetYear);

        // Select the month
        WebElement monthDropdown = driver.findElement(By.className("react-datepicker__month-select"));
        monthDropdown.sendKeys(targetMonth);

        // Locate and select the target day
        WebElement dayElement = driver.findElement(By.xpath("//div[contains(@class, 'react-datepicker__day--0" + targetDay + "') and not(contains(@class, 'outside-month'))]"));
        dayElement.click();

        // Verify that the date input field now displays the selected date
        String selectedDate = dateOfBirthInput.getAttribute("value");
        assertTrue(selectedDate.contains(targetDay) && selectedDate.contains(targetMonth.substring(0, 3)), "Error: Date not selected correctly.");
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