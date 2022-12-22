package api;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.condition.Condition;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic17_Wait_V_Explicit {
	// Static wait - Wait được sét cứng - Bắt buộc chờ hết time out - Step tiếp theo
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		explicitWait = new WebDriverWait(driver, 15);
		//Explicit wait là thư viện Java Thread (not Selenium) linh động với 2 case:
			// 1 - Wait 1 or nhiều Element đến khi hiển thị (visible) - Thấy - Step tiếp
			// 2 - Wait 1 or nhiều Element đến khi biến mất (invisible) - Biến mất - Step tiếp 
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		}

	@Test
	public void TC_01_Enough_Time() {
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		// Chờ cho đến khi Xpath element hiển thị, trong vòng 2s
		explicitWait = new WebDriverWait(driver, 2);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@text()='11']")));
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}

	
	public void TC_02_Less_Time() {
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		// Chờ cho đến khi Xpath element hiển thị, trong vòng 1s
		explicitWait = new WebDriverWait(driver, 1);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@text()='11']")));
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
		// Failed
	}
	
	public void TC_03_Greater_Time() {
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		// Chờ cho đến khi Xpath element hiển thị, trong vòng 10s
		explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@text()='11']")));
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}
	

	
	
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
