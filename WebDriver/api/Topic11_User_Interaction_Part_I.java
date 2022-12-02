package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic11_User_Interaction_Part_I {
	WebDriver driver;
	Actions action;
	
	

  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  action = new Actions(driver);
	  //Time chờ Alert xuất hiện là 10s
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test(enabled = false)
  public void TC01_Hover_Element_Tooltip() {
	  driver.get("https://automationfc.github.io/jquery-tooltip/");
	  action.moveToElement(driver.findElement(By.xpath("//a[text()='ThemeRoller']"))).perform();
	  sleepInsecond(2);
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "ThemeRoller: jQuery UI's theme builder application");
  }
  
  @Test(enabled = false)
  public void TC02_Hover_Element() {
	  driver.get("https://www.myntra.com/");
	  action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"))).perform();
	  sleepInsecond(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='desktop-navBlock']//a[text()='Toys']")).isDisplayed());
	  
	  driver.findElement(By.xpath("//ul[@class='desktop-navBlock']//a[text()='Toys']")).click();
	  sleepInsecond(2);
	  Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")), "Toys Store");


  }
  
  @Test(enabled = false)
  public void TC03_Click_And_Hold() {
	  driver.get("https://automationfc.github.io/jquery-selectable/");
	  
	  List<WebElement> numberSelect = driver.findElements(By.xpath("//ol[@class='ui-selectable']/li"));
	  action.clickAndHold(numberSelect.get(0)).moveToElement(numberSelect.get(3)).release().perform();
	  sleepInsecond(3);
	  
	  List<WebElement> numberBeSelected = driver.findElements(By.xpath("//ol[@class='ui-selectable']/li[contains(@class,'ui-selected')]"));
	  Assert.assertEquals(numberBeSelected.size(), 4);
	  
	  for (WebElement numsize : numberBeSelected) {
		System.out.println(numsize.getText());
	}}
  
  @Test(enabled = false)
  public void TC04_Click_And_Hold_RandomNumber() {
	  driver.get("https://automationfc.github.io/jquery-selectable/");
	  
	  List<WebElement> numberSelect = driver.findElements(By.xpath("//ol[@class='ui-selectable']/li"));
	  //Sau action --> .perform();
	  //Nhan vs hold nut Ctrl
	  action.keyDown(Keys.CONTROL).perform();
	  
	  //Chon cac so 2, 7, 9, 10
	  action.click(numberSelect.get(1)).click(numberSelect.get(6)).click(numberSelect.get(8)).click(numberSelect.get(9)).perform();
	  sleepInsecond(3);
	  
	  List<WebElement> numberBeSelected = driver.findElements(By.xpath("//ol[@class='ui-selectable']/li[contains(@class,'ui-selected')]"));
	  Assert.assertEquals(numberBeSelected.size(), 4);
	  
	  for (WebElement numsize : numberBeSelected) {
		System.out.println(numsize.getText());
	}}
  
  @Test
  public void TC05_DoubleClick() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  
	  action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	  sleepInsecond(2);
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
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