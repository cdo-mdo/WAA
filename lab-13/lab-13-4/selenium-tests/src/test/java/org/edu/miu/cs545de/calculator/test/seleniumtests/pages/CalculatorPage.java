package org.edu.miu.cs545de.calculator.test.seleniumtests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CalculatorPage {
    private final WebDriver driver;

    private final By inputA = By.cssSelector("[data-testid='input-a']");
    private final By inputB = By.cssSelector("[data-testid='input-b']");
    private final By btnAdd = By.cssSelector("[data-testid='btn-add']");
    private final By btnSub = By.cssSelector("[data-testid='btn-sub']");
    private final By btnMul = By.cssSelector("[data-testid='btn-mul']");

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public CalculatorPage open(String baseUrl) {
        driver.get(baseUrl + "/");
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

    public void clickAdd() { driver.findElement(btnAdd).click(); }
    public void clickSub() { driver.findElement(btnSub).click(); }
    public void clickMul() { driver.findElement(btnMul).click(); }
}
