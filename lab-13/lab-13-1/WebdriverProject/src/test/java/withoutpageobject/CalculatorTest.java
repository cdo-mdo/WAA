package withoutpageobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
	private WebDriver driver;

	@Before
	public void createWebDriver() {
		// set path to chromedriver.exe
		System.setProperty("webdriver.chrome.driver", "E:\\tmp\\chromedriver-win64\\chromedriver.exe");
		// create chrome instance
//		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.setBinary("E:\\tmp\\chrome-headless-shell-win64\\chrome-headless-shell.exe");
		options.addArguments("--remote-allow-origins=*");
		// create chrome instance
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	}

	@After
	public void closeWebDriver() {
		driver.close();
	}

	@Test
	public void oneAndFour() {
		driver.navigate().to("http://www.rekenmachine-calculator.nl/");

		WebElement button = driver.findElement(By.name("one"));
		button.click();
		button = driver.findElement(By.name("add"));
		button.click();
		button = driver.findElement(By.name("four"));
		button.click();
		assertThat(driver.findElement(By.name("txt")).getAttribute("value"), is("1+4"));
		button = driver.findElement(By.name("equal"));
		button.click();
		assertThat(driver.findElement(By.name("txt")).getAttribute("value"), is("5"));
		button = driver.findElement(By.name("cancel"));
		button.click();
	}

	@Test
	public void twelveAndTortyFour() {
		driver.navigate().to("http://www.rekenmachine-calculator.nl/");

		WebElement button = driver.findElement(By.name("one"));
		button.click();
		button = driver.findElement(By.name("two"));
		button.click();
		button = driver.findElement(By.name("add"));
		button.click();
		button = driver.findElement(By.name("four"));
		button.click();
		button = driver.findElement(By.name("four"));
		button.click();
		assertThat(driver.findElement(By.name("txt")).getAttribute("value"), is("12+44"));
		button = driver.findElement(By.name("equal"));
		button.click();
		assertThat(driver.findElement(By.name("txt")).getAttribute("value"), is("56"));
	}
}
