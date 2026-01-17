package withpageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculatorPage {
	protected WebDriver driver;

	public CalculatorPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(name = "one")
	private WebElement oneButton;
	@FindBy(name = "two")
	private WebElement twoButton;
	@FindBy(name = "three")
	private WebElement threeButton;
	@FindBy(name = "four")
	private WebElement fourButton;
	@FindBy(name = "cancel")
	private WebElement clearButton;
	@FindBy(name = "add")
	private WebElement addButton;
	@FindBy(name = "sub")
	private WebElement subtractButton;
	@FindBy(name = "equal")
	private WebElement equalButton;
	@FindBy(name = "txt")
	private WebElement resultField;

	public void open(String url) {
		driver.get(url);
	}

	public void close() {
		driver.close();
	}

	public String clickOne() {
		oneButton.click();
		return resultField.getAttribute("value");
	}

	public String clickTwo() {
		twoButton.click();
		return resultField.getAttribute("value");
	}

	public String clickThree() {
		threeButton.click();
		return resultField.getAttribute("value");
	}

	public String clickFour() {
		fourButton.click();
		return resultField.getAttribute("value");
	}
	
	public void clickClear() {
		clearButton.click();
	}

	public String clickEqual() {
		equalButton.click();
		return resultField.getAttribute("value");
	}

	public String clickAdd() {
		addButton.click();
		return resultField.getAttribute("value");
	}
	public String clickSubtract() {
		subtractButton.click();
		return resultField.getAttribute("value");
	}
	public String getResult() {
		return resultField.getAttribute("value");
	}

}
