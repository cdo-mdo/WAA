package org.edu.miu.cs545de.calculator.test.seleniumtests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage {
    private final WebDriver driver;

    private final By result = By.cssSelector("[data-testid='result']");
    private final By equation = By.cssSelector("[data-testid='equation']");
    private final By back = By.cssSelector("[data-testid='btn-back']");

    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getResultText() {
        return driver.findElement(result).getText().trim();
    }

    public String getEquationText() {
        return driver.findElement(equation).getText().trim();
    }

    public void clickBack() {
        driver.findElement(back).click();
    }
}

