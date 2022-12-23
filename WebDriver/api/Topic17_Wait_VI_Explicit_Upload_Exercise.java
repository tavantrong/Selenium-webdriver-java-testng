package api;
import org.testng.annotations.Test;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic17_Wait_VI_Explicit_Upload_Exercise {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String angelFileName = "psd_angel.psd";
	String signatureFileName = "psd_signature.psd";
	
	String angelPath = projectPath + getFileSeparator() + "uploadFiles" + getFileSeparator() + angelFileName;
	String signaturePath = projectPath + getFileSeparator() + "uploadFiles" + getFileSeparator() + signatureFileName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		
		}

	
	public void TC_01() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//Wait for Data Picker visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		//Verify Text 'Selected Dates'
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "No Selected Dates to display.");
		
		//Wait for Current Date Clickable 
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Friday, December 16, 2022']")));
		driver.findElement(By.xpath("//td[@title='Friday, December 16, 2022']")).click();
		
		//Wait for Ajax loading icon invisible (chờ hết cái loading icon) - Xpath cấu trúc phủ định có NOT
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		//Wait for 16 number is selected
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@title='Friday, December 16, 2022' and @class='rcSelected']")));

		Assert.assertTrue(driver.findElement(By.xpath("//td[@title='Friday, December 16, 2022' and @class='rcSelected']")).isDisplayed());
		
		//Verify test in 'Selected Dates'
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "Friday, December 16, 2022");


	}

	@Test
	public void TC_02() {
		explicitWait = new WebDriverWait(driver, 45);
		driver.get("https://filebin.net/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(angelPath + "\n" + signaturePath);
		
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='progress-bar progress-bar-striped progress-bar-animated']"))));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + angelFileName + "']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + signatureFileName + "']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + angelFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + signatureFileName + "']")).isDisplayed());
	}

	// Hàm để detect dấu tùy theo Win (//) or macOS (\\)
	public String getFileSeparator() {
		return File.separator;
	}
			
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
