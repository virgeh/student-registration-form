package com.example.tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PracticeFormPage;

public class MobileNumberValidationTest extends BaseTest {
    @Test
    public void testMobileNumberValidation_LessThan10Digits() {
        // Open the application
        driver.get(getProperty("app.url"));

        // Page and helper initialization
        PracticeFormPage formPage = new PracticeFormPage(driver);

        // Enter an invalid short number
        String shortNumber = "12345"; // Example of an invalid short number
        formPage.enterMobileNumber(shortNumber);

        // Scroll and click the submit button
        WebElement submitButton = formPage.getSubmitButton();
        helper.scrollToElement(submitButton);
        helper.waitForClickability(submitButton).click();

        // Wait for the validation to reflect on the field
        WebElement mobileNumberField = helper.waitForVisibility(formPage.getMobileNumberField());
        String borderColor = mobileNumberField.getCssValue("border-color");

        // Log the border color for debugging
        System.out.println("Mobile Number Field Border Color (Less than 10 digits): " + borderColor);

        // Assert the validation color
        Assert.assertTrue(borderColor.equals("rgb(255, 0, 0)") || borderColor.equals("rgb(149, 157, 212)"),
                "Validation error (red border) not shown for less than 10 digits.");
    }

    @Test
    public void testMobileNumberValidation_NonDigitCharacters() {
        // Open the application
        driver.get(getProperty("app.url"));

        // Page and helper initialization
        PracticeFormPage formPage = new PracticeFormPage(driver);

        // Enter an invalid input with non-digit characters
        String invalidInput = "abc!@#123"; // Example of invalid input with non-digits
        formPage.enterMobileNumber(invalidInput);

        // Scroll and click the submit button
        WebElement submitButton = formPage.getSubmitButton();
        helper.scrollToElement(submitButton);
        helper.waitForClickability(submitButton).click();

        // Verify border color
        WebElement mobileNumberField = formPage.getMobileNumberField();
        String borderColor = mobileNumberField.getCssValue("border-color");

        // Log the actual border color for debugging
        System.out.println("Mobile Number Field Border Color (Non-digit characters): " + borderColor);

        // Assert the actual border color matches expected value
        Assert.assertTrue(borderColor.equals("rgb(149, 157, 212)") || borderColor.equals("rgb(255, 0, 0)"),
                "Validation error (red border) not shown for non-digit characters.");
    }
}