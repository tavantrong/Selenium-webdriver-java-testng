package Practice;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic08_Dynamic_Xpath_And_Locator {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/multiple-fields/");
	}

	private static final String DYNAMIC_CHECKBOX = "//label[contains(text(),'%s')]/preceding-sibling::input";
	private static final String DYNAMIC_LETTER_START_CHECKBOX = "//label[starts-with(text(),'%s')]/preceding-sibling::input";

	@Test
	public void TC01_Dynamic_Locator() {
		clickToElement(DYNAMIC_CHECKBOX, "Anemia");
		clickToElement(DYNAMIC_CHECKBOX, "Cancer");
		clickToElement(DYNAMIC_CHECKBOX, "Gout");
		sleepInSecond(3);
	}

	@Test
	public void TC02_Dynamic_Locator_And_Xpath() {
		driver.navigate().refresh();

		clickToOptionElements(DYNAMIC_LETTER_START_CHECKBOX, " H");
		sleepInSecond(2);
		clickToOptionElements(DYNAMIC_LETTER_START_CHECKBOX, " A");
		sleepInSecond(2);
		// Xpath không hỗ trợ Ends-with
	}

	
	public String getDynamicLocator(String locator, String... values) { 
		  return String.format(locator, (Object[])values); 
	}
	 
	public void clickToElement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void clickToElement(String locator, String... values) {
		WebElement element = driver.findElement(By.xpath(getDynamicLocator(locator, values)));
		element.click();
	}

	public void clickToOptionElements(String locator, String... values) {
		List<WebElement> elements = driver.findElements(By.xpath(getDynamicLocator(locator, values)));
		for (WebElement element : elements) {
			element.click();
		}
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
