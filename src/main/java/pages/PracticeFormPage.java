package pages;

import helpers.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class PracticeFormPage {
    private WebDriver driver;
    private Helper helper;
    // Web Elements
    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(id = "gender-radio-1")
    private WebElement maleGenderRadioButton;

    @FindBy(id = "gender-radio-2")
    private WebElement femaleGenderRadioButton;

    @FindBy(id = "gender-radio-3")
    private WebElement otherGenderRadioButton;

    @FindBy(id = "userNumber")
    private WebElement mobileNumberField;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthField;

    @FindBy(id = "subjectsInput")
    private WebElement subjectsField;

    @FindBy(id = "hobbies-checkbox-1")
    private WebElement hobbiesCheckbox;

    @FindBy(id = "uploadPicture")
    private WebElement uploadPictureButton;

    @FindBy(id = "currentAddress")
    private WebElement addressField;

    @FindBy(id = "state")
    private WebElement stateDropdown;

    @FindBy(id = "city")
    private WebElement cityDropdown;

    @FindBy(id = "submit")
    private WebElement submitButton;

    // Constructor
    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        this.helper = new Helper(driver); // Initialize Helper instance
        PageFactory.initElements(driver, this); // Initialize elements
    }

    // Methods to interact with form elements
    public void enterFirstName(String firstName) {
        helper.waitForVisibility(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        helper.waitForVisibility(lastNameField).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        helper.waitForVisibility(emailField).sendKeys(email);
    }

    public void selectMaleGender() {
        // Wait for the element to become visible
        helper.waitForVisibility(maleGenderRadioButton);

        // Scroll to the element to ensure it's in the viewport
        helper.scrollToElement(maleGenderRadioButton);

        // Attempt to click using WebDriver or JavaScript
        try {
            helper.waitForClickability(maleGenderRadioButton).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // Fallback to JavaScript click if intercepted
            helper.executeJavaScript("arguments[0].click();", maleGenderRadioButton);
        } catch (org.openqa.selenium.TimeoutException e) {
            throw new RuntimeException("Gender radio button is not clickable: Check for overlays or visibility issues.");
        }
    }

    public void enterMobileNumber(String mobileNumber) {
        helper.waitForVisibility(mobileNumberField).clear();
        mobileNumberField.sendKeys(mobileNumber);
    }
    public void enterDateOfBirth(String dateOfBirth) {
        helper.scrollToElement(dateOfBirthField);
        dateOfBirthField.click();
        dateOfBirthField.clear();
        dateOfBirthField.sendKeys(dateOfBirth);
        dateOfBirthField.sendKeys("\n");
    }

    public void enterSubjects(String subject) {
        helper.scrollToElement(subjectsField);
        subjectsField.sendKeys(subject);
        subjectsField.sendKeys("\n");
    }

    public void selectHobby() {
        helper.scrollToElement(hobbiesCheckbox);
        helper.waitForClickability(hobbiesCheckbox).click();
    }

    public void uploadPicture(String filePath) {
        helper.waitForVisibility(uploadPictureButton).sendKeys(filePath);
    }

    public void enterAddress(String address) {
        helper.scrollToElement(addressField);
        helper.waitForVisibility(addressField).clear();
        helper.waitForVisibility(addressField).sendKeys(address);
    }

    public void clickSubmitButton() {
        helper.handleAdOverlay(); // Handle any advertisement overlay
        helper.scrollToElement(submitButton);
        helper.waitForClickability(submitButton).click();
    }

    public void submitForm() {
        helper.scrollToElement(submitButton);
        helper.waitForClickability(submitButton).click();
    }

    public void selectRandomStateAndCity() {
        helper.scrollToElement(stateDropdown);
        stateDropdown.click();

        // Get all available states
        List<WebElement> states = driver.findElements(By.cssSelector("#state div"));
        WebElement randomState = states.get(new Random().nextInt(states.size()));
        String selectedState = randomState.getText();
        randomState.click();

        // Wait for city options to load after selecting a state
        helper.waitForVisibility(cityDropdown);
        cityDropdown.click();

        // Get all available cities for the selected state
        List<WebElement> cities = driver.findElements(By.cssSelector("#city div"));
        WebElement randomCity = cities.get(new Random().nextInt(cities.size()));
        String selectedCity = randomCity.getText();
        randomCity.click();

        // Logging for verification
        System.out.println("Selected State: " + selectedState);
        System.out.println("Selected City: " + selectedCity);

        // Store selected values for later verification
        this.selectedState = selectedState;
        this.selectedCity = selectedCity;
    }

    public void selectFemaleGender() {
        helper.waitForVisibility(femaleGenderRadioButton);
        helper.scrollToElement(femaleGenderRadioButton);

        try {
            helper.waitForClickability(femaleGenderRadioButton).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            helper.executeJavaScript("arguments[0].click();", femaleGenderRadioButton);
        }
    }

    public void selectOtherGender() {
        helper.waitForVisibility(otherGenderRadioButton);
        helper.scrollToElement(otherGenderRadioButton);

        try {
            helper.waitForClickability(otherGenderRadioButton).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            helper.executeJavaScript("arguments[0].click();", otherGenderRadioButton);
        }
    }

    public void selectHobby(String hobby) {
        WebElement hobbyCheckbox = driver.findElement(By.xpath("//label[contains(text(), '" + hobby + "')]/preceding-sibling::input"));
        helper.scrollToElement(hobbyCheckbox);
        if (!hobbyCheckbox.isSelected()) {
            hobbyCheckbox.click();
        }
    }

    private String selectedState;
    private String selectedCity;

    public String getSelectedAddress() {
        return helper.waitForVisibility(addressField).getAttribute("value").trim();
    }

    public String getSelectedState() {
        return selectedState;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public String getEnteredAddress() {
        return helper.waitForVisibility(addressField).getAttribute("value").trim();
    }

    public String getUploadedPicturePath() {
        return uploadPictureButton.getAttribute("value");
    }

    public WebElement getEmailField() {
        return emailField;
    }

    // Method to get the border color of the email field
    public String getEmailFieldBorderColor() {
        // Adjust CSS property if necessary (e.g., 'border-color' or 'outline-color')
        return helper.waitForVisibility(emailField).getCssValue("border-color");
    }

    public String getEmailFieldBorderColorWithRetries(int retries, int delayMillis) {
        for (int i = 0; i < retries; i++) {
            try {
                WebElement emailField = helper.waitForVisibility(this.emailField);
                String color = emailField.getCssValue("border-color");
                System.out.println("Retry " + (i + 1) + ": " + color);
                return color;
            } catch (Exception e) {
                System.out.println("Retrying to fetch border color...");
            }
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        throw new RuntimeException("Failed to fetch border color after retries.");
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getFirstNameField() {
        return firstNameField;
    }

    public WebElement getLastNameField() {
        return lastNameField;
    }

    public WebElement getMobileNumberField() {
        return mobileNumberField;
    }

    public WebElement getDateOfBirthField()  {
        return dateOfBirthField;
    }

    public WebElement getSubjectsField() {
        return subjectsField;
    }

    public WebElement getMaleGenderRadioButton() {
        return maleGenderRadioButton;
    }

    public WebElement getFemaleGenderRadioButton() {
        return femaleGenderRadioButton;
    }

    public WebElement getOtherGenderRadioButton() {
        return otherGenderRadioButton;
    }

    public WebElement getAddressField() {
        return addressField;
    }

    public WebElement getStateDropdown() {
        return stateDropdown;
    }

    public WebElement getCityDropdown() {
        return cityDropdown;
    }

    public Helper getHelper() {
        return helper;
    }

    // Method to compare RGB colors with tolerance
    // Method to compare RGB colors with tolerance
    public boolean colorsAreSimilar(String actualColor, String expectedColor) {
        String[] actualValues = actualColor.replace("rgb(", "").replace(")", "").split(", ");
        String[] expectedValues = expectedColor.replace("rgb(", "").replace(")", "").split(", ");

        for (int i = 0; i < 3; i++) {
            try {
                int actual = Integer.parseInt(actualValues[i].trim());
                int expected = Integer.parseInt(expectedValues[i].trim());

                // Check tolerance
                if (Math.abs(actual - expected) > 5) {
                    System.out.printf("Color mismatch at channel %d: actual=%d, expected=%d\n", i, actual, expected);
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid RGB value detected.");
                return false;
            }
        }

        return true;
    }


    // Method to validate if the email field border color matches the expected color
    public boolean isEmailFieldBorderColorCorrect(List<String> expectedColors) {
        String actualColor = getEmailFieldBorderColor();
        System.out.println("Actual border color: " + actualColor);

        for (String expectedColor : expectedColors) {
            if (colorsAreSimilar(actualColor, expectedColor)) {
                System.out.println("Border color matches expected color: " + expectedColor);
                return true;
            }
        }

        System.out.println("Border color did not match any expected value.");
        return false;
    }

    public boolean isHobbySelected(String hobby) {
        WebElement hobbyCheckbox = driver.findElement(By.xpath("//label[contains(text(), '" + hobby + "')]/preceding-sibling::input"));
        return hobbyCheckbox.isSelected();
    }
}