package vo;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExDbitem {
	private Dbitem item = new Dbitem();
	private String qn_title = "";
	private String q_stem = "";
	private ArrayList<ExDbanswer> exAnswerList = null;
	private BigDecimal i_a_count = BigDecimal.valueOf(0);
	//no translation
	
	public Dbitem getItem() {
		return item;
	}
	public String getQn_title() {
		return qn_title;
	}
	public void setQn_title(String qn_title) {
		this.qn_title = qn_title;
	}
	public String getQ_stem() {
		return q_stem;
	}
	public void setQ_stem(String q_stem) {
		this.q_stem = q_stem;
	}

	public void setAll(ResultSet rs) throws SQLException{
		try {
			this.getItem().setAll(rs);
			this.setQn_title(rs.getString("qn_title"));
			this.setQ_stem(rs.getString("q_stem"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void setPart(ResultSet rs) throws SQLException{
		try {
			this.getItem().setAll(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<ExDbanswer> getExAnswerList() {
		if(exAnswerList == null){
			exAnswerList = new ArrayList<ExDbanswer>();
		}
		return exAnswerList;
	}
	public BigDecimal getI_a_count() {
		return i_a_count;
	}
	public void setI_a_count(BigDecimal i_a_count) {
		this.i_a_count = i_a_count;
	}
	
	//translation
	public Dbitem get_transItem() {
		return item;
	}
	public String get_transQn_title() {
		return qn_title;
	}
	public void set_transQn_title(String qn_title) {
		this.qn_title = qn_title;
	}
	public String get_transQ_stem() {
		return q_stem;
	}
	public void set_transQ_stem(String q_stem) {
		this.q_stem = q_stem;
	}
	public ArrayList<ExDbanswer> get_transExAnswerList() {
		return getExAnswerList();
	}
	public long get_transI_a_count() {
		return i_a_count.longValue();
	}
	public void set_transI_a_count(long i_a_count) {
		this.i_a_count = BigDecimal.valueOf(i_a_count);
	}
}
