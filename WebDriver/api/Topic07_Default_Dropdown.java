package api;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic07_Default_Dropdown {
	//Biến toàn cục
	WebDriver driver;
	Select select;
	String firstName, lastName, emailAddress, companyName, password; 
	String date, month, year;
	
	@BeforeClass
	//Hàm
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		firstName = "John";
		lastName = "Smith";
		
		companyName = "Lazada";
		password = "123456";
		
		date = "30";
		month = "January";
		year = "1987";
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		
		}

	@Test(invocationCount = 2) //Chay lai TC nay 2 lan
	public void TC_01_Register() {
		
		// 1 . Register
		driver.findElement(By.cssSelector(".ico-register")).click();
		
		// 2 . Dien thong tin cac field Required
		ClicktoCheckboxorRadio(By.cssSelector("#gender-male"));
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		//Khoi tao bien Select de thao tac voi Dropdown Date
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));

			//Su dung index: Chon ngay 30 (Day: 1 + 31 gia tri = 32)
			select.selectByIndex(31); //Chon ngay 30
			SleepinSecond(3);
			//Su dung Value: Chon ngay 15
			select.selectByValue("15"); //String - phai them ""
			SleepinSecond(3);
			//Su dung Text: Chon ngay 30
			select.selectByVisibleText(date); //String
			SleepinSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "30");

		//Khoi tao bien Select de thao tac voi Dropdown Month
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));	
		select.selectByVisibleText(month); //January (value 0 = text "Month")
		SleepinSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		//Kiem tra Dropdown nay khong ho tro multiple
		Assert.assertFalse(select.isMultiple());
		
		//select.deselectAll(); - Dung bo chon Multiple dropdoen nhieu select
		//select.getAllSelectedOptions(); - Lay ra tat ca multiple option duoc chon
		
		//select.getFirstSelectedOption().getText(); - Lay ra Text da chon thanh cong
		//select.getOptions(); - Lay ra tat ca Option trong Dropdown list
		List<WebElement> allItem = select.getOptions();
		Assert.assertEquals(allItem.size(), 13); //Kiem tra Dropdown text "Month" + 12 thang = 13
			//In ra ket qua
			for (WebElement item : allItem) {
				System.out.println(item.getText());
			}
		
		//Khoi tao bien Select de thao tac voi Dropdown Year
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		SleepinSecond(3);
		//khai bao bien email random tai day de chay nhieu lan
		emailAddress = "Johnsmith" + getRandomnumber() + "@gmail.com";
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		SleepinSecond(3);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		SleepinSecond(3);
		ClicktoCheckboxorRadio(By.id("Newsletter"));
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

		
		// 3 . Dang ky
		driver.findElement(By.id("register-button")).click();
		
		// 4 . Kiem tra xuat hien message dang ky thanh cong
		Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(), "Your registration completed");
		
		// 5 . vao trang My Account
		driver.findElement(By.cssSelector(".ico-account")).click();
		
		//Sau khi click xong - chuyen sang trang HTML khac - bat buoc phai tim element lai --> loi : ko tim thay element
		// Refesh / load lai trang - HTML update lai
		
		// 6 . Kiem tra dung voi thong tin dang ky
		Assert.assertTrue(driver.findElement(By.cssSelector("#gender-male")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		driver.findElement(By.cssSelector(".ico-logout")).click();
	}
	
	
	public void ClicktoCheckboxorRadio(By by) {
		WebElement Element = driver.findElement(by);
		// tao ham if - neu chua duoc chon ---> chon
		if(!Element.isSelected());{
			Element.click();
		}}
	public void UnchecktoCheckbox(By by) {
		WebElement Element = driver.findElement(by);
		// tao ham if - neu duoc chon ---> Bo chon
		if(Element.isSelected());{
		Element.click();
	}}
	public void SleepinSecond(long second)  {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public int getRandomnumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
