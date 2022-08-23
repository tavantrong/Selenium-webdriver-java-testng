package javaTester;

import java.util.ArrayList;
import java.util.List;

public class Data_Type {
	//Java Console - Giao diện dòng lệnh
	public static void main(String[] args) {
	//Kiểu dữ liệu: data type
	// 1 - Kiểu dữ liệu nguyên thủy (Primitive)
		//Char : nháy đơn + 1 kí tự
		char key = '%';
		//Kí tự
		
		// Số nguyên: Không có phần thập phân
			// Byte : Từ -128 đến 127
			byte numberByte = 127;
			// Short : 
			short numberShort = 6500;
			// Int
			int numberInt = 65000;
			// Long
			long numberLong = 34567899;
			//Lựa chọn đúng dữ liệu để tránh dư thừa or thiếu bộ nhớ gây hư app
			
		// Số thực: có thập phân
			// Float : number + F
			float numberFloat = 15.65F;
			// Double : number + D
			double numberDouble = 16.65D;
		
		// Boolean: 2 giá trị true/false
		// Sex = true: nam/ false: nữ
		boolean status = true;
		
	// 2 - Kiểu dữ liệu tham chiếu (Reference)
		//String : nháy kép "", nhiều kí tự
		String addresss = "123 #$%#$%^# Ho Chi Minh";
		
		//Array: Mảng, gồm nhiều address
		String addresess [] = {"Ho Chi Minh","Ha Noi","Da Nang"};
		
		//Class : Khởi tạo 1 đối tượng cho 1 class - Topic01: Biến, đại diện cho class
		Data_Type topic01 = new Data_Type();
		
		//Object: Đối tượng
		Object a = "Cần thơ";
		
		//Collection (Set/ List /Queue)
		List<String> address = new ArrayList<String>();
		
	}

}
