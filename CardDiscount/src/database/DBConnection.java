package database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.print.DocFlavor.STRING;

public class DBConnection {

	Connection con = null;
	Statement st = null;
	Statement stt = null;
	ResultSet rs;
	String sql;

	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "root12");
			st = con.createStatement();
			stt = con.createStatement();

			// String sql;
			// sql = "SELECT * FROM user_db";
			// ResultSet rs = st.executeQuery(sql);
			//
			// while (rs.next()) {
			// String name = rs.getString("id");
			// String company = rs.getString("pw");
			// System.out.println("Name : " + name + "\ncompany : " + company + "\n");
			// }
			//
			// rs.close();
			// st.close();
			// con.close();
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� ���� ���� : " + e.getMessage());
		}

		// public boolean isAdmin(String adminID, String adminPassword) {
		// try {
		// String SQL = "SELECT * FROM card WHERE id = '" + adminID + "' and pw ='" +
		// adminPassword +"'";
		// rs = st.executeQuery(SQL);
		// if(rs.next())
		// {
		// return true;
		// }
		// }
		// catch(Exception e)
		// {
		// System.out.println("�����ͺ��̽� �˻� ���� : " + e.getMessage());
		// }
		// return false;
	}

	public boolean alreadyExist(String id) {
		try {
			sql = "SELECT * FROM user_db";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String dbid = rs.getString("id");
				String dbpw = rs.getString("pw");
				System.out.println("Name : " + dbid + "\ncompany : " + dbpw + "\n");
				if (id.equalsIgnoreCase(dbid))
					return true;

			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� ���� ���� : " + e.getMessage());
		}
		return false;
	}

	public boolean register(String id, String pw, String card) {
		if (alreadyExist(id)) {
			System.out.println("�̹� �����ϴ� ���̵��Դϴ�.\n");
		} else {
			try {
				if(id.equalsIgnoreCase("")||pw.equalsIgnoreCase(""))
				{
					System.out.println("ID �Ǵ� PW�� �����Դϴ�.");
					return false;
				}
				sql = "INSERT into user_db values(\'" + id + "\',\'" + pw + "\',\'" + card + "\')";
				st.executeUpdate(sql);
				// user ��ϰ���. ī�������� �Է��ϸ� �����ڿ� ī�嵵 �ִ´�.
				User user = new User(id, pw, card);
				return true;
			}

			catch (Exception e) {
				System.out.println("�����ͺ��̽� ���� ���� : " + e.getMessage());
			}
		}
		return false;
	}

	public void DBexit() {
		try {
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� ���� ���� : " + e.getMessage());
		}
	}

	public boolean login(String id, String pw) {
		try {
			sql = "SELECT * FROM user_db";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String dbid = rs.getString("id");
				String dbpw = rs.getString("pw");
				if (id.equalsIgnoreCase(dbid) && pw.equalsIgnoreCase(dbpw)) {
					System.out.println("ȯ���մϴ�." + id + "��");
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� ���� ���� : " + e.getMessage());
		}
		return false;
	}

	// ī�� �α⵵ ����
	public void increase(String card) {
		try {
			sql = "update cardpopularity_db set popularity = popularity + 1 where popularcard = \'" + card + "\'";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// �̺�Ʈ �����ֱ�

	public void setEventsInProgress() {
		try {
			sql = "SELECT * FROM event_db";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String dbname = rs.getString("name");
				int dbisOn = rs.getInt("isOn");
				Timestamp dbstartdate = rs.getTimestamp("startDate");
				Timestamp dbenddate = rs.getTimestamp("endDate");
				long startTime = System.currentTimeMillis();
				Timestamp now = new Timestamp(startTime);

				System.out.println(dbname);
				System.out.println(dbisOn);
				System.out.println(dbstartdate);
				System.out.println(dbenddate);
				System.out.println(now);

				if (now.after(dbstartdate) && now.before(dbenddate)) {
					String sqll = "update event_db set isOn = 1 where name = \'" + dbname+"\'";
					stt.executeUpdate(sqll);
					System.out.println("������\n");
				} else {
					String sqll = "update event_db set isOn = 0 where name = \'" + dbname+"\'";
					stt.executeUpdate(sqll);
					System.out.println("��\n");
				}
			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� ���� ���� : " + e.getMessage());
		}

	}

	public void checkEventsInProgress() {
		try {
			sql = "SELECT * FROM event_db";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String dbname = rs.getString("name");
				Timestamp dbstartdate = rs.getTimestamp("startdate");
				Timestamp dbenddate = rs.getTimestamp("enddate");
				int dbisOn = rs.getInt("isOn");
				if (rs.getInt("isOn") == 1)
					System.out.println("ī���̸� : " + dbname + " ���۳�¥ : " + dbstartdate + " ����¥ : " + dbenddate+"\n");
			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� ���� ���� : " + e.getMessage());
		}

	}

	public void checkEventsByCardCompany(String company) {
		try {
			sql = "SELECT * FROM event_db where company = \'" + company+"\'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String dbname = rs.getString("name");
				Timestamp dbstartdate = rs.getTimestamp("startdate");
				Timestamp dbenddate = rs.getTimestamp("enddate");
				System.out.print("ī���̸� : " + dbname + " ���۳�¥ : " + dbstartdate + " ����¥ : " + dbenddate);
				if(rs.getInt("isOn")==1)
					System.out.println(" ������");
				else
					System.out.println("�����̺�Ʈ");
			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� ���� ���� : " + e.getMessage());
		}
	}

	// �α�ī�� �����ֱ�

	public void checkCardPopularity() {
		try {
			int count = 1;
			sql = "SELECT * FROM cardpopularity_db ORDER BY popularity DESC";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next() && (count <= 3)) {
				String dbname = rs.getString("popularcard");
				System.out.println("�α� �ִ� ī�� : "+dbname);
				count++;
			}
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� ���� ���� : " + e.getMessage());
		}
	}
	

}
