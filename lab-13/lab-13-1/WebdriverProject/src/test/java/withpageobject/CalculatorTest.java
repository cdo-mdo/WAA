package withpageobject;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
	private static CalculatorPage page;

	@Before
	public void createWebDriver() {
		// set path to chromedriver.exe
		System.setProperty("webdriver.chrome.driver", "E:\\tmp\\chromedriver-win64\\chromedriver.exe");
	    page = PageFactory.initElements(new ChromeDriver(), CalculatorPage.class);
	    page.open("http://www.rekenmachine-calculator.nl/");
	  }

	  @AfterClass
	  public static void closeTheBrowser() {
	    page.close();
	  }

	  @Test
	  public void oneAndFour() {
	    page.clickOne();
	    page.clickAdd();
	    page.clickFour();
	    assertThat(page.getResult(),  is("1+4"));
	    page.clickEqual();
	    assertThat(page.getResult(),  is("5"));
	    page.clickClear();
	  }

	}