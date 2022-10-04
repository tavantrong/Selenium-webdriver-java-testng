package Practice;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic06_Textbox_TextArea_Part_I {
	//Biến toàn cục
	WebDriver driver;
	String LoginURLpage, UserID, Passwordlogin;
	String name, dob, address, city, state, pin, phone, email, password, customerid;
	String editAddress, editPhone;
	
	//Khi mà Browser, app chưa chạy --> ko thể tìm thấy element được
		//WebElement Emailtextbox = driver.findElement(By.name("name")); --> ko thể dùng
	By customernameTextbox = By.name("name");
	By genderTextbox = By.name("gender");
	By dobTextbox = By.name("dob");
	By addressTextbox = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By mobileTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");
	
	@BeforeClass
	//Hàm
	public void beforeClass() {
		driver = new FirefoxDriver();
		//Timeout chờ để tìm element - wait ngầm định
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");
		
		name = "Ritski Keneth";
		dob = "1987-01-01";
		address = "563 Suitable Address";
		city = "New york";
		state = "California";
		pin = "999666";
		phone = "0244777777";
		email = "automationkfc8@gmail.com";
		
		editAddress = "06 hung Dinh - Thuan An";
		editPhone = "0395453620";
		
		}

	@Test
	public void TC_01_Register() {
		LoginURLpage = driver.getCurrentUrl();
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("automationkfc8@gmail.com");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//Get thong tin user & pass luu vao bien toan cuc
		UserID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Passwordlogin = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
	}
	@Test
	public void TC_02_Login() {
		driver.get(LoginURLpage);
		
		//lay gia tri tu bien toan cuc gan vao Element / Form dang nhap
		driver.findElement(By.name("uid")).sendKeys(UserID);
		driver.findElement(By.name("password")).sendKeys(Passwordlogin);
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : "+ UserID +"']")).isDisplayed());
						
		
	}
	@Test
	public void TC_03_New_Customer() throws InterruptedException {
		//Vao tab New Customer va input du lieu hop le. Submit
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		SendkeytoElement(customernameTextbox, name);
		//driver.findElement(CustomernameTextbox).sendKeys(name);
		SendkeytoElement(dobTextbox, dob);
		SendkeytoElement(cityTextbox, city);
		SendkeytoElement(addressTextbox, address);
		SendkeytoElement(stateTextbox, state);
		SendkeytoElement(pinTextbox, pin);
		SendkeytoElement(mobileTextbox, phone);
		SendkeytoElement(emailTextbox, email);
		SendkeytoElement(passwordTextbox, Passwordlogin);
		
		Thread.sleep(3000);
		driver.findElement(By.name("sub")).click();	
		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
		
		//Server process + Response (output)
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	
		customerid = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}
	@Test
	public void TC_04_Edit_Customer() throws InterruptedException {
	
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerid);
		driver.findElement(By.name("AccSubmit")).click();
		
		//Verify 3 field dau Disable
		Assert.assertFalse(isElementEnable(customernameTextbox));
		Assert.assertFalse(isElementEnable(genderTextbox));
		Assert.assertFalse(isElementEnable(dobTextbox));
		
		
		Assert.assertEquals(driver.findElement(customernameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(dobTextbox).getAttribute("value"), dob);
		Assert.assertEquals(driver.findElement(cityTextbox).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(addressTextbox).getText(), address);
		Assert.assertEquals(driver.findElement(stateTextbox).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextbox).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(mobileTextbox).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), email);
		//Edit Customer
		driver.findElement(addressTextbox).clear();
		driver.findElement(addressTextbox).sendKeys(editAddress);
		Thread.sleep(3000);

		driver.findElement(mobileTextbox).clear();
		driver.findElement(mobileTextbox).sendKeys(editPhone);
		Thread.sleep(3000);

		driver.findElement(By.name("sub")).click();		
		
	}

	
	
	
	public void SendkeytoElement(By by,String value) {
		WebElement Element = driver.findElement(by);
		Element.clear();
		Element.sendKeys(value);
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
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
