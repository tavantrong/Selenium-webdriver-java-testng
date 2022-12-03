package api;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic11_User_Interaction_Part_II {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor javascriptExecutor;
	String javascriptPath = projectPath + "\\dragAndDrop\\drag_and_drop_helper.js";
	String jqueryPath = projectPath + "\\dragAndDrop\\jquery_load_helper.js";
	

  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  action = new Actions(driver);
	  javascriptExecutor = (JavascriptExecutor) driver;
	  //Time chờ Alert xuất hiện là 10s
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  
  public void TC01_Right_Click() {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  //Right click vao nut "rifgt click me"
	  action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
	  sleepInsecond(3);
	  
	  //Verufy su xuat hien cua Quit
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());
	  
	  //Hover Quit
	  action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
	  sleepInsecond(2);
	  
	  //Verify Quit co them thuoc tinh (Hover and Visible)
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-hover') and contains(@class,'context-menu-visible')]")).isDisplayed());
	  
	  //Click vao Quit
	  action.click(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
	  
	  //Verify and accept alert
	  Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
	  sleepInsecond(2);
	  driver.switchTo().alert().accept();
	  
	  //Verify Quit is not displayed
	  Assert.assertFalse(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());

	  
  }
  
  
  public void TC02_Drag_Drop_HTML4() {
	  driver.get("https://automationfc.github.io/kendo-drag-drop/");
	  
	  WebElement sourceCircle = driver.findElement(By.id("draggable"));
	  WebElement targetCircle = driver.findElement(By.id("droptarget"));
	  sleepInsecond(2);
	  action.dragAndDrop(sourceCircle, targetCircle).perform();
	  sleepInsecond(3);
	  //Verify dua vao text displayed
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
	  //Verify dua vao color
	  Assert.assertEquals(getHexaValue(driver.findElement(By.id("droptarget")).getCssValue("background-color")), "#03a9f4");
  }
  
  @Test(enabled = false)
  public void TC03_Drag_Drop_HTML5_CssOnly() throws IOException {
	  driver.get("https://automationfc.github.io/drag-drop-html5/");
		String java_script = getContentFile(javascriptPath);
		
		String sourceCss = "#column-a";
		String targetCss = "#column-b";


		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		javascriptExecutor.executeScript(java_script);
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

		// B to A
		javascriptExecutor.executeScript(java_script);
		sleepInsecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
  }
  
  @Test
  public void TC03_Drag_Drop_HTML5_Xpath() throws AWTException {
	  //Drop and Drag toa do bang thu vien Robot cua Java
	  //Work dung voi Scale resolution = 100%
	  driver.get("https://automationfc.github.io/drag-drop-html5/");
	  
	  dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
	  sleepInsecond(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
	  
	  dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
	  sleepInsecond(3);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());



  }
  
  
  
  
  public String getHexaValue(String rgbValue) {
		//Lay ra gia tri Hexa de Verify mau
		return Color.fromString(rgbValue).asHex();
	}
  
  public void sleepInsecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}
  
  public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
  
  
  public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
  
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
  
}