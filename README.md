# Selenium TestNG Automation Practice

A Selenium WebDriver automation project using TestNG framework to test web elements on [Rahul Shetty Academy Practice Page](https://rahulshettyacademy.com/AutomationPractice/).

## Project Structure

```
selenium-testng-samples/
├── src/main/java/org/seleniumsamples/
│   └── AutomationPractice.java              # Standalone execution class
├── src/test/java/org/seleniumsamples/
│   └── AutomationPracticeTest.java          # TestNG test class
├── src/main/resources/
│   ├── log4j2.xml                          # Logging configuration  
│   └── testng.xml                          # TestNG suite file
└── pom.xml                                 # Maven dependencies
```

## TestNG Test Class Details

### AutomationPracticeTest.java Structure

#### TestNG Annotations Used

```java
@BeforeClass    // Runs once before all test methods - Setup WebDriver
@AfterClass     // Runs once after all test methods - Cleanup WebDriver  
@BeforeMethod   // Runs before each test method - Pre-test logging
@AfterMethod    // Runs after each test method - Post-test logging
@Test(priority = X, description = "...")  // Individual test methods
```

#### Test Methods with Priorities

| Priority | Method Name | Description |
|----------|-------------|-------------|
| 1 | `testRadioButtons()` | Tests radio button selection and validation |
| 2 | `testAutocompleteField()` | Tests autocomplete input field functionality |
| 3 | `testDropdownExample()` | Tests dropdown selection and option validation |
| 4 | `testCheckboxExample()` | Tests checkbox toggle and state verification |
| 5 | `testSwitchWindowExample()` | Tests window button properties (doesn't open new window) |
| 6 | `testSwitchTabExample()` | Tests tab button properties (doesn't open new tab) |

#### Key TestNG Features Demonstrated

- **Priority-based execution**: Tests run in numerical order (1→6)
- **Assertions**: Uses `Assert.assertTrue()`, `Assert.assertEquals()` for validation
- **Test independence**: Each test can run standalone
- **Comprehensive logging**: Before/After state logging for debugging
- **Exception handling**: Proper error logging and test failure reporting

## Running Tests in Eclipse

### Prerequisites
- Eclipse IDE with TestNG plugin installed
- Java 11+ configured in Eclipse
- Maven integration (m2e plugin)

### Step 1: Import Project
1. Open Eclipse
2. `File` → `Import` → `Existing Maven Projects`
3. Browse to project folder and click `Finish`
4. Wait for Maven to download dependencies

### Step 2: Install TestNG Plugin (if not installed)
1. `Help` → `Eclipse Marketplace`
2. Search for "TestNG"
3. Install "TestNG for Eclipse" plugin
4. Restart Eclipse

### Step 3: Running Individual Test Methods

#### Method 1: Right-click on Test Method
1. Open `AutomationPracticeTest.java`
2. Right-click on any `@Test` method (e.g., `testRadioButtons()`)
3. Select `Run As` → `TestNG Test`

#### Method 2: Right-click on Test Class  
1. Right-click on `AutomationPracticeTest.java` in Package Explorer
2. Select `Run As` → `TestNG Test`
3. All test methods will run in priority order

### Step 4: Running via TestNG Suite File

#### Method 1: Run testng.xml
1. Right-click on `src/main/resources/testng.xml`
2. Select `Run As` → `TestNG Suite`

#### Method 2: Configure Run Configuration
1. `Run` → `Run Configurations`
2. Right-click `TestNG` → `New Configuration`
3. **Project**: Select your project
4. **Suite**: Browse and select `testng.xml`
5. Click `Run`

### Step 5: Running Specific Test Methods

#### Using TestNG Groups (if you add groups)
1. Add group to test method:
   ```java
   @Test(priority = 1, groups = {"smoke"})
   public void testRadioButtons() { ... }
   ```
2. Right-click class → `Run As` → `TestNG Test`
3. In Run Configuration, specify groups to include/exclude

#### Running Single Method
1. Place cursor inside test method
2. `Ctrl+F11` (Run) or `F11` (Debug)
3. Eclipse will run only that method

### Step 6: Viewing Test Results

#### TestNG Results Tab
- **Green**: Passed tests
- **Red**: Failed tests  
- **Yellow**: Skipped tests
- Click on any test to see details/stack trace

#### Console Output
- Detailed logging from Log4j2
- WebDriver actions and assertions
- Error messages and stack traces

### Step 7: Debug Mode Execution

#### Debug Individual Test
1. Set breakpoints by double-clicking line numbers
2. Right-click test method → `Debug As` → `TestNG Test`
3. Use Debug perspective to step through code

#### Debug Configuration
1. `Run` → `Debug Configurations`
2. Create new TestNG Debug configuration
3. Set breakpoints in test methods
4. Step through test execution line by line

## Common Eclipse Issues & Solutions

### Issue: TestNG not recognized
**Solution**: Install TestNG plugin from Eclipse Marketplace

### Issue: Maven dependencies not resolved
**Solution**: 
1. Right-click project → `Maven` → `Reload Projects`
2. Or `Alt+F5` → Check "Force Update" → OK

### Issue: Tests not running in priority order  
**Solution**: Ensure TestNG suite configuration preserves order in `testng.xml`

### Issue: ChromeDriver issues
**Solution**: WebDriverManager handles this automatically. Check console for specific errors.

## Test Execution Output Example

```
=== Starting Test Suite Setup ===
Setting up WebDriver...
WebDriver setup completed successfully
Navigating to practice page: https://rahulshettyacademy.com/AutomationPractice/
=== TEST: Radio Button Elements ===
Before: Found 3 radio buttons  
After: Clicked on first radio button with value: radio1
=== COMPLETED: Radio Button Test ===
```

## Maven Commands (Alternative to Eclipse)

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=AutomationPracticeTest

# Run specific test method  
mvn test -Dtest=AutomationPracticeTest#testRadioButtons
```

## Dependencies

- **Java**: 11
- **Selenium WebDriver**: 4.15.0  
- **TestNG**: 7.8.0
- **WebDriverManager**: 5.6.2 (automatic ChromeDriver management)
- **Log4j2**: 2.21.1