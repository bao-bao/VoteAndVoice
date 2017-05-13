package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dbown {
	
	private String u_id = "0";
	private String qn_id = "0";
	
	//no translation
	
	public String getU_id() {
		return u_id;
	}
	
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	
	public String getQn_id() {
		return qn_id;
	}
	
	public void setQn_id(String qn_id) {
		this.qn_id = qn_id;
	}
	
	public void setAll(ResultSet rs) throws SQLException{
		try {
			this.setU_id(rs.getString("u_id"));
			this.setQn_id(rs.getString("qn_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//translation
	public String get_transU_id() {
		return u_id;
	}
	
	public void set_transU_id(String u_id) {
		this.u_id = u_id;
	}
	
	public String get_transQn_id() {
		return qn_id;
	}
	
	public void set_transQn_id(String qn_id) {
		this.qn_id = qn_id;
	}
}
