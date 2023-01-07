package api;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic16_Upload_File {
	//Biến toàn cục
	WebDriver driver;
	String seleniumFileName = "Selenium.png";
	String eclipseFileName = "Eclipse.jpg";
	String katalonFileName = "Katalon.png";
	String seleniumPath = "F:\\Automation\\02 - Selenium API\\uploadFiles\\" + seleniumFileName;
	String eclipsePath = "F:\\Automation\\02 - Selenium API\\uploadFiles\\" + eclipseFileName;
	String katalonPath = "F:\\Automation\\02 - Selenium API\\uploadFiles\\" + katalonFileName;

	@BeforeClass
	//Hàm
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	// 1.Sử dụng hàm Sendkey của selenium - cover được hết all case
		// Tất cả Browser/OS (MAC/Win/Linux)
		// 1 or nhiều file cùng lúc
	// 2. AutoIT
		// Mỗi browser phải sử dụng 1 kịch bản khác nhau, chỉ Window (not MAC/Linux)
		// Khó sử dụng nếu upload nhiều file
	// 3. Robot Class (java)
		// All browser, chỉ sử dụng trên Window (Dialog trên win mới paste link được)
		// Khó sử dụng nếu upload nhiều file
	// 4. Sikuli
		// All browser , all OS
		// Dùng thông qua hình ảnh (change resolution) nó ko chạy được --> không ổn định (unstable)

	
	public void TC_01_Upload_1_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Hàm sendkey upload file ko care Xpath ẩn in this case/page
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		//Load file lên - preview
		uploadFile.sendKeys(seleniumPath);
		
		//Verify file loaded success (thẻ p)- Dynamic nối chuỗi
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + seleniumFileName + "']")).isDisplayed());
		
		//Click start upload
		driver.findElement(By.cssSelector(".files .start")).click();
		
		//Verify file upload success --> link thẻ a
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seleniumFileName + "']")).isDisplayed());
	}

	@Test
	public void TC_02_Upload_multi_files() {
		//Không thể sử dụng với firefox < 47.0.1 - Chỉ load dc file cuối
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(seleniumPath + "\n" + eclipsePath + "\n" + katalonPath);
		
		//Verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + seleniumFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + eclipseFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + katalonFileName + "']")).isDisplayed());
		
		//Click Start upload for each file
		List<WebElement> startButtons = driver.findElements(By.cssSelector(".files .start"));
		
		for (WebElement start : startButtons) {
			start.click();
			sleepInsecond(2);
		}
		
		//Verify files uploaded success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seleniumFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + eclipseFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + katalonFileName + "']")).isDisplayed());

		
	}

	
	
	
	public void sleepInsecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
