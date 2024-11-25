package com.example.tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.PracticeFormPage;
import helpers.Helper;

import static org.testng.Assert.*;

public class VerifyAddressFieldsTest extends BaseTest {

    @Test
    public void testAddressFields() {
        PracticeFormPage formPage = new PracticeFormPage(driver);
        Helper helper = new Helper(driver);

        // Generate random address
        String randomAddress = helper.generateRandomString(20) + ", " + helper.generateRandomString(10);
        formPage.enterAddress(randomAddress);

        // Select random state and city
        formPage.selectRandomStateAndCity();

        // Submit the form
        formPage.submitForm();

        // Validate entered and selected values
        assertEquals(formPage.getEnteredAddress(), randomAddress, "Address mismatch.");
        assertNotNull(formPage.getSelectedState(), "State was not selected.");
        assertNotNull(formPage.getSelectedCity(), "City was not selected.");
    }
}