package Practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic03_Browser_Command {
	WebDriver driver;

	@BeforeClass
	  public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
		
	@Test
	public void TC01_Verify_URL() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}
	
	@Test
	public void TC02_Verify_Title() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	
	}
		
	@Test
	public void TC03_Navigate_Function() throws InterruptedException {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Thread.sleep(5000);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC04_Get_PageSource() throws InterruptedException {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
		//X??c nh???n trang n??y ko ch???a t??? Login or Create an Account
		Assert.assertFalse(driver.getPageSource().contains("Login or Create an Account"));


		//Assert.assertTrue(true); - ph???i c?? ?????i t?????ng ????? c???p
		//Assert.assertFalse(false); - kh??ng c?? ?????i t?????ng ????? c???p
		//Assert.assertEquals(true, true); - X??c nh???n 2 ?????i t?????ng ????ng / gi???ng nhau
		//Assert.assertEquals(false, false); - Ng?????c l???i
		

	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
}
