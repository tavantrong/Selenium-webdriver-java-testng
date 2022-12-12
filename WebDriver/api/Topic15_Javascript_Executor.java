package api;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic15_Javascript_Executor {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	String UserID, Passwordlogin, LoginURLpage;

	@BeforeClass
	//Hàm
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
				
		}

	@Test(enabled = false)
	public void TC_01_Click_Hidden_Element() {
		driver.get("https://zingpoll.com/");
		WebElement vietnameseLanguage = driver.findElement(By.xpath("//li[@class='dropdown hidden-xs']//a[contains(string(),'Tiếng Việt')]"));
		jsExecutor.executeScript("arguments[0].click()", vietnameseLanguage);
		
		//or like this: clickToElementByJS("//li[@class='dropdown hidden-xs']//a[contains(string(),'Tiếng Việt')]");
	}

	@Test(enabled = false)
	public void TC_02() {
		navigateToUrlByJS("http://live.techpanda.org");
		
		//executeForBrowser("domain.domain;") - tra ve la Object nen phai dung return tra ve String de Verify
		String homepageDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(homepageDomain, "live.techpanda.org");
		
		String homepageURL = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(homepageURL, "http://live.techpanda.org/");
		
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		
		hightlightElement("//li[@class='success-msg']//span");
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnDown("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", getEmailRandom());
		
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		
		hightlightElement("//li[@class='success-msg']//span");
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		
		navigateToUrlByJS("https://demo.guru99.com/v4");
		String demoguruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(demoguruDomain, "demo.guru99.com");
		
	}

	
	public void TC_03() {
		navigateToUrlByJS("https://login.ubuntu.com/");
		clickToElementByJS("//button[@id='cookie-policy-button-accept']");
		
		//Jquery - Console : Lay message can Verify
		//var a = $x("//input[@name='email']")[0]; a.validationMessage;
		clickToElementByJS("//button[@data-qa-id='login_button']");
		sleepInsecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@class='textType']"), "Please fill out this field.");
		
		sendkeyToElementByJS("//input[@class='textType']", "aa");
		clickToElementByJS("//button[@data-qa-id='login_button']");
		sleepInsecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@class='textType']"), "Please include an '@' in the email address. 'aa' is missing an '@'.");
		
		sendkeyToElementByJS("//input[@class='textType']", "aa@");
		clickToElementByJS("//button[@data-qa-id='login_button']");
		sleepInsecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@class='textType']"), "Please enter a part following '@'. 'aa@' is incomplete.");
		
		sendkeyToElementByJS("//input[@class='textType']", "123@...");
		clickToElementByJS("//button[@data-qa-id='login_button']");
		sleepInsecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@class='textType']"), "'.' is used at a wrong position in '...'.");
		
		sendkeyToElementByJS("//input[@class='textType']", "trong@gmail.com");
		clickToElementByJS("//button[@data-qa-id='login_button']");
		sleepInsecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@name='password']"), "Please fill out this field.");
		
		}
	@Test
	public void TC_03_Remove_attribute() {
		By customernameTextbox = By.name("name");
		By dobTextbox = By.name("dob");
		By addressTextbox = By.name("addr");
		By cityTextbox = By.name("city");
		By stateTextbox = By.name("state");
		By pinTextbox = By.name("pinno");
		By mobileTextbox = By.name("telephoneno");
		By emailTextbox = By.name("emailid");
		By passwordTextbox = By.name("password");
		
		String name = "Ritski Keneth";
		String dob = "1987-01-01";
		String address = "563 Suitable Address";
		String city = "New york";
		String state = "California";
		String pin = "999666";
		String phone = "0244777777";
		String email = getEmailRandom(); //thong tin co dinh thi phai luu vao bien
		
		driver.get("https://demo.guru99.com/v4/");
		LoginURLpage = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//Get thong tin user & pass luu vao bien toan cuc
		UserID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Passwordlogin = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		driver.get(LoginURLpage);
		//lay gia tri tu bien toan cuc gan vao Element / Form dang nhap
		driver.findElement(By.name("uid")).sendKeys(UserID);
		driver.findElement(By.name("password")).sendKeys(Passwordlogin);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : "+ UserID +"']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		sleepInsecond(2);

		// Remove attribute (type=date)
		removeAttributeInDOM("//input[@name='dob']", "type");
		sleepInsecond(2);
		
		//input data
		driver.findElement(customernameTextbox).sendKeys(name);
		driver.findElement(dobTextbox).sendKeys(dob);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(addressTextbox).sendKeys(address);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(mobileTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(Passwordlogin);
	}
	
	
	public void sleepInsecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInsecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	//Ham sendkey JS # sendkey Selenium - thinh thoang sendkey roi nhung van ra gia tri Empty
	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public String getEmailRandom() {
		Random rand = new Random();
		return "Join" + rand.nextInt(999) + "@gmail.com";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
