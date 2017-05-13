package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dbfollow {
	
	private String following_u_id = "0";
	private String followed_u_id = "0";
	
	//no translation
	
	public String getFollowing_u_id() {
		return following_u_id;
	}
	
	public void setFollowing_u_id(String following_u_id) {
		this.following_u_id = following_u_id;
	}
	
	public String getFollowed_u_id() {
		return followed_u_id;
	}
	
	public void setFollowed_u_id(String followed_u_id) {
		this.followed_u_id = followed_u_id;
	}
	
	public void setAll(ResultSet rs) throws SQLException{
		try {
			this.setFollowing_u_id(rs.getString("following_u_id"));
			this.setFollowed_u_id(rs.getString("followed_u_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//translation
	
	public String get_transFollowing_u_id() {
		return following_u_id;
	}
	
	public void set_transFollowing_u_id(String following_u_id) {
		this.following_u_id = following_u_id;
	}
	
	public String get_transFollowed_u_id() {
		return followed_u_id;
	}
	
	public void set_transFollowed_u_id(String followed_u_id) {
		this.followed_u_id = followed_u_id;
	}
}
