package base;

import helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Helper helper;
    protected Properties properties = new Properties();

    // Constructor to load configurations
    public BaseTest() {
        loadConfigurations();
    }

    private void loadConfigurations() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("config.properties file not found in the classpath");
            }
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error loading configuration file: " + e.getMessage(), e);
        }
    }

    // Get property value or default
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    @BeforeMethod
    public void setUp() {
        String browser = getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("chrome")) {
            // Dynamically fetch the path for ChromeDriver
            String chromeDriverPath = getClass().getClassLoader().getResource("drivers/chromedriver.exe").getPath();
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            // Dynamically fetch the path for GeckoDriver
            String geckoDriverPath = getClass().getClassLoader().getResource("drivers/geckodriver.exe").getPath();
            System.setProperty("webdriver.gecko.driver", geckoDriverPath);
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(getProperty("timeout", "30"))));
        driver.manage().window().maximize();
        helper = new Helper(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}