package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbc.DatabaseConnection;

public class ChangePwdDAO {
	private DatabaseConnection dbconn = null;
	private Connection conn = null;

	static public final int EXCEPTION = -1;
	static public final int SUCCESS = 1;
	static public final int FAILED = -2;
	
	public ChangePwdDAO() {
		try {
			dbconn = new DatabaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = dbconn.getConnection();
	}
	
	public int changePwd(String u_id, String u_pwd_old, String u_pwd_new) {
		int message = EXCEPTION;
		String sql = "update db_16.user "
				+ "set u_pwd = ? "
				+ "where u_id = ? and u_pwd = ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_pwd_new);
			pstmt.setString(2, u_id);
			pstmt.setString(3, u_pwd_old);
			int i = pstmt.executeUpdate();
			if(i > 0) {
				message = SUCCESS;
			}
			else {
				message = FAILED;
			}
		} catch (SQLException e) {
			message = EXCEPTION;
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
