package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DatabaseConnection;
import vo.Dbuser;

public class FollowDAO {
	private DatabaseConnection dbconn = null;
	private Connection conn = null;

	static public final int EXCEPTION = -1;
	static public final int SUCCESS = 1;
	static public final int FAILED = -2;
	
	public FollowDAO() {
		try {
			dbconn = new DatabaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = dbconn.getConnection();
	}
	
	public int getFriends(String u_id, ArrayList<Dbuser> friendsList) {
		int message = EXCEPTION;
		String sql = "select * "
				+ "from db_16.user "
				+ "where u_id in (select f1.followed_u_id "
				+ " from db_16.follow f1 join db_16.follow f2 on (f1.following_u_id=f2.followed_u_id and f1.followed_u_id=f2.following_u_id) "
				+ " where f1.following_u_id = ?) ";
		System.out.println(sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Dbuser user = new Dbuser();
				user.setAll(rs);
				friendsList.add(user);
			}
			message = SUCCESS;
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
	
	public int getFollowedUser(String u_id, ArrayList<Dbuser> friendsList) {
		int message = EXCEPTION;
		String sql = "select * "
				+ "from db_16.user "
				+ "where u_id in (select followed_u_id "
				+ " from db_16.follow "
				+ " where following_u_id=? and followed_u_id not in(select following_u_id "
				+ "  from db_16.follow "
				+ "  where followed_u_id=?)) ";
		System.out.println(sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			pstmt.setString(2, u_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Dbuser user = new Dbuser();
				user.setAll(rs);
				friendsList.add(user);
			}
			message = SUCCESS;
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
	
	public int getFollowingUser(String u_id, ArrayList<Dbuser> friendsList) {
		int message = EXCEPTION;
		String sql = "select * "
				+ "from db_16.user "
				+ "where u_id in (select following_u_id "
				+ " from db_16.follow "
				+ " where followed_u_id=? and following_u_id not in(select followed_u_id "
				+ "  from db_16.follow "
				+ "  where following_u_id=?)) ";
		System.out.println(sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			pstmt.setString(2, u_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Dbuser user = new Dbuser();
				user.setAll(rs);
				friendsList.add(user);
			}
			message = SUCCESS;
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

	public int getAllFollowedUser(String u_id, ArrayList<Dbuser> friendsList) {
		int message = EXCEPTION;
		String sql = "select * "
				+ "from db_16.user "
				+ "where u_id in (select followed_u_id "
				+ " from db_16.follow "
				+ " where following_u_id=? ) ";
		System.out.println(sql);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Dbuser user = new Dbuser();
				user.setAll(rs);
				friendsList.add(user);
			}
			message = SUCCESS;
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
	
	public int follow(String following_u_id, String followed_u_id) {
		int message = EXCEPTION;
		String sqlTest = "select count(*) from db_16.follow "
				+ "where following_u_id=? and followed_u_id=? ";
		String sql = "insert into db_16.follow(following_u_id, followed_u_id) "
				+ "values(?,?) ";
		try {
			PreparedStatement pstmtTest = conn.prepareStatement(sqlTest);
			pstmtTest.setString(1, following_u_id);
			pstmtTest.setString(2, followed_u_id);
			ResultSet rs = pstmtTest.executeQuery();
			rs.next();
			int counterTest = rs.getInt(1);
			if(counterTest == 1) {
				message = FAILED;
				return message;
			}
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, following_u_id);
			pstmt.setString(2, followed_u_id);
			int counter = pstmt.executeUpdate();
			if(counter > 0) {
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
	
	public int unfollow(String following_u_id, String followed_u_id) {
		int message = EXCEPTION;
		String sql = "delete from db_16.follow "
				+ "where following_u_id=? and followed_u_id = ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, following_u_id);;
			pstmt.setString(2, followed_u_id);
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
