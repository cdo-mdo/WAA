package org.edu.miu.cs545de.calculator.test.calculatoruitests;

import org.edu.miu.cs545de.calculator.test.calculatoruitests.CalculatorPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorUiTest {

    private WebDriver driver;
    private CalculatorPage page;

    // React dev server URL
    private final String BASE_URL = "http://localhost:3000";

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        page = new CalculatorPage(driver).open(BASE_URL);
    }

    @AfterEach
    void teardown() {
        if (driver != null) driver.quit();
    }

    @Test
    void testAdd() {
        page.setA("10").setB("5").clickAdd();
        assertEquals("15", page.getResult());
    }

    @Test
    void testSubtract() {
        page.setA("10").setB("5").clickSubtract();
        assertEquals("5", page.getResult());
    }

    @Test
    void testMultiply() {
        page.setA("10").setB("5").clickMultiply();
        assertEquals("50", page.getResult());
    }

    @Test
    void testClear() {
        page.setA("10").setB("5").clickAdd();
        page.clickClear();
        assertEquals("", page.getResult());
    }
}
