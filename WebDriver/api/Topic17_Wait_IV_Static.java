package api;
import org.testng.annotations.Test;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic17_Wait_IV_Static {
	// Static wait - Wait được sét cứng - Bắt buộc chờ hết time out - Step tiếp theo
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		}

	@Test
	public void TC_01_Enough_Time() {
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}

	
	public void TC_02_Less_Time() {
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		sleepInsecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
		//Failed vì không đủ time để load element
	}
	
	public void TC_03_Greater_Time() {
		driver.get("https://juliemr.github.io/protractor-demo/");
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		System.out.println(getDateTimeNow());
		sleepInsecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
		System.out.println(getDateTimeNow());
		//Passed - nhưng phải chờ dư 3s mới xong
	}
	

	
	
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	
	public void sleepInsecond(long timeout) {
		try {
		Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
