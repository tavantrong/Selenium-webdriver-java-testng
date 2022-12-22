package api;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic17_Wait_III_FindElement_Exercise {
	//Biến toàn cục
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		}

	
	public void TC_01_Dont_Set_Implicit() {
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
		// Failed - expected [11] but found [.] - Do element loading hiện chưa đầy đủ
		
	}

	
	public void TC_02_Invalid_Locator() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
		//Failed - Tìm được và lấy giá trị ngay thời điểm thấy nhưng không đúng (sai Locator)
	}
	
	public void TC_03_Valid_Locator() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding' and text()='11']")).getText(), "11");
		// Pass - Sau khi click - chờ đúng giá trị Element Xpath - Qua step tiếp theo
	}
	@Test
	public void TC_04_Valid_Locator_But_Invalid_Implicit() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding' and text()='11']")).getText(), "11");
		//Failed - 1s timeout không đủ để hiện Element xpath (tối đa 2-3s) 
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
