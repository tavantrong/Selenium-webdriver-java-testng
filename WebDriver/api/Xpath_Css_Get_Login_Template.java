package api;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Xpath_Css_Get_Login_Template {
	//Biến toàn cục
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	  public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	  }

	@Test
  public void TC01_Login_Empty_DATA() {
		//Use empty data to log in
		
		driver.get("https://alada.vn/tai-khoan/dang-ky.html/");
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(), "Vui lòng nhập họ tên");
  }
	
	@Test
	  public void TC02_Login_Invalid_Email() {
		//Use invalid email
			
		driver.get("https://alada.vn/tai-khoan/dang-ky.html/");
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Trong");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("tronggmail.com");
		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
			
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email hợp lệ");
	  }
	
	@Test
	  public void TC03_Login_Incorrect_Confirmed_Email() {
		//Use invalid email
			
		driver.get("https://alada.vn/tai-khoan/dang-ky.html/");
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Trong");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("trong@yahoo.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("trong@yahoo.net");

		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
			
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
	  }
	
	@Test
	  public void TC04_Login_With_Password_Under_6_Character() {
		//Use invalid password
			
		driver.get("https://alada.vn/tai-khoan/dang-ky.html/");
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Trong");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("trong@yahoo.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("trong@yahoo.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345");


		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
			
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	  }
	
	@Test
	  public void TC05_Login_Incorrect_Confirmed_Password() {
		//Use wrong password
		driver.get("https://alada.vn/tai-khoan/dang-ky.html/");	
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Trong");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("trong@yahoo.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("trong@yahoo.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123457");

		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
	  }
	
	@Test
	  public void TC06_Login_Invalid_Phone_Number() {
		//Use invalid email
		driver.get("https://alada.vn/tai-khoan/dang-ky.html/");	
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Trong");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("trong@yahoo.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("trong@yahoo.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("Mothaiba");
		
		
		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập con số");
	  }
	
	@Test
	  public void TC07_Login_Invalid_Phone_Number_2() {
		//Use invalid email
		driver.get("https://alada.vn/tai-khoan/dang-ky.html/");	
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Trong");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("trong@yahoo.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("trong@yahoo.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("123456789");
		
		
		driver.findElement(By.xpath("//div[@class='field_btn']//button[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	  }
  

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
