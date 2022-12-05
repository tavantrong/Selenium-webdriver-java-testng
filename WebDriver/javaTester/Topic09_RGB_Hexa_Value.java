package javaTester;

import org.openqa.selenium.support.Color;

public class Topic09_RGB_Hexa_Value {

	public static void main(String[] args) {
		//Lay ra gia tri RGB
		System.out.println("Gia tri RGB: " + getRGBValue("#03a9f4"));
		
		//Lay ra gia tri Hexa tu Element tren Web hien thi (RGB)
		System.out.println("Gia Tri Hexa: " + getHexaValue("rgb(3, 169, 244)"));

	}
	
	
	
	
	
	public static String getRGBValue(String hexaValue) {
		//Chuyen gia tri tu Hexa sang RGB
		return Color.fromString(hexaValue).asRgb();
	}
	
	public static String getHexaValue(String rgbValue) {
		//Lay ra gia tri Hexa de Verify mau
		return Color.fromString(rgbValue).asHex();
	}

}
