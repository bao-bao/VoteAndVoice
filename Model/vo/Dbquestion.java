package vo;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dbquestion {
	
	private String qn_id = "0";
	private BigDecimal q_id = BigDecimal.valueOf(0);
	private String q_type = "";
	private String q_stem = "";
	private String q_des = "";
	private BigDecimal q_i_count = BigDecimal.valueOf(0);
	
	//no translation
	
	public String getQn_id() {
		return qn_id;
	}
	
	public void setQn_id(String qn_id) {
		this.qn_id = qn_id;
	}
	
	public BigDecimal getQ_id() {
		return q_id;
	}
	
	public void setQ_id(BigDecimal q_id) {
		this.q_id = q_id;
	}
	
	public String getQ_type() {
		return q_type;
	}
	
	public void setQ_type(String q_type) {
		this.q_type = q_type;
	}
	
	public String getQ_stem() {
		return q_stem;
	}
	
	public void setQ_stem(String q_stem) {
		this.q_stem = q_stem;
	}
	
	public String getQ_des() {
		return q_des;
	}
	
	public void setQ_des(String q_des) {
		this.q_des = q_des;
	}
	
	public BigDecimal getQ_i_count() {
		return q_i_count;
	}
	
	public void setQ_i_count(BigDecimal q_i_count) {
		this.q_i_count = q_i_count;
	}
	
	public void setAll(ResultSet rs) throws SQLException{
		try {
			this.setQn_id(rs.getString("qn_id"));
			this.setQ_id(rs.getBigDecimal("q_id"));
			this.setQ_type(rs.getString("q_type"));
			this.setQ_stem(rs.getString("q_stem"));
			this.setQ_des(rs.getString("q_des"));
			this.setQ_i_count(rs.getBigDecimal("q_i_count"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//translation
	public String get_transQn_id() {
		return qn_id;
	}
	
	public void set_transQn_id(String qn_id) {
		this.qn_id = qn_id;
	}
	
	public long get_transQ_id() {
		return q_id.longValue();
	}
	
	public void set_transQ_id(long q_id) {
		this.q_id = BigDecimal.valueOf(q_id);
	}
	
	public String get_transQ_type() {
		if (q_type.equals("sin"))
		{
			return "单选";
		}
		else if (q_type.equals("mul"))
		{
			return "多选";
		}
		else if (q_type.equals("que"))
		{
			return "问答";
		}
		return q_type;
	}
	
	public void set_transQ_type(String q_type) {
		if (q_type.equals("单选"))
		{
			this.q_type = "sin";
		}
		else if (q_type.equals("多选"))
		{
			this.q_type = "mul";
		}
		else if (q_type.equals("问答"))
		{
			this.q_type = "que";
		}
		else {
			this.q_type = q_type;			
		}
	}
	
	public String get_transQ_stem() {
		return q_stem;
	}
	
	public void set_transQ_stem(String q_stem) {
		this.q_stem = q_stem;
	}
	
	public String get_transQ_des() {
		return q_des;
	}
	
	public void set_transQ_des(String q_des) {
		this.q_des = q_des;
	}
	
	public long get_transQ_i_count() {
		return q_i_count.longValue();
	}
	
	public void set_transQ_i_count(long q_i_count) {
		this.q_i_count = BigDecimal.valueOf(q_i_count);
	}
}
