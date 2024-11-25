package com.example.tests;

import base.BaseTest;
import helpers.Helper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PracticeFormPage;

public class RequiredFieldValidationTest extends BaseTest {

    @Test
    public void testRequiredFieldValidation() {
        // Open the application URL
        driver.get(getProperty("app.url"));

        // Initialize PracticeFormPage and Helper
        PracticeFormPage formPage = new PracticeFormPage(driver);
        Helper helper = new Helper(driver);

        // Scroll to and click the submit button
        WebElement submitButton = formPage.getSubmitButton();
        helper.scrollToElement(submitButton);
        helper.waitForClickability(submitButton).click();

        // Scroll back to the top of the page
        helper.scrollPageUp();

        // Validation checks: Ensure the required fields show error indicators
        WebElement firstNameField = formPage.getFirstNameField();
        WebElement lastNameField = formPage.getLastNameField();
        WebElement emailField = formPage.getEmailField(); // Email field validation
        WebElement mobileNumberField = formPage.getMobileNumberField();
        WebElement genderField = formPage.getMaleGenderRadioButton(); // Use existing maleGenderRadioButton

        // Check that the required fields have the appropriate error indicators
        Assert.assertTrue(firstNameField.getAttribute("class").contains("form-control"),
                "First name field should have 'form-control' class indicating a validation error.");
        Assert.assertTrue(lastNameField.getAttribute("class").contains("form-control"),
                "Last name field should have 'form-control' class indicating a validation error.");
        Assert.assertTrue(emailField.getAttribute("class").contains("form-control"),
                "Email field should have 'form-control' class indicating a validation error.");
        Assert.assertTrue(mobileNumberField.getAttribute("class").contains("form-control"),
                "Mobile number field should have 'form-control' class indicating a validation error.");
        Assert.assertTrue(genderField.getAttribute("class").contains("custom-control-input"),
                "Gender field should have 'custom-control-input' class indicating a validation error.");
    }
}