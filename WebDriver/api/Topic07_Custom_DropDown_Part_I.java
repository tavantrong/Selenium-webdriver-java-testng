package api;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic07_Custom_DropDown_Part_I {
	//Biến toàn cục
	WebDriver driver;
	WebDriverWait explicitWait;
	
	
	@BeforeClass
	//Hàm
	public void beforeClass() {
		driver = new FirefoxDriver();
		// Khai báo driver sau để lấy biến driver
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}

	@Test(enabled = false) // Disable testcase này
	public void TC01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "13");
		sleepInsecond(4);
		// Lấy xpath item mình chọn và so sánh item mong muốn bằng nhau
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(), "13");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "8");
		sleepInsecond(4);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(), "8");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		sleepInsecond(4);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(), "19");
	}	
	/* Hành vi của 1 Dropdown:
	  	- Click vào Dropdown
	  	- Chờ cho các item được hiển thị ra
	  	- Tìm item cần chọn
	  		+ Item cần tìm nằm trong tầm nhìn thấy của user --> Click chọn
	  		+ Không nằm trong tầm nhìn thấy (viewport) --> Scroll xuống --> Click
	  	- Bấm vào
	  	- Kiểm tra xem chọn đúng chưa
	 */
	
	@Test
	public void TC02_Nopcommerce() {
		driver.get("https://demo.nopcommerce.com/register");
		selectItemInCustomDropdown("//select[@name='DateOfBirthYear']", "//select[@name='DateOfBirthYear']//option", "1987");
		selectItemInCustomDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']//option", "19");
		selectItemInCustomDropdown("//select[@name='DateOfBirthMonth']", "//select[@name='DateOfBirthMonth']//option", "March");
		
		sleepInsecond(4);
	}

	
	
	public void selectItemInCustomDropdown(String parentXpass, String allItemXpath, String expectedText) {
		// Click vào Dropdown
		driver.findElement(By.xpath(parentXpass)).click();
		
		// Chờ cho các item được hiển thị ra
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		//Lấy hết item con đưa vào 1 list để duyệt qua
		List<WebElement> allItem = driver.findElements(By.xpath(allItemXpath));
		
		
		//Dùng vòng lặp duyệt qua từng item - For each
		for (WebElement item : allItem) {
			// Duyệt qua từng item và getText ra
			// Nếu như text get ra bằng với cái text mong muốn - dừng vs lick vào item đó
			// Thoát khỏi vòng lặp
			if (item.getText().equals(expectedText)) {
				item.click();
				break;
			}
			
		}
	}
	public void sleepInsecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
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
