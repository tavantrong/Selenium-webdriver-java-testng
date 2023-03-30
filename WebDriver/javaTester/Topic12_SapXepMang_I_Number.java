package javaTester;

public class Topic12_SapXepMang_I_Number {

	public static void main(String[] args) {
		int mang[] = {-2,5,3,6,2,8,9,15,20,-10};
		
		for (int i = 0; i < mang.length -1; i++) {
			int number = mang[i];
			for (int j = i + 1; j < mang.length; j++) {
				if (number < mang[j]) {
					mang[i] = mang[j];
					mang[j] = number;
					number = mang[i];
				}
			}
		}
		for (int x : mang) {
			System.out.println(x);
		}
		
		
		//Lay gia tri nho nhat
		int max = 0;
		for (int i = 0; i < mang.length; i++) {
			if (mang[i] > max) {
				max = mang[i];
				}
				
			}
		System.out.println("Gia trị lớn nhất trong mảng: " + max);	
		
		int min = 0;
		for (int i = 0; i < mang.length; i++) {
			if (i == 0) { //Nếu i đang đứng ở vị trí 0 - đầu tiên
				min = mang[i];
			} else if (mang[i] < min) {
				min = mang[i];
			}
		}
		System.out.println("Gia tri nho nhat: " + min);
		

	
	}
}
