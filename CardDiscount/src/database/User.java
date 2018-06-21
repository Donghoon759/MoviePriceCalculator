package database;

public class User {
//	Boolean isLogin;
	String id;
	String pw;
	String card;
	
	public User(String id, String pw, String card)
	{
		this.id = id;
		this.pw = pw;
		this.card = card;
	}
	
	public User(String id, String pw)
	{
		this.id = id;
		this.pw = pw;
	}
	//유저의 카드정보가 있을 수도 있고 없을 수도 있고. 생성자.
	
//	public void login (String id, String pw) {
//		
//	}
//	
//	public void register (String id, String pw) {
//		
//	}
	
	public void checkEventsInProgress() {
	}
	
	
	public void checkEventsByCinema() {
		
	}
	
	public void checkEventsByCardCompany() {
		
	}
	
	public void showCardPopularityUpToThird() {
		
	}
	
	public void calculateMoviePrice() {
		
	}
	
	public void offerTicketingLink() {
		
	}
	
	

}
