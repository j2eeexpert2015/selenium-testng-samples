package org.seleniumsamples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

/**
 * Automation Practice Test Class
 * This class demonstrates accessing first 5-6 elements from Rahul Shetty Academy Practice Page
 */
public class AutomationPractice{
    
    private static final Logger logger = LoggerFactory.getLogger(AutomationPractice.class);
    private static final String BASE_URL = "https://rahulshettyacademy.com/AutomationPractice/";
    private WebDriver driver;
    private WebDriverWait wait;

    public static void main(String[] args) {
        AutomationPractice test = new AutomationPractice();
        test.runTest();
    }

    public void runTest() {
        try {
            setupDriver();
            navigateToPage();
            accessPageElements();
        } catch (Exception e) {
            logger.error("Test execution failed: {}", e.getMessage(), e);
        } finally {
            tearDown();
        }
    }

    private void setupDriver() {
        logger.info("Setting up WebDriver...");
        
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        logger.info("WebDriver setup completed successfully");
    }

    private void navigateToPage() {
        logger.info("Navigating to practice page: {}", BASE_URL);
        driver.get(BASE_URL);
        logger.info("Successfully navigated to practice page");
        logger.info("Page title: {}", driver.getTitle());
    }

    private void accessPageElements() {
        logger.info("Starting to access page elements...");
        
        // Element 1: Radio Button Example
        accessRadioButtons();
        
        // Element 2: Suggestion Class Example (Autocomplete)
        accessAutocompleteField();
        
        // Element 3: Dropdown Example
        accessDropdownExample();
        
        // Element 4: Checkbox Example
        accessCheckboxExample();
        
        // Element 5: Switch Window Example
        accessSwitchWindowExample();
        
        // Element 6: Switch Tab Example
        accessSwitchTabExample();
        
        logger.info("Completed accessing all page elements");
    }

    private void accessRadioButtons() {
        try {
            logger.info("Accessing Radio Button elements...");
            
            List<WebElement> radioButtons = driver.findElements(By.name("radioButton"));
            logger.info("Found {} radio buttons", radioButtons.size());
            
            for (int i = 0; i < radioButtons.size(); i++) {
                WebElement radioButton = radioButtons.get(i);
                String value = radioButton.getAttribute("value");
                logger.info("Radio button {}: value = {}, selected = {}", 
                           i + 1, value, radioButton.isSelected());
            }
            
            // Click on the first radio button
            if (!radioButtons.isEmpty()) {
                radioButtons.get(0).click();
                logger.info("Clicked on first radio button");
            }
            
        } catch (Exception e) {
            logger.error("Error accessing radio buttons: {}", e.getMessage());
        }
    }

    private void accessAutocompleteField() {
        try {
            logger.info("Accessing Autocomplete field...");
            
            WebElement autocompleteField = driver.findElement(By.id("autocomplete"));
            logger.info("Autocomplete field found - placeholder: {}", 
                       autocompleteField.getAttribute("placeholder"));
            
            // Type some text
            autocompleteField.sendKeys("India");
            logger.info("Typed 'India' in autocomplete field");
            
            Thread.sleep(2000); // Wait for suggestions
            
            // Clear the field
            autocompleteField.clear();
            logger.info("Cleared autocomplete field");
            
        } catch (Exception e) {
            logger.error("Error accessing autocomplete field: {}", e.getMessage());
        }
    }

    private void accessDropdownExample() {
        try {
            logger.info("Accessing Dropdown element...");
            
            WebElement dropdownElement = driver.findElement(By.id("dropdown-class-example"));
            Select dropdown = new Select(dropdownElement);
            
            logger.info("Dropdown found with {} options", dropdown.getOptions().size());
            
            // Log all options
            for (WebElement option : dropdown.getOptions()) {
                logger.info("Dropdown option: {}", option.getText());
            }
            
            // Select by visible text
            dropdown.selectByVisibleText("Option2");
            logger.info("Selected 'Option2' from dropdown");
            
            // Get currently selected option
            WebElement selectedOption = dropdown.getFirstSelectedOption();
            logger.info("Currently selected option: {}", selectedOption.getText());
            
        } catch (Exception e) {
            logger.error("Error accessing dropdown: {}", e.getMessage());
        }
    }

    private void accessCheckboxExample() {
        try {
            logger.info("Accessing Checkbox elements...");
            
            List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
            logger.info("Found {} checkboxes", checkboxes.size());
            
            for (int i = 0; i < Math.min(3, checkboxes.size()); i++) {
                WebElement checkbox = checkboxes.get(i);
                String name = checkbox.getAttribute("name");
                String value = checkbox.getAttribute("value");
                boolean isSelected = checkbox.isSelected();
                
                logger.info("Checkbox {}: name = {}, value = {}, selected = {}", 
                           i + 1, name, value, isSelected);
                
                // Toggle checkbox state
                checkbox.click();
                logger.info("Toggled checkbox {} - now selected: {}", i + 1, checkbox.isSelected());
            }
            
        } catch (Exception e) {
            logger.error("Error accessing checkboxes: {}", e.getMessage());
        }
    }

    private void accessSwitchWindowExample() {
        try {
            logger.info("Accessing Switch Window button...");
            
            WebElement openWindowButton = driver.findElement(By.id("openwindow"));
            logger.info("Switch Window button found - text: {}", openWindowButton.getText());
            logger.info("Button is enabled: {}", openWindowButton.isEnabled());
            logger.info("Button is displayed: {}", openWindowButton.isDisplayed());
            
            // Note: Not clicking to avoid opening new window in this demo
            logger.info("Switch Window button accessed successfully (not clicked to avoid new window)");
            
        } catch (Exception e) {
            logger.error("Error accessing switch window button: {}", e.getMessage());
        }
    }

    private void accessSwitchTabExample() {
        try {
            logger.info("Accessing Switch Tab button...");
            
            WebElement openTabButton = driver.findElement(By.id("opentab"));
            logger.info("Switch Tab button found - text: {}", openTabButton.getText());
            logger.info("Button is enabled: {}", openTabButton.isEnabled());
            logger.info("Button is displayed: {}", openTabButton.isDisplayed());
            
            // Note: Not clicking to avoid opening new tab in this demo
            logger.info("Switch Tab button accessed successfully (not clicked to avoid new tab)");
            
        } catch (Exception e) {
            logger.error("Error accessing switch tab button: {}", e.getMessage());
        }
    }

    private void tearDown() {
        if (driver != null) {
            logger.info("Closing browser and cleaning up...");
            driver.quit();
            logger.info("Browser closed successfully");
        }
    }
}