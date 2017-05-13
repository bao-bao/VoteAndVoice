package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DatabaseConnection;
import vo.Dbuser;

public class UserInfoDAO {
	private DatabaseConnection dbconn = null;
	private Connection conn = null;
	
	static public final int SUCCESS = 1;
	static public final int EXCEPTION = -1;
	static public final int WRONG_ID = -2;

	public UserInfoDAO() {
		try {
			dbconn = new DatabaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = dbconn.getConnection();
	}
	
	public int getUserInfo(String u_id, ArrayList<Dbuser> userList) {
		int message = EXCEPTION;
		String sql = "select * "
				+ "from db_16.user "
				+ "where u_id=? ";
		userList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Dbuser user = new Dbuser();
				user.setAll(rs);
				userList.add(user);
				message = SUCCESS;
			}
			if(userList.size() == 0) {
				message = WRONG_ID;
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
	
	public int getUserInfoByName(String u_name, ArrayList<Dbuser> userList, int totalCount) {
		int message = SUCCESS;
		String sql = "select * "
				+ "from db_16.user "
				+ "where u_name like ? ";
		userList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + u_name + "%");
			ResultSet rs = pstmt.executeQuery();
			for(int i = 0; rs.next() && i != totalCount; i++) {
				Dbuser user = new Dbuser();
				user.setAll(rs);
				userList.add(user);
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
	
	public int changeUserInfo(Dbuser user) {
		int message = EXCEPTION;
		String sql = "update db_16.user "
				+ "set u_name = ?, u_gender = ?, u_phone = ?, u_ad_country = ?, u_ad_province = ?, u_ad_city = ?, u_ad_street = ? "
				+ "where u_id = ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getU_name());
			pstmt.setString(2, user.getU_gender());
			pstmt.setString(3, user.getU_phone());
			pstmt.setString(4, user.getU_ad_country());
			pstmt.setString(5, user.getU_ad_province());
			pstmt.setString(6, user.getU_ad_city());
			pstmt.setString(7, user.getU_ad_street());
			pstmt.setString(8, user.getU_id());
			int i = pstmt.executeUpdate();
			if(i > 0) {
				message = SUCCESS;
			}
			else {
				message = WRONG_ID;
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
