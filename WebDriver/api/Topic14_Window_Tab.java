package api;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic14_Window_Tab {
	//Biến toàn cục
	WebDriver driver;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}

	
	public void TC_01_Only_Two_Window_or_Tab() {
		driver.get("https://kyna.vn/");
		
		//Before Click - Get ID page
		String kynaID = driver.getWindowHandle();
		System.out.println("ID of page Kyna =" + kynaID);
		
		// Click to FB link at footer
		driver.findElement(By.xpath("//div[@class='social']//img[@alt='facebook']")).click();
		
		// After Click - get all IDs
		Set<String> allIDs = driver.getWindowHandles();
		
		// Switch to FB tab page
		for (String id : allIDs) {
			System.out.println("All IDs = " + id);
			if (!id.equals(kynaID)) { // "!" - Phu dinh (NOT)
				driver.switchTo().window(id);				
			}}
		String facebookID = driver.getWindowHandle();
		System.out.println("ID of page FB =" + facebookID);
		
		//Verify URL is corrected FB
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
		
		// Switch to kyna.vn (Parent page) - vong lap nguoc phia tren or Ham
		switchToWindowByID(facebookID);
		
		// Verify return kyna.vn URL
		Assert.assertTrue(driver.getCurrentUrl().contains("skills.kynaenglish.vn"));

	}
	
	
	@Test
	public void TC_02_More_Than_Two_Window_or_Tab() {
		driver.get("https://kyna.vn/");
		String kynaID = driver.getWindowHandle();
		
		//Click vao Facebook at footer
		driver.findElement(By.xpath("//div[@class='social']//img[@alt='facebook']")).click();
		
		//Switch to Facebook
		swithToWindowByTitle("Kyna.vn | Facebook");
		Assert.assertTrue(driver.getCurrentUrl().contains("facebook.com"));
		
		//Switch ve trang Kyna
		swithToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertTrue(driver.getCurrentUrl().contains("skills.kynaenglish.vn"));
		
		//Click vao youtube
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();
		
		//Switch vao trang Youtube
		swithToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertTrue(driver.getCurrentUrl().contains("youtube.com"));
		
		//Switch ve trang Kyna (*)
		swithToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertTrue(driver.getCurrentUrl().contains("skills.kynaenglish.vn"));
		
		//Close all tab without kyna
		closeAllWindowWithoutParent(kynaID);
		sleepInsecond(3);
		

		
	}
	
	
	public void switchToWindowByID(String windowID) {
		// Lay all ID of Window/Tab
		Set<String> allIDs = driver.getWindowHandles();
		
		//Dung vong lap duyet qua tung gia tri
		for (String ID : allIDs) {
			// Moi lan duyet qua 1 gia tri thi kiem tra dieu kien
			// Neu no khong bang vs gia tri dang so sanh thi..
			if (!ID.equals(windowID)) {
				//..Switch vao window ID do
				driver.switchTo().window(ID);
				// Thoat khoi vong lap - Dat dc dieu kien nen khong can chay tiep
				break;
			}}}

	public void swithToWindowByTitle(String expectedWindowID) {
		Set<String> allIDs = driver.getWindowHandles();
		
		for (String id : allIDs) {
			driver.switchTo().window(id);
			String actualWindowTitle = driver.getTitle();
			if (actualWindowTitle.equals(expectedWindowID)) {
				break;
			}}}
	
	public void closeAllWindowWithoutParent (String windowID) {
		Set<String> allIDs = driver.getWindowHandles();
		
		for (String id : allIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				driver.close();				
			}}}
	
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
