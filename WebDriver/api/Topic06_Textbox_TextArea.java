package api;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic06_Textbox_TextArea {
	//Biến toàn cục
	WebDriver driver;
	
	@BeforeClass
	//Hàm
	public void beforeClass() {
		driver = new FirefoxDriver();
		//Timeout chờ để tìm element - wait ngầm định
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");
		
		}

	@Test
	public void TC_01_ValidateCurrentUrl() {
		// Textbox
		driver.findElement(By.name("user_mail")).sendKeys("automationfc@gmail.com");
		
		// TextArea (\n : xuong dong)
		driver.findElement(By.name("user_edu")).sendKeys("Automation\nTesting\nAdvance");
		
		//getAttribute - Lay du lieu nam trong attribute
			//<input type='text' value='Da nang' name='city'>
			Assert.assertEquals("Da nang", driver.findElement(By.name("city")).getAttribute("value"));
			
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
