package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Helper {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public Helper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Wait for element visibility
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for element clickability
    public WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Scroll to a specific element
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Scroll the page up
    public void scrollPageUp() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, -250);"); // Adjust the scroll value as needed
    }

    // Handle advertisement overlays
    public void handleAdOverlay() {
        try {
            WebElement adOverlay = driver.findElement(By.cssSelector("div[id^='google_ads_iframe']"));
            if (adOverlay.isDisplayed()) {
                driver.switchTo().frame(adOverlay);
                WebElement closeButton = driver.findElement(By.cssSelector("button[title='Close']"));
                closeButton.click();
                driver.switchTo().defaultContent();
            }
        } catch (Exception e) {
            // Log if no overlay was present
            System.out.println("No advertisement overlay found.");
        }
    }

    // Select a value from a dropdown by visible text
    public void selectFromDropdownByVisibleText(WebElement dropdown, String visibleText) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    // Select a value from a dropdown by index
    public void selectFromDropdownByIndex(WebElement dropdown, int index) {
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    // Select a value from a dropdown by value attribute
    public void selectFromDropdownByValue(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    // Execute JavaScript on an element
    public void executeJavaScript(String script, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(script, element);
    }

    // Generate a random alphanumeric string of a given length
    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }

    public String generateRandomNumber(int length) {
        String digits = "0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            result.append(digits.charAt(random.nextInt(digits.length())));
        }
        return result.toString();
    }


    // Generate a random date within the past year
    public String generateRandomDate() {
        long minDay = LocalDate.now().minusYears(1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}