package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationModal {
    private WebDriver driver;

    // Web Elements
    @FindBy(css = ".modal-header .modal-title")
    private WebElement confirmationTitle;

    @FindBy(css = ".modal-body")
    private WebElement modalBody;

    // Constructor
    public ConfirmationModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods to interact with modal
    public String getConfirmationTitle() {
        return confirmationTitle.getText();
    }

    public String getModalBodyText() {
        return modalBody.getText();
    }
}