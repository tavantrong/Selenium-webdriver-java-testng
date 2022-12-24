package api;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic17_Wait_VII_Mixing_Implicit_Explicit {
	// Static wait - Wait được sét cứng - Bắt buộc chờ hết time out - Step tiếp theo
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();		
		}
	
	//Timeout để FindElement là Implicit
	//Timeout trong exception là Explicit
		//Nếu chỉ dùng explicit thì phải build Code thật tốt / handle all case
		//Bất kì action đều phải wait, ko là fail (do findElement/findElements = 0)
	
	//Nhận timeout của cả 2 (sẽ chạy theo dang Async bất đồng bộ) - chạy lâu hơn
		//Sẽ kích hoạt cùng lúc or chênh lệch 0.5-1s
		//Mỗi lần Run sẽ ra 1 tổng timeout khác nhau
		//Nên dùng cả 2 để tránh trường hợp không handle tất cả các case của các loại wait

	@Test
	public void TC_01_Not_Found_Element_Implicit_Equal_Explicit() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login");
		
		explicitWait = new WebDriverWait(driver, 4);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		
		//Try catch - bỏ qua error NosuchElement - Pass testcase
		System.out.println("START Implicit wait: " + getDateTimeNow());
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
		} catch (Exception e) {}
		System.out.println("END Implicit wait: " + getDateTimeNow());

		System.out.println("START Ex"
				+ "plicit wait: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@text()='11']")));
		} catch (Exception e) {}
		System.out.println("END Explicit wait: " + getDateTimeNow());
	}

	@Test
	public void TC_02_Not_Found_Element_Implicit_Less_Explicit() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login");
		
		explicitWait = new WebDriverWait(driver, 6);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		
		System.out.println("START Implicit wait: " + getDateTimeNow());
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
		} catch (Exception e) {}
		System.out.println("END Implicit wait: " + getDateTimeNow());

		System.out.println("START Ex"
				+ "plicit wait: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@text()='11']")));
		} catch (Exception e) {}
		System.out.println("END Explicit wait: " + getDateTimeNow());
	}
	
	@Test
	public void TC_03_Not_Found_Element_Implicit_Greater_Explicit() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login");
		
		explicitWait = new WebDriverWait(driver, 4);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		System.out.println("START Implicit wait: " + getDateTimeNow());
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
		} catch (Exception e) {}
		System.out.println("END Implicit wait: " + getDateTimeNow());

		System.out.println("START Ex"
				+ "plicit wait: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@text()='11']")));
		} catch (Exception e) {}
		System.out.println("END Explicit wait: " + getDateTimeNow());
	}
	

	
	
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
