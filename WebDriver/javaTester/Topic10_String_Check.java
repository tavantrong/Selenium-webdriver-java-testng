package javaTester;

import org.testng.Assert;

public class Topic10_String_Check {

	public static void main(String[] args) {
		// Java
		// java
		// JAVA
		// JaVa
		// Java
		String articleTitle = "Java";
		//Cach verify ko phan biet viet Hoa - viet thuong
		Assert.assertTrue(articleTitle.equalsIgnoreCase("Java"));
		Assert.assertTrue(articleTitle.equalsIgnoreCase("java"));
		Assert.assertTrue(articleTitle.equalsIgnoreCase("JAVA"));
		Assert.assertTrue(articleTitle.equalsIgnoreCase("JaVa"));

	}

}
