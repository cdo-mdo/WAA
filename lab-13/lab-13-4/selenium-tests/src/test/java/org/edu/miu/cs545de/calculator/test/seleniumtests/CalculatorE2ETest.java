package org.edu.miu.cs545de.calculator.test.seleniumtests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.edu.miu.cs545de.calculator.test.seleniumtests.pages.CalculatorPage;
import org.edu.miu.cs545de.calculator.test.seleniumtests.pages.ResultPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorE2ETest {

    private WebDriver driver;

    // React app URL (make sure npm start is running!)
    private final String baseUrl = "http://localhost:3000";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Headless is optional. If you want to SEE the browser, comment next line out.
        options.addArguments("--headless=new");

        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void testAdd() {
        CalculatorPage calc = new CalculatorPage(driver).open(baseUrl);
        calc.setA("10").setB("3").clickAdd();

        ResultPage result = new ResultPage(driver);
        assertEquals("13", result.getResultText());
        assertTrue(result.getEquationText().contains("10"));
    }

    @Test
    void testSubtract() {
        CalculatorPage calc = new CalculatorPage(driver).open(baseUrl);
        calc.setA("10").setB("3").clickSub();

        ResultPage result = new ResultPage(driver);
        assertEquals("7", result.getResultText());
    }

    @Test
    void testMultiply() {
        CalculatorPage calc = new CalculatorPage(driver).open(baseUrl);
        calc.setA("10").setB("3").clickMul();

        ResultPage result = new ResultPage(driver);
        assertEquals("30", result.getResultText());
    }
}
