package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic13_Frame_Iframe {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	Select select;
	
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TC01_Iframe() {
	  driver.get("https://kyna.vn/");
	  
	  //Switch vao chat iframe
	  		// 1. Su dung Index : driver.switchTo().frame(0);  index dem tu 0	  
	  		// 2. Su dung ID or name: driver.switchTo().frame("cs_chat_iframe"); ID or name co dinh or doc nhat
	  		// 3. Su dung WebElement: 
	  		//driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='cs-live-chat']//iframe")));

	  driver.switchTo().frame("cs_chat_iframe"); //lay frame ID
	  driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")).click();
	  sleepInsecond(2);

	  // Input vao field bat buoc
	  driver.findElement(By.cssSelector("input.input_name")).sendKeys("Ta Van Trong");
	  driver.findElement(By.cssSelector("input.input_phone")).sendKeys("098 765 456");
	  // Dropdown button
	  select = new Select(driver.findElement(By.id("serviceSelect")));
	  select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
	  sleepInsecond(2);
	  
	  // Default content (Back lai parent trang)
	  driver.switchTo().defaultContent();
	  
	  // Chuyen sang ifamre Facebook
	  driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']//iframe")));
	  
	  String likeNumber = driver.findElement(By.xpath("//div[@class='_1drq']")).getText();
	  System.out.println(likeNumber);
	  
	  //Verify tieu de trang FB
	  Assert.assertTrue(driver.findElement(By.xpath("//a[@title='Kyna.vn']")).isDisplayed());
	 
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