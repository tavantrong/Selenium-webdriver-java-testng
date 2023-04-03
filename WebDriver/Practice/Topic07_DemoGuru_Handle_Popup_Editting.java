package Practice;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_DemoGuru_Handle_Popup_Editting {
	//Biến toàn cục
	WebDriver driver;
	String LoginURLpage, UserID, Passwordlogin;
	String name, dob, address, city, state, pin, phone, email, password, customerid;
	String editAddress, editPhone;
	String projectLocator = System.getProperty("user.dir");
		
	@BeforeClass
	//Hàm
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectLocator + "\\browserDrivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/index.php");
		email = "automationkfc" + getRandomNumber() + "@gmail.com";	
	}

	@Test
	public void TC_01_Register() {
		LoginURLpage = driver.getCurrentUrl();
		
		clickToElement("//a[text()='here']");
 		sleepInsecond(2);
 		WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0']"));
 	    driver.switchTo().frame(frame1);
 		clickToElement("//div[@id='dismiss-button']");
		  
		  
		  sendkeyToElement("//input[@name='emailid']", email);
		  clickToElement("//input[@type='submit']");
		  
		  UserID = getTextElement("//td[text()='User ID :']/following-sibling::td");
		  Passwordlogin =
		  getTextElement("//td[text()='Password :']/following-sibling::td");
			
	}
	
	@Test
	public void TC_02_Login() {
		driver.get(LoginURLpage);
		
		sendkeyToElement("//input[@name='uid']", UserID);
		sendkeyToElement("//input[@name='password']", Passwordlogin);
		clickToElement("//input[@name='btnLogin']");
		
		isElementDisplayed("//td[text()='Manger Id : "+ UserID +"']");					
	}
	

		
	
	public void sendkeyToElement(String locator,String value) {
		WebElement Element = driver.findElement(By.xpath(locator));
		Element.clear();
		Element.sendKeys(value);
	}
	
	public String getTextElement(String locator) {
		WebElement Element = driver.findElement(By.xpath(locator));
		return Element.getText();
	}
	
	
	//Tham so check Enable
		public boolean isElementEnable(By by) {
			WebElement element = driver.findElement(by);
			if (element.isEnabled()) {
				System.out.println(by + " is Enable");
				return true;
			}
			else {
				System.out.println(by + " is Disable");
				return false;
			}}
		
		public WebElement getElement(String locator) {
			WebElement element = driver.findElement(By.xpath(locator));
			return element;
			}
		
		public boolean isElementDisplayed(String locator) {
			WebElement element = driver.findElement(By.xpath(locator));
			if (element.isDisplayed()) {
				return true;
			} else {
				return false;
			}}
		
		public void clickToElement(String locator) {
			WebElement element = driver.findElement(By.xpath(locator));
			element.click();
		}
		
		public int getRandomNumber() {
			Random rand = new Random();
			return rand.nextInt(99999);
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
