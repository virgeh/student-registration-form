package com.example.tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.PracticeFormPage;

import static org.testng.Assert.assertTrue;

public class VerifyHobbiesSelectionTest extends BaseTest {

    @Test
    public void testHobbiesSelection() {
        PracticeFormPage practiceFormPage = new PracticeFormPage(driver);

        // Select "Sports" checkbox
        practiceFormPage.selectHobby("Sports");
        assertTrue(practiceFormPage.isHobbySelected("Sports"), "Sports checkbox should be selected after clicking.");

        // Select "Reading" checkbox
        practiceFormPage.selectHobby("Reading");
        assertTrue(practiceFormPage.isHobbySelected("Reading"), "Reading checkbox should be selected after clicking.");

        // Select "Music" checkbox
        practiceFormPage.selectHobby("Music");
        assertTrue(practiceFormPage.isHobbySelected("Music"), "Music checkbox should be selected after clicking.");
    }
}