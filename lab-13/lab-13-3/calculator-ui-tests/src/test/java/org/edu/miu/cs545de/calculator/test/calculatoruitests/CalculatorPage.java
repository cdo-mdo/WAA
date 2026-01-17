package org.edu.miu.cs545de.calculator.test.calculatoruitests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CalculatorPage {
    private final WebDriver driver;

    private final By inputA = By.id("input-a");
    private final By inputB = By.id("input-b");
    private final By addBtn = By.id("btn-add");
    private final By subBtn = By.id("btn-sub");
    private final By mulBtn = By.id("btn-mul");
    private final By clearBtn = By.id("btn-clear");
    private final By result = By.id("result");

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public CalculatorPage open(String baseUrl) {
        driver.get(baseUrl);
        return this;
    }

    public CalculatorPage setA(String value) {
        driver.findElement(inputA).clear();
        driver.findElement(inputA).sendKeys(value);
        return this;
    }

    public CalculatorPage setB(String value) {
        driver.findElement(inputB).clear();
        driver.findElement(inputB).sendKeys(value);
        return this;
    }

    public CalculatorPage clickAdd() {
        driver.findElement(addBtn).click();
        return this;
    }

    public CalculatorPage clickSubtract() {
        driver.findElement(subBtn).click();
        return this;
    }

    public CalculatorPage clickMultiply() {
        driver.findElement(mulBtn).click();
        return this;
    }

    public CalculatorPage clickClear() {
        driver.findElement(clearBtn).click();
        return this;
    }

    public String getResult() {
        return driver.findElement(result).getText().trim();
    }
}
