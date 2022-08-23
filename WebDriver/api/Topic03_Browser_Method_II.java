package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic03_Browser_Method_II {
	WebDriver driver;
	
	

  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
  }
  
  @Test
  public void f() {
	  //Hàm - method - command để tương tác với browser
	  	//driver.get("https://www.google.com.vn/"); - Mở 1 trình duyệt với link chỉ định 
	  	//driver.close(); - Đóng 1 tab trình duyệt đang mở
	  	//driver.quit(); - Đóng trình duyệt
	  	//Lấy link của trang hiện tại rồi verify
	  		driver.get("https://automationfc.github.io/basic-form/");
	  		driver.findElement(By.xpath("//a[text()='Quảng Nam']")).click();
	  		driver.getCurrentUrl();
	  		Assert.assertEquals(driver.getCurrentUrl(), "https://vi.wikipedia.org/wiki/Qu%E1%BA%A3ng_Nam");
	  		System.out.println(driver.getCurrentUrl());
	  		
	  	//driver.getTitle(); - Lấy ra title của trang hiện tại
		//driver.getPageSource(); - Lấy tất cả source code của trang hiện tại
	  	//driver.getWindowHandle(); - Lấy ID của Tab/Window nó đang đứng (active)
	  	//driver.getWindowHandles(); - Lấy ra tất cả ID của tab/window
	  	
	  	//implicitlyWait - Chờ cho element xuất hiện để tương tác trong vòng 30s
	  	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  	driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
	  	// Chờ page được load xong trong vòng 30s
	  	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	  	// Chờ đoạn Script của Javascript Executor thực thi xong trong vòng 30s 
	  	driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	  	
	  	//Phóng to browser
	  	driver.manage().window().maximize();
	  	//driver.manage().window().fullscreen(); - F11 (full screen màn hình)
	  	driver.manage().window().getPosition();
	  	driver.manage().window().getSize();
	  	driver.manage().window().setPosition(null);
	  	driver.manage().window().setSize(null);
	  	
	  	//Thao tác nút bấm trình duyệt - Back/Foward/F5
  		driver.get("https://automationfc.github.io/basic-form/");
  		driver.findElement(By.xpath("//a[text()='Quảng Nam']")).click();
  		
	  	driver.navigate().back();
	  	driver.navigate().forward();
	  	driver.navigate().refresh();
	  	
	  	//Mở ra 1 trang với URL - Tracking history tốt hơn
	  	driver.navigate().to("https://www.google.com.vn/");
	  	
	  	//Thao tác với Alert vs Frame/Iframe
	  	driver.switchTo().alert();
	  	driver.switchTo().frame('0');
	  	
	  	//Thao tác với Window/tab
	  	driver.switchTo().window("");


	  		  	
  }
  
  
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
