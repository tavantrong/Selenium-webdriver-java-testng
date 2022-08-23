package javaTester;

public class Topic02_And_Or {

	public static void main(String[] args) {
		boolean nam = true;
		boolean nu = false;
		
		//Phép AND: tất cả điều kiện phải đều đúng - đúng (Sử dụng &&)
		nam = true;
		nu = true;
		System.out.println(nam && nu);
		
			// 1 trong 2 sai - sai
		nam = false;
		nu = true;
		System.out.println(nam && nu);
		
		
		//Phép OR: Chỉ cần 1 trong 2 điều kiện đúng - đúng (Sử dụng ||)
		nam = false;
		nu = true;
		System.out.println(nam || nu);
		
			//OR: Cả 2 sai - sai
		nam = false;
		nu = false;
		System.out.println(nam || nu);
		
		//Xpath_AND: //input[@type='email' and @id='email'] (2 tham số phải đều chính xác)
		//Xpath_OR: //input[@type='email' or @id='email'] (1 trong 2 tham số có sai cũng được)
	}

}

