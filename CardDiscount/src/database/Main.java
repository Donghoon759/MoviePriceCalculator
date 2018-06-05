package database;

public class Main {
	
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 720;

	public static void main(String[] args) {
		
		//DBConnection connection = new DBConnection();
		new DynamicBeat();
		
//		System.out.println("관리자 여부 : " + connection.isAdmin("admin", "admin"));
	}

}
