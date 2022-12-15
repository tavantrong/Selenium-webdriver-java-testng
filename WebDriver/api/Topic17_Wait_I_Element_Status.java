package api;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic17_Wait_I_Element_Status {
	//Biến toàn cục
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	//Hàm
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
		
		}

	@Test
	public void TC_01_Visible() {
		// 1. Element có trong UI và trong DOM (bắt buộc)
			// User có thể thấy và thao tác được
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		//Chờ cho nút Login được visible trong vòng 15s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Login']")));
		
	}

	@Test
	public void TC_02_Invisible() {
		// 2. Element không có trên UI (bắt buộc), nhưng có / không có trong DOM - invisibility
		// Người dùng ko thế thao tác và nhìn thấy
			//Có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
			//Không có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[text()='Register']")));
		
	}

	@Test
	public void TC_03_Presence() {
		// Có (giống visible) hoặc Không có trên UI (bị che mất)
		// Phải có trong DOM (bắt buộc) - presence
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
		

	}
	
	@Test
	public void TC_04_Staleness() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
