package api;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_Custom_DropDown_Part_II {
	//Biến toàn cục
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	
	String month = "February";
	String[] TC01months = {"March", "May", "July"};
	String[] TC02months = {"March", "May", "July", "September"};
	String[] TC03months = {"[Select all]"};
	
	
	
	@BeforeClass
	//Hàm
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Khai báo driver sau để lấy biến driver
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
		}

	
	
	
	@Test
	public void TC06_multiSelectDropdown() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		selectItemInCustomDropdown("(//div[@class=\"ms-parent multiple-select\"])[1]", "(//div[@class=\"ms-parent multiple-select\"])[1]//li//span", TC01months);
		sleepInsecond(2);
		areItemSelected(TC01months);
		
		driver.navigate().refresh();
		
		selectItemInCustomDropdown("(//div[@class=\"ms-parent multiple-select\"])[1]", "(//div[@class=\"ms-parent multiple-select\"])[1]//li//span", TC02months);
		sleepInsecond(2);
		areItemSelected(TC02months);
		
		driver.navigate().refresh();

		selectItemInCustomDropdown("(//div[@class='ms-parent multiple-select'])[1]", "(//div[@class='ms-parent multiple-select'])[1]//li//span", TC03months);
		sleepInsecond(2);
		areItemSelected(TC03months);
		
		
	}
	
	
		
	public void selectItemInCustomDropdown(String parentXpass, String allItemXpath, String[] months) {
		// Click vào Dropdown
		driver.findElement(By.xpath(parentXpass)).click();
		sleepInsecond(2);
		// Chờ cho các item được hiển thị ra
		
		
		//Lấy hết item con đưa vào 1 list để duyệt qua
		List<WebElement> allItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		
		//Dùng vòng lặp duyệt qua từng item - For each
		for (String month : months) {
			//Lấy giá trị của vòng lặp trên sử dụng cho vòng lặp dưới
			for (WebElement item : allItem)
			if (item.getText().equals(month)) {
				item.click();
				sleepInsecond(1);
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
	public boolean areItemSelected(String[] months) {
        List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
        //Lấy ra số lượng checkbox đã chọn = 3(TC01) - 4(TC02) - 13(TC03)
        int numberItemSelected = itemSelected.size();
        
        //Text sau khi chọn xong
        String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
        //Show ket qua trên Console
        System.out.println("Text da chon = " + allItemSelectedText);
        
        //Nếu số lượng item đã chọn get ra lớn hơn 0 và nhỏ hơn 3
        if (numberItemSelected <= 3 && numberItemSelected > 0) {
                boolean status = true;
                for (String item : months) {
                        if (allItemSelectedText.contains(item)) {
                                status = false;
                                return status;
                        }
                }
                return status;
        } else if (numberItemSelected >= 12) {
                return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
        } else if (numberItemSelected > 3 && numberItemSelected < 12) {
                return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
        } else {
                return false;
        }
}

	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
