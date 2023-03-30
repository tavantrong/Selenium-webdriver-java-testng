package javaTester;

public class Topic12_SapXepMang_II_KiTuAlpha {
	public static void main(String[] args) {
	String chuoi = "vietnam";
	//Chuyen chuỗi String sang mảng array
	char arr[] = chuoi.toCharArray();
		for(int i = 0 ; i < arr.length ; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					char temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		System.out.println("Giá trị theo thứ tự " +i+ " là: " +arr[i]);
	}
		
	String chuoi2 = "BbAaDdCc";
	System.out.println("Sắp xếp chuỗi theo thứ tự tăng dần: ");
	char arr2[] = chuoi2.toCharArray();
	for (int i = 0; i < arr2.length; i++) {
		for (int j = i+1; j < arr2.length; j++) {
			if (Character.toLowerCase(arr2[i]) > Character.toLowerCase(arr2[j])) {
				//Giảm dần: Character.toLowerCase(arr2[i]) < Character.toLowerCase(arr2[j])
				//Character.toLowerCase: Chuyển chữ HOA -> hoa
				char temp = arr2[i];
				arr2[i] = arr2[j];
				arr2[j] = temp;
		}
			
		}
		System.out.println(arr2[i]);
	}
}
}
