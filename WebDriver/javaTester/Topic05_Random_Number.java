package javaTester;

import java.util.Random;

public class Topic05_Random_Number {

	public static void main(String[] args) {
		// java Class - Tao tham so ngau nhien 
		Random rand = new Random();
		System.out.println(rand.nextInt(999999));  		//Lay random so ngau nhien duoi 7 so (0 - 999999)
		System.out.println(rand.nextInt(999999));
		
		//Random email (tao ngau nhien email)
			//Format: Prefix + random + postfix (webmail server: github/ gmail/ hotmail/..)
		System.out.println("auto" + rand.nextInt(999999) + "@gmail.com" );
		System.out.println("auto" + rand.nextInt(999999) + "@gmail.com" );
		System.out.println("auto" + rand.nextInt(999999) + "@gmail.com" );


	}

}
