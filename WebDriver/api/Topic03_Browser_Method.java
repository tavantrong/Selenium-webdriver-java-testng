package api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic03_Browser_Method {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	
@Test
public void TC01_Run_on_firefox() {
	//Firefox ver 47 + Selenium 2.x.x + Không dùng Geckogriver
	driver = new FirefoxDriver();
	
	//Firefox ver 48 or mới nhất + Selenium 3.141.59 + Geckogriver tương ứng
	//TestNG thư viện (ko phải là TestNG Plugin)
	System.setProperty("webdriver.gecko.driver", "//path_of_geckodriver");
	//Sau do set driver = new FirefoxDriver();
	driver.get("https://www.google.com.vn/");
	driver.quit();
	
	
}
@Test	
public void TC02_Run_on_Chrome() {
	//Set driver - Dùng cho 1 máy
	//System.setProperty("webdriver.chrome.driver", "F:\\Automation\\02 - Selenium API\\browserDrivers\\chromedriver.exe");
	
	//Set driver - Dùng cho nhiều máy (dynamic link)
	//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
	//. - Đại diện cho project location
	
	//Set driver - Dùng cho nhiều máy (Sử dụng biến set sẵn "projectLocation")
	System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");

	driver = new ChromeDriver();
	driver.get("https://www.google.com.vn/");
	driver.quit();	
	
}
	
	
	
	
}
