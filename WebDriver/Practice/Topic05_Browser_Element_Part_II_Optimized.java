package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Topic05_Browser_Element_Part_II_Optimized {
	WebDriver driver;
	By emailTextbox = By.cssSelector("#mail");
	By educationTextArea = By.cssSelector("#edu");
	By Ageunder18Radiobutton = By.cssSelector("#under_18");
	By nameTextuser5 = By.xpath("//h5[text()='Name: User5']");
	By Slider01 = By.cssSelector("#slider-1");
	By Slider02 = By.cssSelector("#slider-2");
	By Javacheckbox = By.cssSelector("#java");

	@BeforeTest
	  public void beforeTest() {
		  driver = new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
	}
	
	
	public void TC01_isDisplayed() {
		
	driver.get("https://automationfc.github.io/basic-form/");
	
	//Goi ham check Display Console va Sendkey
	if (isElementDisplayed(emailTextbox)) {
		sendKeytoElement(emailTextbox, "Automation testing Email");	}
	
	if (isElementDisplayed(educationTextArea));{
	sendKeytoElement(educationTextArea, "Automation testing Education");}
	
	Assert.assertFalse(isElementDisplayed(nameTextuser5));
	}
	
	
	public void TC01_isEnable() {
		driver.get("https://automationfc.github.io/basic-form/");

		Assert.assertTrue(isElementEnable(Slider01));
		Assert.assertFalse(isElementEnable(Slider02));
	}
	
	@Test
	public void TC01_isSelected() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/");
		
		ClicktoElement(Ageunder18Radiobutton);
		ClicktoElement(Javacheckbox);
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementSelected(Ageunder18Radiobutton));
		Assert.assertTrue(isElementSelected(Javacheckbox));

		ClicktoElement(Ageunder18Radiobutton);
		ClicktoElement(Javacheckbox);
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementSelected(Ageunder18Radiobutton));
		Assert.assertFalse(isElementSelected(Javacheckbox));
	}
	
	
	
	//Tham so check Display
	public boolean isElementDisplayed(By by) {
	WebElement element = driver.findElement(by);
	if (element.isDisplayed()) {
		System.out.println(by + " is Displayed");
		return true;
	}
	else {
		System.out.println(by + " is NOT Displayed");
		return false;
	}}
	//Tham so check Enable
	public boolean isElementEnable(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println(by + " is Enable");
			return true;
		}
		else {
			System.out.println(by + " is Disable");
			return false;
		}}
	//Tham so check Selected
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println(by + " is Selected");
			return true;
		}
		else {
			System.out.println(by + " is De-selected");
			return false;
		}}
	//Tham so ham Sendkey
	public void sendKeytoElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}
	//Tham so ham Click
	public void ClicktoElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	@AfterTest
	  public void afterTest() {
		  driver.quit();
	  }
}
