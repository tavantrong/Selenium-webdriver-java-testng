package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Topic05_Browser_Element_Part_I {
	WebDriver driver;

	@BeforeTest
	  public void beforeTest() {
		  driver = new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
	}
	
	
	public void TC01_isDisplayed() {
		
	driver.get("https://automationfc.github.io/basic-form/");
	
	//Kiem tra hien thi cac phan tu
	Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed());
	
	//Get console display Radio button Age under 18
		boolean RadioAgeunder18Status = driver.findElement(By.cssSelector("#under_18")).isDisplayed();
		if (RadioAgeunder18Status == true) {
			System.out.println("Age under 18 radio button is Displayed");
		}
		else {
			System.out.println("Age under 18 radio button is NOT displayed");
		}
	//Get console display User 5 hover to display
		Assert.assertFalse(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());
		boolean UserFiveHover = driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed();
		if (UserFiveHover == true) {
			System.out.println("User 5 is Displayed");
		}
		else {
			System.out.println("User 5 is NOT displayed");
		}
	
	//Nhap gia tri Automation testing vao 2 field Email/Edu
	driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation Testing Email");
	driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation Testing Edu");
	//Click chon Radio button Age under 18
	driver.findElement(By.xpath("//input[@id='under_18']")).click();
	}
	
	public void TC01_isEnable() {
	driver.get("https://automationfc.github.io/basic-form/");
	
	//Kiem tra cac phan tu Enable
	Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isEnabled());
	Assert.assertTrue(driver.findElement(By.xpath("//select[@id='job1']")).isEnabled());
	Assert.assertTrue(driver.findElement(By.xpath("//select[@id='job2']")).isEnabled());
	Assert.assertTrue(driver.findElement(By.xpath("//input[@id='development']")).isEnabled());
	Assert.assertTrue(driver.findElement(By.xpath("//input[@id='slider-1']")).isEnabled());
	
	//Kiem tra cac phan tu Disable
	Assert.assertFalse(driver.findElement(By.xpath("//input[@id='radio-disabled']")).isEnabled());
	Assert.assertFalse(driver.findElement(By.xpath("//select[@id='job3']")).isEnabled());
	Assert.assertFalse(driver.findElement(By.xpath("//input[@id='check-disbaled']")).isEnabled());
	Assert.assertFalse(driver.findElement(By.xpath("//input[@id='slider-2']")).isEnabled());
	
	//Get log console Slider 1 enable
	boolean Slide1Enable = driver.findElement(By.xpath("//input[@id='slider-1']")).isEnabled();
	if (Slide1Enable) {
		System.out.println("Slide 1 is Enabled");
	}
	else {
		System.out.println("Slide 1 is NOT Enabled");
	}
	
	//Get log console Slider 2 disable
	boolean Slide2Disable = driver.findElement(By.xpath("//input[@id='slider-2']")).isEnabled();
	if (Slide2Disable == true) {
		System.out.println("Slide 2 is Enabled");
	}
	else {
		System.out.println("Slide 2 is Disabled");
	}
	}
	
	
	@Test
	public void TC01_isSelected() throws InterruptedException {
	driver.get("https://automationfc.github.io/basic-form/");
	
	//Click chon Age under 18 button / java : language checkbox
	driver.findElement(By.id("under_18")).click();
	driver.findElement(By.id("java")).click();
	Thread.sleep(3000);
	
	//Kiem tra da duoc chon
	Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
	Assert.assertTrue(driver.findElement(By.id("java")).isSelected());
	
	//Click bo chon java checkbox va kiem tra
	driver.findElement(By.id("java")).click();
	Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
	Thread.sleep(3000);
	
	//Lay log console Select status
	boolean Radiobutton = driver.findElement(By.id("under_18")).isSelected();
	if (Radiobutton) {
		System.out.println("Age under 18 Radio button is selected");
	}
	else {
		System.out.println("Age under 18 Radio button is NOT selected");
	}
	
	//Lay log console Java checkbox status
	boolean Javacheckbox = driver.findElement(By.id("java")).isSelected();
	if (Javacheckbox) {
		System.out.println("Java checkbox is selected");
	}
	else {
		System.out.println("Java checkbox is NOT selected");
	}
		
		
		
	}

	@AfterTest
	  public void afterTest() {
		  driver.quit();
	  }	
}
