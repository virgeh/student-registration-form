package com.example.tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PracticeFormPage;
import pages.ConfirmationModal;

public class FormSubmissionTest extends BaseTest {
        @Test
        public void testFormSubmission() {
            driver.get(getProperty("app.url"));

            PracticeFormPage formPage = new PracticeFormPage(driver);

            // Fill out the form with random data
            formPage.enterFirstName(helper.generateRandomString(8));
            formPage.enterLastName(helper.generateRandomString(8));
            formPage.enterEmail(helper.generateRandomString(5) + "@example.com");
            formPage.enterMobileNumber(helper.generateRandomNumber(10));
            formPage.selectMaleGender();
            formPage.enterDateOfBirth(helper.generateRandomDate());
            formPage.enterSubjects("Maths");
            formPage.selectHobby();
            formPage.uploadPicture("C:\\Users\\Virge\\Pictures\\profile.jpg");
            formPage.enterAddress("123 Main Street");

            // Submit the form
            helper.scrollToElement(formPage.getSubmitButton());
            formPage.clickSubmitButton();

            // Interact with the confirmation modal
            ConfirmationModal confirmationModal = new ConfirmationModal(driver);
            String actualTitle = confirmationModal.getConfirmationTitle();
            String actualBody = confirmationModal.getModalBodyText();

            // Assert confirmation modal content
            Assert.assertEquals(actualTitle, "Thanks for submitting the form", "Modal title does not match.");
            Assert.assertTrue(actualBody.contains("Form successfully submitted!"), "Modal body text is incorrect.");
        }
    }
