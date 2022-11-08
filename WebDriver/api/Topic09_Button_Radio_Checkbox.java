package api;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic09_Button_Radio_Checkbox {
	//Biến toàn cục
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	//Hàm
	public void beforeClass() {
		//set link đến latest driver (dynamic)
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
				
		//Khai bao driver cua JavascriptExecutor
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}
	//Khai báo biến By : dùng nhiều lần, qua nhiều màn hình, ko quan tâm status thay đổi
	//Biến WebElement : Chỉ dùng trong 1 màn hình và khi HTML ko thay đổi
	@Test(enabled = false)
	public void TC_01_Button() {
				By loginButton = By.cssSelector(".fhs-btn-login");
		
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		driver.findElement(By.cssSelector("#login_username")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("12345678");
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		//Remove attribute disable của Button - Dùng Javascript
		removeDisabledAttributeByJS(loginButton);
		sleepInsecond(2);
		
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		


	}
	
	// Hàm Verify : isSelected - Chỉ dùng cho thẻ Input kể cả trong trường hợp bị ẩn
	@Test(enabled = false)
	public void TC_02_Default_Radio_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
	//Click to all checkboxes
		List <WebElement> allItemCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
	//Khai báo biến allItem chứa tất cả trong allItemCheckboxes
		for (WebElement allItem : allItemCheckboxes) {
			allItem.click();
		}
		sleepInsecond(2);
	//Verify all checkboxes được selected
		for (WebElement allItem : allItemCheckboxes) {
		Assert.assertTrue(allItem.isSelected());  //Kết quả sẽ Failed nếu allItemCheckboxes không phải thẻ Input
		}
	
	//Radio Button
		driver.findElement(By.xpath("//input[@value='I have a loose diet']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='I have a loose diet']")).isSelected());
	}

	//Thẻ Input trong Custom Radio bị ẩn ko dùng Click được nhưng có thể dùng Verify được
	//Thẻ Span click được nhưng ko verify được
	@Test(enabled = false)
	public void TC_03_Custom_Radio_Checkbox() {
		driver.get("https://material.angular.io/components/radio/examples");
		//Case 1: Click to Radio manual
			//driver.findElement(By.xpath("//input[@value='Spring']/preceding-sibling::span[@class='mat-radio-inner-circle']")).click();
			//sleepInsecond(5);
		//Verify isSelected: phải luôn luôn xài xpath thẻ Input
			//Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Spring']")).isSelected());
		
		//Case 2: Dùng hàm javascript, ko cần quan tâm Element ẩn/hiện
		//Khai báo biến By nếu dùng nhiều lần
		By springRadio = By.xpath("//input[@value='Spring']");
		clickByJS(springRadio);
		//clickByJS(By.xpath("//input[@value='Spring']"));
		sleepInsecond(2);
		Assert.assertTrue(driver.findElement(springRadio).isSelected());
	}
	//Dùng DiffChecker phân biệt sự khác nhau trước sau của xpath Radio button
	@Test
	public void TC_04_Custom_Radio_Checkbox_II() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		//Before Click
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		
		//Click chọn Radio button
		driver.findElement(By.xpath("//div[@aria-label='Cần Thơ']")).click();
		
		//After Click
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());

	}
	
	public void removeDisabledAttributeByJS(By by) {
        WebElement element = driver.findElement(by);
        jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
}

	
	public void sleepInsecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//Click by Javascript : ko quan tâm Element ẩn/hiện
	public void clickByJS(By by) {
        WebElement element = driver.findElement(by);
        jsExecutor.executeScript("arguments[0].click();", element);
}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
