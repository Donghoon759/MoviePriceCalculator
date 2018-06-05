package database;

import java.sql.*;

public class DBConnection {
	
	Connection con = null;
	Statement st = null;
	ResultSet rs;
	
	public DBConnection() {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB?useSSL=false", "root", "root12");
			st = con.createStatement();
			
			String sql;
			sql = "SELECT * FROM user_db";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				String name = rs.getString("id");
				String company = rs.getString("pw");
				System.out.println("Name : "+ name + "\ncompany : "+company+ "\n");
			}
			
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("데이터베이스 연결 오류 : " + e.getMessage());
		}
	
//	public boolean isAdmin(String adminID, String adminPassword) {
//		try {
//			String SQL = "SELECT * FROM card WHERE id = '" + adminID + "' and pw ='" + adminPassword +"'";
//			rs = st.executeQuery(SQL);
//			if(rs.next())
//			{
//				return true;
//			}
//		}
//		catch(Exception e)
//		{
//			System.out.println("데이터베이스 검색 오류 : " + e.getMessage());
//		}
//		return false;
	}
}
