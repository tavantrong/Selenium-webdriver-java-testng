package api;
import org.testng.annotations.Test;

import com.google.common.base.Function;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic17_Wait_VIII_Fluent {
	// Static wait - Wait được sét cứng - Bắt buộc chờ hết time out - Step tiếp theo
	WebDriver driver;
	FluentWait<WebElement> fluentElement;
	FluentWait<WebDriver> fluentDriver;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		}
	
	// Fluent wait : Cơ chế giống implicit/explicit nhưng có thể custom lại polling
		// Implicit/Explicit polling: 0.5s check lại 1 lần nếu ko thấy element cho đến hết timeout
			// Mặc định ignore exception - chỉ fail nếu hết timeout mà vẫn ko thấy element 
	
		// Fluent polling: Tự set đơn vị để check lại element là bao nhiêu second/ milisecond.
			// Nếu quá trình xử lý - không chèn code ignore exception - fail ngay lập tức


	public void TC_01_Fluent_WebElement() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdownTime = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		
		fluentElement = new FluentWait<WebElement>(countdownTime);
		
		fluentElement.withTimeout(15, TimeUnit.SECONDS)
					.pollingEvery(300, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class);
		
		//Set điều kiện
		fluentElement.until(new Function<WebElement, Boolean>(){
			@Override
			public Boolean apply(WebElement countdownTime) {
				//getText của Element countdownTime
				String text = countdownTime.getText();
				System.out.println(text);
				//Trả về True/Pass nếu countdownTime kết thúc với "00"
				return text.endsWith("00");
			}
		});
	}
	
	@Test
	public void TC_02_Fluent_WebDriver() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		//Wait for Start button display và Click
		waitForElementAndClick(By.xpath("//div[@id='start']/button"));
		// Check display "Hello World!" and verify
		isElementDisplay(By.xpath("//div[@id='finish']/h4"));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
		
		// Check NOTFOUND Element - Throw Exception (TimeoutException) = 15s - FAIL
		isElementDisplay(By.xpath("//div[@id='finish']/h8"));

	}
	
	
	
	
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	
	public WebElement getElement(By locator) {
		//Khởi tạo Fluent wait với WebDriver
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				// Tổng timeoutInSecond được tính bằng second và bằng 15
				.withTimeout(timeoutInSecond, TimeUnit.SECONDS)
				// Tần số mỗi intervalMilisecond = 300 miliSecond = 0.3s check 1 lần
				.pollingEvery(intervalInMilisecond, TimeUnit.MILLISECONDS)
				// Nếu gặp exception là find không thấy Element sẽ bỏ qua
				.ignoring(NoSuchElementException.class); // Sử sụng Exception NosuchElement của selenium
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){ //Function của Google Base
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;		
	}
	
	
	public void waitForElementAndClick(By locator) {
		WebElement element = getElement(locator);
		element.click();
	}
	
	
	public boolean isElementDisplay(By locator) {
		WebElement element = getElement(locator);
		return element.isDisplayed();
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	long timeoutInSecond = 15;
	long intervalInMilisecond = 100; // 300 miliSecond = 0.3s (second) (Inplicit/Explicit = 0.5s)
	
}
