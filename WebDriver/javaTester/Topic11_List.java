package javaTester;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Topic11_List {
	static WebDriver driver;
	public static void main(String[] args) {
		List<String> addresses = new ArrayList<>();
		addresses.add("Ho Chi Minh");
		addresses.add("Ha Noi");
		addresses.add("Da Nang");
		
		System.out.println("Tổng số addresses = " + addresses.size());
		Assert.assertEquals(addresses.size(), 3);
		
		List<WebElement> checkboxes;
		checkboxes = driver.findElements(By.xpath(""));
		((WebElement) checkboxes).click();
	}
	
}
