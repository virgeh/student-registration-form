package com.example.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class VerifyPictureUploadTest {
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
    public void testPictureUpload() {
        WebElement uploadButton = driver.findElement(By.id("uploadPicture"));

        // Specify the correct file path to the image
        String filePath = "C:\\Users\\Virge\\Desktop\\Virge1.png";

        uploadButton.sendKeys(filePath);

        // Verify upload (usually by checking if the filename appears)
        String uploadedFileName = uploadButton.getAttribute("value");
        assertTrue(uploadedFileName.contains("Virge1.png"), "Error: Picture upload failed.");
    }
}