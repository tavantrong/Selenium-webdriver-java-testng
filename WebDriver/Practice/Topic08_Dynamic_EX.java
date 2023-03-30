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

public class Topic08_Dynamic_EX {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}

	@Test
	public void TC01_Dynamic_Locator() {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
			
		String first = driver.findElement(By.xpath("//div[@class='oxd-grid-3 orangehrm-full-width-grid']//input[@class='oxd-input oxd-input--active']")).getAttribute("placeholder");
		System.out.println("Display Text first: "+ first);
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
