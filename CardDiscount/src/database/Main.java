package database;

public class Main {
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	public static void main(String[] args) {
		
		//DBConnection connection = new DBConnection();
		new DynamicBeat();
//		connection.alreadyExist("admin");
//		connection.register("Jihoon","Jihooon","NH채움");
//		connection.login("Jihoon", "Jihooon");
//		connection.increase("NH");
//		connection.checkEventsInProgress();
//		connection.checkEventsByCardCompany("NH");
//		connection.checkCardPopularity();
		
//		System.out.println("관리자 여부 : " + connection.isAdmin("admin", "admin"));
	}

}
