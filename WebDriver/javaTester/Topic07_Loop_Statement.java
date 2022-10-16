package javaTester;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Topic07_Loop_Statement {

	public static void main(String[] args) {
		// Cách 1: Mảng Array
		String[] Student = {"Trường","Tiến","Thơm","Thi","Bảo"};
		
		// Lấy ra thông tin từ mảng Array
		for (int i = 0; i < Student.length; i++) {
			//System.out.println(Student[i]);
		}
		
		// In ra từ 0 - 10
		for (int i = 0; i <= 10; i++) {
			//System.out.println(i);
		}
		
		// cách 2: tạo list và add vào
		List<String> address = new ArrayList<String>();
		address.add("Tài");
		address.add("Tuấn");
		address.add("Trọng");
		
		for (Iterator iterator = address.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
			
		}
		for (String bientamthoi : address) { //For each
			System.out.println(bientamthoi);
		}

	}

}
