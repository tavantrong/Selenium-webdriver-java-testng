package api;

public class Topic02_Xpath_Css_II {

	public static void main(String[] args) {
		//https://fptshop.com.vn/dien-thoai/samsung
			//.//div[@class='card fplistbox']//div[starts-with(@class,'cdt-product')][last()] Lấy vị trí cuối
		
		//https://jqueryui.com/resources/demos/selectable/display-grid.html
		//index vs position là như nhau
			//ol[@id='selectable']/li[1] - Lấy bị trí đầu tiên
			//ol[@id='selectable']/li[last()] - Lấy vị trí cuối
			//ol[@id='selectable']/li[6] - Lấy vị trí thứ 6
			//ol[@id='selectable']/li[position()=9] - Lấy vị trí thứ 9
		
		//inside: Nằm trong cùng 1 thẻ cha thì lấy index ra được
			//ul/li[@class='item last'][3]
		
		//Outside: Bọc cái Xpath lại (nó ko cùng 1 cha - ko đồng cấp)
			//  (//a[@class='product-image']/img)[3]
		
		
		//Lấy locator linh động: Dựa vào mối quan hệ giữa các thẻ HTML
			//Dynamic text - đại diện tên sản phẩm
			//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='action']/button
		
			//Dựa vào thông tin cố định lấy ra thông tin dynamic
			//td[text()='Customer ID']/following-siblings::td
		
		//http://automationpractice.com/index.php?controller=order
		//span[text()='Total']/preceding-sibling::span  - Từ thằng em lấy lên anh
	
	
	}		

}

