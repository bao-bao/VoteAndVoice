package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbc.DatabaseConnection;
import vo.Dbuser;

public class RegisterDAO {
	private DatabaseConnection dbconn = null;
	private Connection conn = null;

	static public final int EXCEPTION = -1;
	static public final int AVAILABLE = 1;
	static public final int EXIST = -2;
	static public final int SUCCESS = 1;
	
	public RegisterDAO() {
		try {
			dbconn = new DatabaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = dbconn.getConnection();
	}
	
	public int checkIdAvailable(String u_id) {
		int message = EXIST;
		String sql = "select * "
				+ "from db_16.user "
				+ "where u_id=? ";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, u_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				message = EXIST;
			}
			else {
				message = AVAILABLE;
			}
		} catch (SQLException e) {
			message = EXCEPTION;
			System.out.println("MySQL fault.");
			e.printStackTrace();
		} finally {
			try {
				dbconn.close();
			} catch (Exception e) {
				message = EXCEPTION;
				e.printStackTrace();
			}
		}
		return message;
	}

	public int register(String u_id, String u_pwd, 
			//BigDecimal v_exp, BigDecimal v_level, BigDecimal v_coin, String u_validity, 
			String u_name, String u_gender, String u_phone, 
			String u_ad_country, String u_ad_province, String u_ad_city, String u_ad_street) {
		int message = EXCEPTION;
		String sql = "insert into db_16.user(u_id, u_pwd, u_name, u_gender, u_phone, u_ad_country, u_ad_province, u_ad_city, u_ad_street) "
				+ "values(?,?,?,?,?,?,?,?,?) ";
		try {
			int checkMessage = new RegisterDAO().checkIdAvailable(u_id);
			if(checkMessage < 0) {
				message = checkMessage;
				dbconn.close();
				return message;
			}
			PreparedStatement pstmt = conn.prepareStatement(sql);
			String[] paraList = {u_id, u_pwd, u_name, u_gender, u_phone, u_ad_country, u_ad_province, u_ad_city, u_ad_street};
			for(int i = 0; i != paraList.length; i++) {
				if(paraList[i] == null) {
					paraList[i] = "";
				}
				pstmt.setString(i + 1, paraList[i]);
			}
			int i = pstmt.executeUpdate();
			if(i > 0) {
				message = SUCCESS;
			}
			else {
				message = EXIST;
			}
		} catch (Exception e) {
			message = EXCEPTION;
			System.out.println("MySQL fault.");
			e.printStackTrace();
		} finally {
			try {
				dbconn.close();
			} catch (Exception e) {
				message = EXCEPTION;
				e.printStackTrace();
			}
		}
		return message;
	}
	public int register(Dbuser user) {
		return this.register(user.getU_id(), user.getU_pwd(), user.getU_name(), user.getU_gender(), user.getU_phone(), user.getU_ad_country(), user.getU_ad_province(), user.getU_ad_city(), user.getU_ad_street());
	}
}
