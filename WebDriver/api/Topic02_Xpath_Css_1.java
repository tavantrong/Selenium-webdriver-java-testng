package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic02_Xpath_Css_1 {
	WebDriver driver;
  @BeforeClass
  public void beforeClass() {
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.manage().window().maximize();
  }
  
  @Test
  public void TC01_ID() {
  }
  @Test
  public void TC02_Class() {
  }
  @Test
  public void TC03_Name() {
	  
  }
  @Test
  public void TC04_Tagname() {
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  System.out.println("Tổng số link =" + driver.findElements(By.tagName("a")).size());
  }
  @Test
  public void TC05_Linktext() throws InterruptedException {
	  //Text của link tuyệt đối (chính xác toàn bộ chuỗi) - ít dùng
	  driver.findElement(By.linkText("Hợp tác giảng dạy")).click();
	  Thread.sleep(4000);
	  
  }
  @Test
  public void TC06_Partial_Linktext() throws InterruptedException {
	  //text của link tương đối (1 phần của chuỗi) - ít dùng
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  driver.findElement(By.partialLinkText("Hợp tác")).click();
	  driver.findElement(By.partialLinkText("thỏa thuận")).click();
	  Thread.sleep(4000);
	  
  }
  @Test
  public void TC07_Css_Selector() throws InterruptedException {
	  //CSS không làm việc với text được
	  //Không thể lấy ngược lên được
	  //CSS: tagname[attribute='value']
	  driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	  
	  //CSS - Use ID
	  driver.findElement(By.cssSelector("input[id='txtFirstname']")).sendKeys("testingID");
	  sleepinSecond(3);
	  driver.findElement(By.cssSelector("input[id='txtFirstname']")).clear();
	  
	  //CSS - placeholder
	  driver.findElement(By.cssSelector("input[placeholder='Nhập họ và tên']")).sendKeys("testing_placeholder");
	  sleepinSecond(3);
	  driver.findElement(By.cssSelector("input[placeholder='Nhập họ và tên']")).clear();
	  
	  //CSS - Kí tự đặc biệt "#, ."
	  driver.findElement(By.cssSelector("#txtFirstname")).sendKeys("CSS_Ki Tu Dac Biet");
	  sleepinSecond(3);
	  driver.findElement(By.cssSelector("#txtFirstname")).clear();
	  		//Thay thế cho class="class="text form-control""
	  driver.findElement(By.cssSelector(".text.form-control")).sendKeys("CSS Class Combine");
	  sleepinSecond(3);
  }

@Test
  public void TC08_Xpath() {
	// Xpath cover hết 7 loại trên
	//Xpath: //tagname[@attribute='value']
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Cấu trúc parent
		driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtFirstname']")).sendKeys("Parent Xpath");
		sleepinSecond(3);
	  
  }
  
  public void sleepinSecond(long time) {
	  try {
		Thread.sleep(time * 1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
