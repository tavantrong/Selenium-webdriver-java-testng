package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic05_Browser_Element_Part_I {
	WebDriver driver;
		
	

  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TC01_WebElement_Command() {
	  
	  // 1. Thao tác trực tiếp trên Element + ko cần khai báo biến
	  //Chỉ dùng 1 lần
	  driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	  driver.findElement(By.cssSelector("#email")).sendKeys("automationtesting@yahoo.com");
	  
	  // 2. Khai báo biến rồi mới thao tác các action (click/ sendkey/ getText/ Select/ ...)
	  //Dùng nhiều lần đỡ phải code nhiều lần
	  WebElement Emailtextbox = driver.findElement(By.cssSelector("#email"));
	  
	  	//Nhập email
	  	Emailtextbox.sendKeys("Nhapemail@yahoo.com");
	  	//Xóa
	  	Emailtextbox.clear();
	  	//Nhập email lần 2
	  	Emailtextbox.sendKeys("Nhapemaillan2@yahoo.com");
	  
	  
	  	// Thao tác với 1 element - driver.findElement : tìm và thao tác với chỉ 1 element đầu tiên cho dù tìm được nhiều 
	  
	  	//Thao tác với nhiều element (2 trở lên): List <webElement>
	  	//driver.findElements - Thao tác với nhiều element tìm được
	  	driver.get("https://automationfc.github.io/multiple-fields/");
	  	
	  	List <WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']")); // Crtl + Shift + O - Chọn java.util.List
	  	System.out.println("Tổng số lượng checkbox tại page này = " + checkboxes.size()); //Size() - Lấy ra số lượng của đối tượng
	  	
	  	for (WebElement Clickcheckbox : checkboxes) {
	  		Clickcheckbox.click(); //Click vào 29 cái checkbox trên
	  	}
	  	
	  //3. Các command cho element
	  WebElement Element = driver.findElement(By.cssSelector(""));
	  //WebElement Element = driver.findElement(By.Xpath(""));
	  
	  //1. Action
	  //Xoa du lieu
	  Element.clear();
	  //Click vao element (checkbox / button / radio button / link / image / dopdown /..)
	  Element.click();
	  //Nhap du lieu vao 1 textbox / textarea / dropdown (list editable)
	  Element.sendKeys("Anh Pham");
	  //ENTER vao trong 1 FORM (Chi dung duoc voi FORM)
	  Element.submit();
	  
	  //2. Lay du lieu (get...)
	  //Lay gia tri Attribute cua element
	  Element.getAttribute("placeholder"); //Search entore store here..
	  //Lay ra Style cua 1 element (font / size / color / background / -> GUI)
	  Element.getCssValue("background"); //#3399cc
	  Element.getCssValue("font-size"); //13px
	  
	  //GUI
	  Element.getLocation(); //Vi tri
	  Element.getRect();
	  Element.getSize(); //Kich thuoc Chieu rong, chieu cao
	  
	  //Chup hinh loi dua vao report --> Framework
	  //Element.getScreenshotAs(target);
	  
	  //Lay ra ten the
	  Element.getTagName(); //div / input...
	  //Lay ra text cua 1 label / header / span / div /...
	  Element.getText();
	  
	  //3. Kiem tra du lieu (is..) - Luu no vao 1 bien de kiem tra du lieu
	  //Kiem tra bang nhau (Equal)
	  String emailtextboxTagname = Element.getTagName();
	  Assert.assertEquals(emailtextboxTagname, "input");
	  //Kiem tra tinh dung sai (True / False)
	    //Kiem tra element mong muon dang hien thi
	  	Assert.assertTrue(Element.isDisplayed());
	  	//Kiem tra element khong mong muon hien thi
	  	Assert.assertFalse(Element.isDisplayed());
	  	
	  //Kiem tra element co the thao tac duoc
	  Assert.assertTrue(Element.isEnabled());
	  //Da duoc chon thanh cong (Radio / Checkbox)
	  Assert.assertTrue(Element.isSelected());
	  
	  	
	  	
	 
	  
	  
	 
	  	
	  	
  }
  
  
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
