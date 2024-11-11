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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormSubmissionTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Set up ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Virge\\Desktop\\Woman go Tech\\Dishaga kohtumised\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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
    public void testFormSubmission() {
        // Fill out form fields
        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("lastName")).sendKeys("Doe");
        driver.findElement(By.id("userEmail")).sendKeys("john.doe@example.com");
        driver.findElement(By.id("userNumber")).sendKeys("1234567890");

        // Select gender radio button via JavaScript
        WebElement genderRadio = driver.findElement(By.cssSelector("input[id='gender-radio-1']"));
        clickUsingJavaScript(genderRadio);

        // Set Date of Birth directly
        setDateOfBirth();

        // Scroll to submit button to ensure it's in view
        WebElement submitButton = driver.findElement(By.id("submit"));
        scrollToElement(submitButton);

        // Attempt to click submit with handling for ads or overlays
        tryClickSubmitWithRetry(submitButton);

        // Verify that the confirmation modal appears and contains expected data
        verifyConfirmationModal();
    }

    public void setDateOfBirth() {
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

    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void clickUsingJavaScript(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    private void tryClickSubmitWithRetry(WebElement submitButton) {
        // Try clicking the submit button and handle any click interception due to ads
        int retryCount = 3;
        for (int i = 0; i < retryCount; i++) {
            try {
                clickUsingJavaScript(submitButton);
                break; // Exit loop if successful
            } catch (ElementClickInterceptedException e) {
                System.out.println("Click intercepted, attempting to close any visible ads...");
                closeAdIfPresent();
            }
        }
    }

    private void closeAdIfPresent() {
        // Check if there's an iframe with an ad and close it if possible
        try {
            driver.switchTo().defaultContent(); // Ensure we're in main content
            WebElement adIframe = driver.findElement(By.cssSelector("iframe[id^='google_ads_iframe']"));
            driver.switchTo().frame(adIframe); // Switch to ad iframe

            // Look for a close button inside the iframe
            WebElement closeButton = driver.findElement(By.cssSelector("button[title='Close']"));
            closeButton.click();

            driver.switchTo().defaultContent(); // Return to main content
        } catch (NoSuchElementException | NoSuchFrameException e) {
            System.out.println("Ad not found or no close button available.");
        }
    }

    private void verifyConfirmationModal() {
        // Wait for the confirmation modal to appear
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));

        // Check if modal contains the expected text
        String modalHeader = modal.findElement(By.className("modal-title")).getText();
        assertTrue(modalHeader.contains("Thanks for submitting the form"), "Modal header text is incorrect.");

        // Validate Student Name and Date of Birth
        String studentName = modal.findElement(By.xpath("//td[text()='Student Name']/following-sibling::td")).getText();
        String dateOfBirth = modal.findElement(By.xpath("//td[text()='Date of Birth']/following-sibling::td")).getText();

        assertEquals("John Doe", studentName, "Student Name does not match expected value.");
        assertEquals("06 November,2000", dateOfBirth, "Date of Birth does not match expected value.");
    }
}