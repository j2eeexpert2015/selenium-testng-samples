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
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

/**
 * Automation Practice Test Class with TestNG
 * This class demonstrates accessing first 5-6 elements from Rahul Shetty Academy Practice Page
 * using proper TestNG test methods with setup and teardown
 */
public class AutomationPracticeTest {
    
    private static final Logger logger = LoggerFactory.getLogger(AutomationPracticeTest.class);
    private static final String BASE_URL = "https://rahulshettyacademy.com/AutomationPractice/";
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setupClass() {
        logger.info("=== Starting Test Suite Setup ===");
        setupDriver();
        navigateToPage();
        logger.info("=== Test Suite Setup Completed ===");
    }

    @AfterClass
    public void tearDownClass() {
        logger.info("=== Starting Test Suite Teardown ===");
        tearDown();
        logger.info("=== Test Suite Teardown Completed ===");
    }

    @BeforeMethod
    public void beforeEachTest() {
        logger.info("--- Starting individual test method ---");
    }

    @AfterMethod
    public void afterEachTest() {
        logger.info("--- Completed individual test method ---");
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
        String pageTitle = driver.getTitle();
        logger.info("Successfully navigated to practice page");
        logger.info("Page title: {}", pageTitle);
        
        // Assert page is loaded correctly
        Assert.assertTrue(pageTitle.contains("Practice"), "Page title should contain 'Practice'");
    }

    @Test(priority = 1, description = "Test Radio Button functionality")
    public void testRadioButtons() {
        logger.info("=== TEST: Radio Button Elements ===");
        
        logger.info("Before: Accessing Radio Button elements...");
        List<WebElement> radioButtons = driver.findElements(By.name("radioButton"));
        logger.info("Before: Found {} radio buttons", radioButtons.size());
        
        Assert.assertTrue(radioButtons.size() > 0, "Radio buttons should be present on the page");
        
        for (int i = 0; i < radioButtons.size(); i++) {
            WebElement radioButton = radioButtons.get(i);
            String value = radioButton.getAttribute("value");
            boolean isSelected = radioButton.isSelected();
            logger.info("Before: Radio button {}: value = {}, selected = {}", 
                       i + 1, value, isSelected);
        }
        
        // Test clicking the first radio button
        if (!radioButtons.isEmpty()) {
            String firstRadioValue = radioButtons.get(0).getAttribute("value");
            radioButtons.get(0).click();
            
            logger.info("After: Clicked on first radio button with value: {}", firstRadioValue);
            Assert.assertTrue(radioButtons.get(0).isSelected(), 
                            "First radio button should be selected after clicking");
            logger.info("After: First radio button is now selected: {}", 
                       radioButtons.get(0).isSelected());
        }
        
        logger.info("=== COMPLETED: Radio Button Test ===");
    }

    @Test(priority = 2, description = "Test Autocomplete field functionality")
    public void testAutocompleteField() throws InterruptedException {
        logger.info("=== TEST: Autocomplete Field ===");
        
        logger.info("Before: Accessing Autocomplete field...");
        WebElement autocompleteField = driver.findElement(By.id("autocomplete"));
        String placeholder = autocompleteField.getAttribute("placeholder");
        String initialValue = autocompleteField.getAttribute("value");
        
        logger.info("Before: Autocomplete field found - placeholder: {}, initial value: '{}'", 
                   placeholder, initialValue);
        
        Assert.assertTrue(autocompleteField.isDisplayed(), "Autocomplete field should be visible");
        Assert.assertTrue(autocompleteField.isEnabled(), "Autocomplete field should be enabled");
        
        // Test typing in autocomplete field
        String testInput = "India";
        autocompleteField.sendKeys(testInput);
        logger.info("After: Typed '{}' in autocomplete field", testInput);
        
        Thread.sleep(2000); // Wait for suggestions to appear
        
        String currentValue = autocompleteField.getAttribute("value");
        Assert.assertEquals(currentValue, testInput, "Field should contain the typed text");
        logger.info("After: Current field value: '{}'", currentValue);
        
        // Clear the field
        autocompleteField.clear();
        String clearedValue = autocompleteField.getAttribute("value");
        logger.info("After: Cleared autocomplete field, current value: '{}'", clearedValue);
        
        Assert.assertTrue(clearedValue.isEmpty(), "Field should be empty after clearing");
        
        logger.info("=== COMPLETED: Autocomplete Field Test ===");
    }

    @Test(priority = 3, description = "Test Dropdown selection functionality")
    public void testDropdownExample() {
        logger.info("=== TEST: Dropdown Selection ===");
        
        logger.info("Before: Accessing Dropdown element...");
        WebElement dropdownElement = driver.findElement(By.id("dropdown-class-example"));
        Select dropdown = new Select(dropdownElement);
        
        int optionsCount = dropdown.getOptions().size();
        logger.info("Before: Dropdown found with {} options", optionsCount);
        
        Assert.assertTrue(optionsCount > 0, "Dropdown should have options");
        
        // Log all available options
        for (int i = 0; i < dropdown.getOptions().size(); i++) {
            WebElement option = dropdown.getOptions().get(i);
            logger.info("Before: Dropdown option {}: '{}'", i, option.getText());
        }
        
        // Get initial selection
        WebElement initialSelection = dropdown.getFirstSelectedOption();
        String initialText = initialSelection.getText();
        logger.info("Before: Initially selected option: '{}'", initialText);
        
        // Select a different option
        String optionToSelect = "Option2";
        dropdown.selectByVisibleText(optionToSelect);
        logger.info("After: Selected '{}' from dropdown", optionToSelect);
        
        // Verify selection
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        String selectedText = selectedOption.getText();
        logger.info("After: Currently selected option: '{}'", selectedText);
        
        Assert.assertEquals(selectedText, optionToSelect, 
                          "Selected option should match the expected option");
        
        logger.info("=== COMPLETED: Dropdown Selection Test ===");
    }

    @Test(priority = 4, description = "Test Checkbox functionality")
    public void testCheckboxExample() {
        logger.info("=== TEST: Checkbox Elements ===");
        
        logger.info("Before: Accessing Checkbox elements...");
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        int checkboxCount = checkboxes.size();
        logger.info("Before: Found {} checkboxes", checkboxCount);
        
        Assert.assertTrue(checkboxCount > 0, "Checkboxes should be present on the page");
        
        // Test first 3 checkboxes
        int testCount = Math.min(3, checkboxCount);
        for (int i = 0; i < testCount; i++) {
            WebElement checkbox = checkboxes.get(i);
            String name = checkbox.getAttribute("name");
            String value = checkbox.getAttribute("value");
            boolean initialState = checkbox.isSelected();
            
            logger.info("Before: Checkbox {}: name = '{}', value = '{}', selected = {}", 
                       i + 1, name, value, initialState);
            
            Assert.assertTrue(checkbox.isDisplayed(), 
                            String.format("Checkbox %d should be visible", i + 1));
            Assert.assertTrue(checkbox.isEnabled(), 
                            String.format("Checkbox %d should be enabled", i + 1));
            
            // Toggle checkbox state
            checkbox.click();
            boolean newState = checkbox.isSelected();
            logger.info("After: Toggled checkbox {} - now selected: {}", i + 1, newState);
            
            // Verify the state changed
            Assert.assertNotEquals(initialState, newState, 
                                 String.format("Checkbox %d state should have changed after click", i + 1));
        }
        
        logger.info("=== COMPLETED: Checkbox Elements Test ===");
    }

    @Test(priority = 5, description = "Test Switch Window button properties")
    public void testSwitchWindowExample() {
        logger.info("=== TEST: Switch Window Button ===");
        
        logger.info("Before: Accessing Switch Window button...");
        WebElement openWindowButton = driver.findElement(By.id("openwindow"));
        
        String buttonText = openWindowButton.getText();
        boolean isEnabled = openWindowButton.isEnabled();
        boolean isDisplayed = openWindowButton.isDisplayed();
        String buttonClass = openWindowButton.getAttribute("class");
        
        logger.info("Before: Switch Window button found:");
        logger.info("Before: - Text: '{}'", buttonText);
        logger.info("Before: - Enabled: {}", isEnabled);
        logger.info("Before: - Displayed: {}", isDisplayed);
        logger.info("Before: - CSS Class: '{}'", buttonClass);
        
        // Assertions
        Assert.assertTrue(isDisplayed, "Switch Window button should be visible");
        Assert.assertTrue(isEnabled, "Switch Window button should be enabled");
        Assert.assertFalse(buttonText.isEmpty(), "Switch Window button should have text");
        
        // Get current window handle count before potential click
        int initialWindowCount = driver.getWindowHandles().size();
        logger.info("Before: Current number of browser windows: {}", initialWindowCount);
        
        // Note: Not clicking to avoid complications with new windows in this demo
        logger.info("After: Switch Window button accessed and validated successfully");
        logger.info("After: (Button not clicked to avoid new window management in demo)");
        
        logger.info("=== COMPLETED: Switch Window Button Test ===");
    }

    @Test(priority = 6, description = "Test Switch Tab button properties")
    public void testSwitchTabExample() {
        logger.info("=== TEST: Switch Tab Button ===");
        
        logger.info("Before: Accessing Switch Tab button...");
        WebElement openTabButton = driver.findElement(By.id("opentab"));
        
        String buttonText = openTabButton.getText();
        boolean isEnabled = openTabButton.isEnabled();
        boolean isDisplayed = openTabButton.isDisplayed();
        String buttonClass = openTabButton.getAttribute("class");
        
        logger.info("Before: Switch Tab button found:");
        logger.info("Before: - Text: '{}'", buttonText);
        logger.info("Before: - Enabled: {}", isEnabled);
        logger.info("Before: - Displayed: {}", isDisplayed);
        logger.info("Before: - CSS Class: '{}'", buttonClass);
        
        // Assertions
        Assert.assertTrue(isDisplayed, "Switch Tab button should be visible");
        Assert.assertTrue(isEnabled, "Switch Tab button should be enabled");
        Assert.assertFalse(buttonText.isEmpty(), "Switch Tab button should have text");
        
        // Get current window handle count before potential click
        int initialTabCount = driver.getWindowHandles().size();
        logger.info("Before: Current number of browser tabs: {}", initialTabCount);
        
        // Note: Not clicking to avoid complications with new tabs in this demo
        logger.info("After: Switch Tab button accessed and validated successfully");
        logger.info("After: (Button not clicked to avoid new tab management in demo)");
        
        logger.info("=== COMPLETED: Switch Tab Button Test ===");
    }

    private void tearDown() {
        if (driver != null) {
            logger.info("Closing browser and cleaning up...");
            driver.quit();
            logger.info("Browser closed successfully");
        }
    }
}