package com.example.tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PracticeFormPage;

import java.util.Arrays;
import java.util.List;

public class EmailFieldValidationTest extends BaseTest {
    @Test
    public void testEmailFieldValidation() {
        // Navigate to the application
        driver.get(getProperty("app.url"));
        PracticeFormPage formPage = new PracticeFormPage(driver);

        // Enter an invalid email and submit the form
        formPage.enterEmail("invalid-email");
        formPage.clickSubmitButton();

        // Possible border colors
        List<String> expectedBorderColors = Arrays.asList("rgb(220, 53, 69)", "rgb(255, 0, 0)", "rgb(128, 189, 255)");

        // Get the actual border color
        String actualBorderColor = formPage.getEmailFieldBorderColor();

        // Check if the actual color matches any of the expected colors
        boolean isColorValid = expectedBorderColors.stream()
                .anyMatch(expectedColor -> formPage.colorsAreSimilar(actualBorderColor, expectedColor));

        // Assert the result
        Assert.assertTrue(isColorValid, "Error: Email field border color is not within the expected tolerance.");
    }
}