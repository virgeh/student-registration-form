package com.example.tests;

import base.BaseTest;
import helpers.Helper;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.PracticeFormPage;

import static org.testng.Assert.assertTrue;

public class VerifySubjectsFieldTest extends BaseTest {
    @Test
    public void testSubjectsField() {
        String appUrl = getProperty("app.url");
        driver.get(appUrl);

        PracticeFormPage formPage = new PracticeFormPage(driver);
        Helper helper = new Helper(driver);

        String randomSubject = helper.generateRandomString(5); // Generate random subject
        formPage.enterSubjects(randomSubject);

        WebElement submitButton = formPage.getSubmitButton();
        helper.scrollToElement(submitButton);
        helper.waitForClickability(submitButton).click();

        WebElement subjectsElement = helper.waitForVisibility(formPage.getSubjectsField());
        assertTrue(subjectsElement.getAttribute("value").contains(randomSubject),
                "Random subject '" + randomSubject + "' not added.");
    }
}