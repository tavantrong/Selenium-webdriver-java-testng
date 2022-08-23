package api;

public class Topic02_Xpath_Css_III {

	public static void main(String[] args) {
	//Using Xpath and CSS
		
		//Direct child (Đi 1 node)
			//Xpath: //div/input[@id='email']
			//CSS: div>input[id='email']
		//Sub child (nhiều node)
			//Xpath: //ul/input[@id='email']
			//CSS: ul input[id='email']		
		
		//Dùng tham số ID
			//Xpath: //input[@id='email']
			//CSS: input[id='email'] - input#email - #email
		//Dùng tham số Class
			//Xpath: //div[@class='footer']
			//CSS: div[class='footer'] - div.footer - .footer
				//Class nhiều giá trị: .field.name-firstname - .field - .name-firstname
		//Dùng nhiều attribute
			//Xpath: //input[@id='newsletter' and @name='email']
			//CSS: input[id='newsletter'][name='email'][title='Sign up for our newletter']
		
		//Lấy position:
			//Xpath: //ol[@id='selectable']/li[6] - //ol[@id='selectable']/li[position()=6]
			//CSS: li:nth-child(6)
		//Lấy vị trí đầu - cuối
			//Xpath: //ol[@id='selectable']/li[1] - //ol[@id='selectable']/li[last()]
			//CSS: li:first-child - li:last-child
		//Sử dụng tham số Contains
			//Xpath: //li[contains[@class,'ui-selected']
			//CSS: li[class*='ui-selected']  - CSS ko áp dụng cho text được, chỉ sử dung cho attribute
		//Sử dụng hàm Starts-with
			//Xpath: //li[starts-with(@class='ui-state')]
			//CSS: li[class^='ui-state']
		//Sử dụng End with (Xpath not support)
			//CSS: li[class$='ui-selected']
		
		//Hàm Following-sibling (anh xuống em)
			//Xpath: //label[@for='firstname']/following-sibling::div/input
			//CSS: label[for='firstname']+div>input    (Single 1 node)
		//Hàm Following-sibling - nhiều node (CSS: ~)
			//Xpath: //div[@class='field name-firstname']/following-sibling::div
			//CSS: div.name-firstname~div (lấy xuống nhiều node)
		
		//Hàm OR
			//Xpath: //input[@id='password' or @name='password']
			//CSS: #password,.validate-password   (, + .class) - #password,input[name='password']
		
		
		
		
	}

}

