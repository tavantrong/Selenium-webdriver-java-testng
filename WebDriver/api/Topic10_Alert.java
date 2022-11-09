package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic10_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;

  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  //Time chờ Alert xuất hiện là 10s
	  explicitWait = new WebDriverWait(driver, 10);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test(enabled = false)
  public void TC01_Accept_Alert() {
	  driver.get("https://automationfc.github.io/basic-form/");
	  driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	  
	  //Chờ Alert xuất hiện
	  explicitWait.until(ExpectedConditions.alertIsPresent());
	  //Sau khi xuất hiện thì..switch to alert
	  alert = driver.switchTo().alert();
	  //Hoặc alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	  //Verify
	  Assert.assertEquals(alert.getText(), "I am a JS Alert");
	  sleepInsecond(2);
	  alert.accept(); //Chọn OK trong Alert
	  Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	  
  }
  
  @Test(enabled = false)
  public void TC02_Confirm_Alert() {
	  driver.get("https://automationfc.github.io/basic-form/");
	  driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	  
	  alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	  Assert.assertEquals(alert.getText(), "I am a JS Confirm");
	  sleepInsecond(2);
	  
	  alert.dismiss(); //Chọn Cancel trong alert
	  Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");

  }
  
  @Test(enabled = false)
  public void TC03_Prompt_Alert() {
	  String alertText = "Automation Prompt Alert Test";
	  driver.get("https://automationfc.github.io/basic-form/");
	  driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	  
	  alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	  Assert.assertEquals(alert.getText(), "I am a JS prompt");
	  alert.sendKeys(alertText);
	  sleepInsecond(5);
	  
	  alert.accept();
	  Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + alertText);
	  
  }
  
  @Test(enabled = false)
  public void TC04_Authentication_Alert() {
	  //Case 1: Selenium By pass truyền thẳng username/password vào alert
	  // http://username:password@the-internet.herokuapp.com/basic_auth
	  driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	  sleepInsecond(2);
	  Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed(), "Congratulations! You must have the proper credentials.");
	  // password có kí tự đặc biệt admin/P@sswo!d
	  // Sử dụng HTML encode để phiên dịch kí tự đặc biệt : P%40sswo%21d (https://www.w3schools.com/tags/ref_urlencode.ASP)
	  
  }
  @Test
  public void TC05_Authentication_Alert_Custom() {
  driver.get("https://the-internet.herokuapp.com/");
  sleepInsecond(3);
  String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
  
  driver.get(getCredentialToUrl(url, "admin", "admin"));
  Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed(), "Congratulations! You must have the proper credentials.");

  
  }
  
  
  public void sleepInsecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}
  
public String getCredentialToUrl(String url, String username, String password) {
	String[] ArrayUrl = url.split("//") ;
	url = ArrayUrl[0] + "//" + username + ":" + password + "@" + ArrayUrl[1];
	
	return url;
	  
  }
  
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
}