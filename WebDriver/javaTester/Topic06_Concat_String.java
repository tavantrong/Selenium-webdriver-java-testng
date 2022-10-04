package javaTester;

public class Topic06_Concat_String {

	public static void main(String[] args) {
		//khai bao bien
		String userid = "mngr303746";
		
		//Noi bien vao chuoi xpath
		String xpath = "//td[text()='Manage ID: "+ userid +"']";
		
		//Xem ket qua tren Console
		System.out.println(xpath);
		

	}

}
