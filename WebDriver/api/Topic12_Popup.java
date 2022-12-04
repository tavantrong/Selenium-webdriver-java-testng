package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic12_Popup {
	WebDriver driver;
	Actions action;
	String projectLocation = System.getProperty("user.dir");
	
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();

	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  
  public void TC01_Popup_Fixed_Dispay() {
	  //Popup co dinh
	  driver.get("https://tiki.vn/");
	  driver.findElement(By.xpath("//div[@class='Userstyle__Root-sc-6e6am-0 lhUPfd']")).click();
	  sleepInsecond(3);
	  //Verify popup displayed
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='styles__Root-sc-2hr4xa-0 jyAQAr']")).isDisplayed());
	  
  }
  
  
  public void TC02_Popup_in_DOM() {
	  //Popup close nhung van con trong DOM
	  driver.get("https://www.kmplayer.com/home");
	  
	  //Verify popup is displayed
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='pop-conts']")).isDisplayed());
	  sleepInsecond(2);

  }
  
  
  public void TC03_Popup_not_in_DOM() {
	  driver.get("https://tiki.vn/");
	  driver.findElement(By.xpath("//div[@class='Userstyle__Root-sc-6e6am-0 lhUPfd']")).click();
	  sleepInsecond(2);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='styles__Root-sc-2hr4xa-0 jyAQAr']")).isDisplayed());
	  
	  //Close popup
	  driver.findElement(By.xpath("//button[@class='btn-close']")).click();
	  sleepInsecond(2);
	  //Verify popup not display (ko co trong DOM)- cach binh thuong se bao failed
	  //Dung findElements so nhieu de dem so luong
	  //Cach nay cho Verify rat lau = time implicitlyWait set o tren
	  Assert.assertEquals(driver.findElements(By.xpath("//div[@class='styles__Root-sc-2hr4xa-0 jyAQAr']")).size(), 0);

  }
  
  @Test
  public void TC04_Popup_in_DOM_ConditionorRandom() {
	  System.out.println("Step 1");
	  driver.get("https://bangbang.cmn.vn/trang-chu.html");
	  
	  // 1 - Nếu popup xuất hiện --> Step 2: Close --> Step 3
	  // 2 - Nếu popup ko xuất hiện --> qua Step 3
	  
	  //Có hoặc ko xuất hiện đều ở trong DOM
	  if (driver.findElement(By.xpath("//div[@class='popup-kulcard']")).isDisplayed()) {
		  System.out.println("Step 2");
		  driver.findElement(By.xpath("//a[@class='popup-kulcard-close close']")).click();
		  sleepInsecond(3);
		  
	  }
	  
	  // Có xuất hiện có trong DOM
	  // Chưa xuất hiện thì ko có trong DOM
	  //if (driver.findElements(By.xpath("//div[@class='popup-kulcard']")).size() >=1) {
		//  System.out.println("Step 2");
		//  driver.findElement(By.xpath("//a[@class='popup-kulcard-close close']")).click();
		//  sleepInsecond(3);
	 // }
	  
	  System.out.println("Step 3");
	  driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Mothaiba");
	  sleepInsecond(3);
	  
  }
  
  
  
  
  public void sleepInsecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}
  
  
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
}