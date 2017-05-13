package vo;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dbitem {
	private String qn_id = "0";
	private BigDecimal q_id = BigDecimal.valueOf(0);
	private BigDecimal i_id = BigDecimal.valueOf(0);
	private String i_type = "";
	private String i_des = "";
	
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
	
	public BigDecimal getI_id() {
		return i_id;
	}
	
	public void setI_id(BigDecimal i_id) {
		this.i_id = i_id;
	}
	
	public String getI_type() {
		return i_type;
	}
	
	public void setI_type(String i_type) {
		this.i_type = i_type;
	}
	
	public String getI_des() {
		return i_des;
	}
	
	public void setI_des(String i_des) {
		this.i_des = i_des;
	}

	public void setAll(ResultSet rs) throws SQLException{
		try {
			this.setQn_id(rs.getString("qn_id"));
			this.setQ_id(rs.getBigDecimal("q_id"));
			this.setI_id(rs.getBigDecimal("i_id"));
			this.setI_type(rs.getString("i_type"));
			this.setI_des(rs.getString("i_des"));
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
	
	public long get_transI_id() {
		return i_id.longValue();
	}
	
	public void set_transI_id(long i_id) {
		this.i_id = BigDecimal.valueOf(i_id);
	}
	
	public String get_transI_type() {
		if (i_type.equals("sin"))
		{
			return "单选";
		}
		else if (i_type.equals("mul"))
		{
			return "多选";
		}
		else if (i_type.equals("que"))
		{
			return "问答";
		}
		return i_type;
	}
	
	public void set_transI_type(String i_type) {
		if (i_type.equals("单选"))
		{
			this.i_type = "sin";
		}
		else if (i_type.equals("多选"))
		{
			this.i_type = "mul";
		}
		else if (i_type.equals("问答"))
		{
			this.i_type = "que";
		}
		else {
			this.i_type = i_type;
		}
	}
	
	public String get_transI_des() {
		return i_des;
	}
	
	public void set_transI_des(String i_des) {
		this.i_des = i_des;
	}
}
