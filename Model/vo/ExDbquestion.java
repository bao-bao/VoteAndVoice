package vo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExDbquestion {
	private Dbquestion question = new Dbquestion();
	private String qn_title = "";
	private ArrayList<ExDbitem> exItemList = null;
	
	//no translation
	public Dbquestion getQuestion() {
		return question;
	}
	public String getQn_title() {
		return qn_title;
	}
	public void setQn_title(String qn_title) {
		this.qn_title = qn_title;
	}
	
	public void setAll(ResultSet rs) throws SQLException{
		try {
			this.getQuestion().setAll(rs);
			this.setQn_title(rs.getString("qn_title"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void setPart(ResultSet rs) throws SQLException{
		try {
			this.getQuestion().setAll(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<ExDbitem> getExItemList() {
		if(exItemList == null) {
			exItemList = new ArrayList<ExDbitem>();
		}
		return exItemList;
	}
	
	//translation
	public Dbquestion get_transQuestion() {
		return question;
	}
	public String get_transQn_title() {
		return qn_title;
	}
	public void set_transQn_title(String qn_title) {
		this.qn_title = qn_title;
	}
	public ArrayList<ExDbitem> get_transExItemList() {
		return getExItemList();
	}
}
