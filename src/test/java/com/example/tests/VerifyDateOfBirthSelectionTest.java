package com.example.tests;

import base.BaseTest;
import helpers.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PracticeFormPage;
import org.openqa.selenium.WebElement;

public class VerifyDateOfBirthSelectionTest extends BaseTest {
    @Test
    public void testDateOfBirthSelection() {
        String appUrl = getProperty("app.url");
        driver.get(appUrl);

        PracticeFormPage formPage = new PracticeFormPage(driver);
        Helper helper = new Helper(driver);

        String randomDateOfBirth = helper.generateRandomDate();
        formPage.enterDateOfBirth(randomDateOfBirth);

        WebElement submitButton = formPage.getSubmitButton();
        helper.scrollToElement(submitButton);
        helper.waitForClickability(submitButton).click();

        WebElement dateOfBirthField = helper.waitForVisibility(formPage.getDateOfBirthField());
        Assert.assertEquals(dateOfBirthField.getAttribute("value"), randomDateOfBirth,
                "Date of Birth does not match the randomly generated value.");
    }
}