package com.example.tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.PracticeFormPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class VerifyGenderSelectionTest extends BaseTest {

    @Test
    public void testGenderSelection() {
        PracticeFormPage formPage = new PracticeFormPage(driver);

        // Select "Male" option and verify it is selected
        formPage.selectMaleGender();
        assertTrue(formPage.getMaleGenderRadioButton().isSelected(), "Error: Male option not selected.");

        // Select "Female" option and verify it is selected
        formPage.selectFemaleGender(); // Add a method in PracticeFormPage to handle "Female" selection
        assertTrue(formPage.getFemaleGenderRadioButton().isSelected(), "Error: Female option not selected.");
        assertFalse(formPage.getMaleGenderRadioButton().isSelected(), "Error: Multiple gender options selected.");

        // Verify "Other" is not selected
        assertFalse(formPage.getOtherGenderRadioButton().isSelected(), "Error: 'Other' option should not be selected.");
    }
}