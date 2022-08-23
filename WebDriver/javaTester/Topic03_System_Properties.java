package javaTester;

public class Topic03_System_Properties {

	public static void main(String[] args) {
		//Đường dẫn project
		String projectLocation = System.getProperty("user.dir");
		//Window name
		String OSname = System.getProperty("os.name");
		System.out.println(OSname);

		System.out.println(projectLocation);
		System.out.println(projectLocation + "\\browserDrivers\\chromedriver.exe");

	}

}
