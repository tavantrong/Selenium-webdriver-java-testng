package api;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic08_Custom_DropDown_Part_I {
	//Biến toàn cục
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	
	@BeforeClass
	//Hàm
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Khai báo driver sau để lấy biến driver
		explicitWait = new WebDriverWait(driver, 30);
		
		//Khai bao driver cua JavascriptExecutor
		jsExecutor = (JavascriptExecutor) driver;
		
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
	
	@Test (enabled = false)
	public void TC02_Nopcommerce() {
		driver.get("https://demo.nopcommerce.com/register");
		selectItemInCustomDropdown("//select[@name='DateOfBirthYear']", "//select[@name='DateOfBirthYear']//option", "1987");
		selectItemInCustomDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']//option", "19");
		selectItemInCustomDropdown("//select[@name='DateOfBirthMonth']", "//select[@name='DateOfBirthMonth']//option", "March");
		
		sleepInsecond(4);
	}

	public void TC03_Angular() {
	//Angular 2.xx ko làm việc với Firefox bản cũ
	//Hàm getText của Selenium không get dc giá trị bị ẩn - Trick: Dùng javascript
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Basketball");
		sleepInsecond(2);
		Assert.assertEquals(getAngularDropdownSelectedtext(), "Basketball");
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Snooker");
		sleepInsecond(2);
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Football");
		sleepInsecond(2);

	}
	
	@Test(enabled = false)
	public void TC04_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@class='visible menu transition']//span", "Stevie Feliciano");
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Feliciano");
		
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@class='visible menu transition']//span", "Matt");
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Matt");
		
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@class='visible menu transition']//span", "Jenny Hess");
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Jenny Hess");
	}
	
	@Test(enabled = false)
	public void TC05_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
		
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		sleepInsecond(2);
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		sleepInsecond(2);
	}
	
	@Test(enabled = false)
	public void TC05_Editable_Dropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemInCustomDropdown("//div[@class='ui fluid search selection dropdown']", "//div[@class='visible menu transition']//span", "Aland Islands");
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Aland Islands");
	}
	
	@Test
	public void TC05_Editable_Tab_Key_2() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectItemEditDropdownbyTab2("//input[@type='text']", "Aland Islands");
		sleepInsecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Aland Islands");
	
	}
	
	public void selectItemInEditDropdown(String parentXpass, String allItemXpath, String expectedText) {
		driver.findElement(By.xpath(parentXpass)).clear();
		driver.findElement(By.xpath(parentXpass)).sendKeys(expectedText);
		sleepInsecond(2);
		
		List<WebElement> allItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		//Dùng vòng lặp duyệt qua từng item - For each
		for (WebElement item : allItem) {
			if (item.getText().equals(expectedText)) {
				item.click();
				sleepInsecond(2);
				break;
	}}}
	public void selectItemEditDropdownbyTab2(String parentXpass, String expectedText) {
		driver.findElement(By.xpath(parentXpass)).sendKeys(expectedText);
		sleepInsecond(2);
		driver.findElement(By.xpath(parentXpass)).sendKeys(Keys.TAB);
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
			e.printStackTrace();
		}
	}
	public String getAngularDropdownSelectedtext() {
		//Hàm chuyển từ Javascript về giá trị Selenium có thể hiểu được
		return (String) jsExecutor.executeScript("return document.querySelector(\"select[name='games']>option[selected]\").text");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
