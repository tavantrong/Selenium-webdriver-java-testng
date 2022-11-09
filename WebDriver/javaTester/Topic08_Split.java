package javaTester;

public class Topic08_Split {

	public static void main(String[] args) {
		String url = "http://the-internet.herokuapp.com/basic_auth";
		
		String[] newUrl = url.split("//");
		// http:
		// http://the-internet.herokuapp.com/basic_auth
		
		url = newUrl[0] + "//" + "admin" + ":" + "admin" + "@" + newUrl[1];
		
		System.out.println(url);

	}

}
