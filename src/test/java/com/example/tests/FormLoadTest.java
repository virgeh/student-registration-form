package com.example.tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PracticeFormPage;

public class FormLoadTest extends BaseTest {
    @Test
    public void testFormLoad() {
        String appUrl = getProperty("app.url");
        driver.get(appUrl);

        PracticeFormPage formPage = new PracticeFormPage(driver);

        Assert.assertTrue(formPage.getFirstNameField().isDisplayed(), "First Name field is not displayed");
        Assert.assertTrue(formPage.getLastNameField().isDisplayed(), "Last Name field is not displayed");
        Assert.assertTrue(formPage.getEmailField().isDisplayed(), "Email field is not displayed");
    }
}