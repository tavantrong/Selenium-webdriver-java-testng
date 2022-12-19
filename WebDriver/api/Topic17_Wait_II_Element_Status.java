package api;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic17_Wait_II_Element_Status {
	//Biến toàn cục
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		}

	
	public void TC_01_Find_Element() {
		// 1 = Tìm được 1 Element
		// Thấy - ko cần chờ hết timeout
		// Chưa thấy - tiếp tục chờ hết 15s và mỗi 0.5s sẽ tìm lại 1 lần
		// Trong time chờ - tìm thấy - chuyển qua step tiếp theo (bỏ qua timeout đã set)
		System.out.println("Start - " + getDateTimeNow());
		driver.findElement(By.id("email"));
		System.out.println("End - " + getDateTimeNow());
		
		// 2 - Tìm thấy nhiều Element
		// Sẽ thao tác với element đầu tiên (ko quan tâm element ẩn hay hiện)
		System.out.println("Start - " + getDateTimeNow());
		driver.findElement(By.xpath("//input"));
		System.out.println("End - " + getDateTimeNow());
		
		// 3 - Ko thấy element nào
		// Chờ hết time out - Đánh fail testcase
		//Throw ra 1 exception: No such element
		System.out.println("Start - " + getDateTimeNow());
		driver.findElement(By.xpath("//label"));
		System.out.println("End - " + getDateTimeNow());
		
	}

	@Test
	public void TC_02_Find_Elements() {
		// 1 = Tìm được 1 Element
		// Thấy - ko cần chờ hết timeout
		// Chưa thấy - tiếp tục chờ hết 15s và mỗi 0.5s sẽ tìm lại 1 lần
		// Trong time chờ - tìm thấy - chuyển qua step tiếp theo (bỏ qua timeout đã set)
		// Lưu nó vào 1 List (chỉ có duy nhất 1 element)
		System.out.println("Start - " + getDateTimeNow());
		List<WebElement> elements = driver.findElements(By.id("email"));
		System.out.println("Số element tìm thấy: " + elements.size());
		System.out.println("End - " + getDateTimeNow());
		
		// 2 - Tìm thấy nhiều Element
		// lưu vào trong List (chứa các element thõa mãn điều kiện)
		System.out.println("Start - " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//input"));
		System.out.println("Số element tìm thấy: " + elements.size());
		System.out.println("End - " + getDateTimeNow());
		
		// 3 - Ko thấy element nào
		// Không Fail testcase , ko throw exception
		// Trả về List 0 (rỗng) - Chuyển sang step tiếp theo
		System.out.println("Start - " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//label"));
		System.out.println("Số element tìm thấy: " + elements.size());
		System.out.println("End - " + getDateTimeNow());
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
