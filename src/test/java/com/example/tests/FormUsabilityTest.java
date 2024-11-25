package com.example.tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.PracticeFormPage;

public class FormUsabilityTest extends BaseTest {

    @Test
    public void testFormUsability() {
        driver.get(getProperty("app.url")); // Load the application URL from configuration

        // Initialize PracticeFormPage
        PracticeFormPage formPage = new PracticeFormPage(driver);

        // Generate random strings for form fields
        String randomFirstName = helper.generateRandomString(8); // Random string of 8 characters
        String randomLastName = helper.generateRandomString(10); // Random string of 10 characters
        String randomEmail = randomFirstName.toLowerCase() + "@example.com"; // Dynamic email generation

        // Enter First Name and Tab to Next Field
        WebElement firstNameField = formPage.getFirstNameField();
        helper.waitForVisibility(firstNameField);
        firstNameField.sendKeys(randomFirstName);
        firstNameField.sendKeys(Keys.TAB);

        // Validate Focus on Last Name Field
        WebElement lastNameField = formPage.getLastNameField();
        helper.waitForVisibility(lastNameField);
        Assert.assertEquals(lastNameField.getAttribute("id"), "lastName", "Error: Incorrect field focus.");

        // Enter Last Name and Tab to Email Field
        lastNameField.sendKeys(randomLastName);
        lastNameField.sendKeys(Keys.TAB);

        // Validate Focus on Email Field
        WebElement emailField = formPage.getEmailField();
        helper.waitForVisibility(emailField);
        Assert.assertEquals(emailField.getAttribute("id"), "userEmail", "Error: Incorrect field focus.");

        // Enter Random Email
        emailField.sendKeys(randomEmail);
    }
}