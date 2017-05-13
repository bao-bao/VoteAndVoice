package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dbquestionnairetype {
	
	private String qn_type_name = "ÆäËü";
	
	//no translation

	public String getQn_type_name() {
		return qn_type_name;
	}

	public void setQn_type_name(String qn_type_name) {
		this.qn_type_name = qn_type_name;
	}
	
	public void setAll(ResultSet rs) throws SQLException{
		try {
			this.setQn_type_name(rs.getString("qn_type_name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//translation
	public String get_transQn_type_name() {
		return qn_type_name;
	}

	public void set_transQn_type_name(String qn_type_name) {
		this.qn_type_name = qn_type_name;
	}
}
