package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DatabaseConnection;
import vo.Dbuser;

public class LoginDAO {
	private DatabaseConnection dbconn = null;
	private Connection conn = null;
	private Dbuser user = null;
	
	static public final int SUCCESS = 1;
	static public final int EXCEPTION = -1;
	static public final int WRONG_ID = -2;
	static public final int WRONG_PWD = -3;
	
	public LoginDAO() {
		try {
			dbconn = new DatabaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = dbconn.getConnection();
	}
	
	public int login(String u_id, String u_pwd, ArrayList<Dbuser> loginUserList) {
		int message = EXCEPTION;
		String sql = "select * "
				+ "from db_16.user "
				+ "where u_id=? ";
		loginUserList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new Dbuser();
				user.setAll(rs);
				loginUserList.add(0, this.user);
			}
			if(user == null) {
				message = WRONG_ID;
			}
			else if(!user.getU_pwd().equals(u_pwd)) {
				message = WRONG_PWD;
			}
			else {
				message = SUCCESS;
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
}
