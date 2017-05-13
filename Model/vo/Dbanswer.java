package vo;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Dbanswer {
	
	private String u_id = "0";
	private String qn_id = "0";
	private BigDecimal q_id = BigDecimal.valueOf(0);
	private BigDecimal i_id = BigDecimal.valueOf(0);
	private Timestamp a_timestamp = Timestamp.valueOf("1926-08-17 00:00:01");
	private String a_content = "";
	
	//no_translation
	
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
	
	public Timestamp getA_timestamp() {
		return a_timestamp;
	}
	
	public void setA_timestamp(Timestamp a_timestamp) {
		this.a_timestamp = a_timestamp;
	}
	
	public String getA_content() {
		return a_content;
	}
	
	public void setA_content(String a_content) {
		this.a_content = a_content;
	}

	public void setAll(ResultSet rs) throws SQLException{
		try {
			this.setU_id(rs.getString("u_id"));
			this.setQn_id(rs.getString("qn_id"));
			this.setQ_id(rs.getBigDecimal("q_id"));
			this.setI_id(rs.getBigDecimal("i_id"));
			this.setA_timestamp(rs.getTimestamp("a_timestamp"));
			this.setA_content(rs.getString("a_content"));
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
	
	public String get_transA_timestamp() {
		return a_timestamp.toString();
	}
	
	public void set_transA_timestamp(String a_timestamp) {
		this.a_timestamp = Timestamp.valueOf(a_timestamp);
	}
	
	public String get_transA_content() {
		return a_content;
	}
	
	public void set_transA_content(String a_content) {
		this.a_content = a_content;
	}
}
