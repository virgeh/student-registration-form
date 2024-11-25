package com.example.tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.PracticeFormPage;

import static org.testng.Assert.assertTrue;

public class VerifyPictureUploadTest extends BaseTest {

    @Test
    public void testPictureUpload() {
        PracticeFormPage practiceFormPage = new PracticeFormPage(driver);

        // Specify the correct file path to the image
        String filePath = "C:\\Users\\Virge\\Desktop\\Virge1.png";

        // Upload the picture
        practiceFormPage.uploadPicture(filePath);

        // Verify the upload (this could vary based on the website's response)
        String uploadedFilePath = practiceFormPage.getUploadedPicturePath();
        assertTrue(uploadedFilePath.contains("Virge1.png"), "Error: Picture upload failed.");
    }
}