package vo;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Dbquestionnaire {
	
	private String qn_id = "0";
	private String s_id = "0";
	private String qn_title = "";
	private String qn_des = "";
	private String qn_type = "其它";
	private String qn_tag = "其它";
	private String qn_authority = "";
	private String qn_state = "";
	private String qn_validity = "";
	private BigDecimal qn_q_count = BigDecimal.valueOf(0);
	private BigDecimal qn_a_count = BigDecimal.valueOf(0);
	private Timestamp qn_starttime = Timestamp.valueOf("1926-08-17 00:00:01");
	private Timestamp qn_endtime = Timestamp.valueOf("1926-08-17 00:00:01");
	
	//no translation
	
	public String getQn_id() {
		return qn_id;
	}
	
	public void setQn_id(String qn_id) {
		this.qn_id = qn_id;
	}
	
	public String getS_id() {
		return s_id;
	}
	
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	
	public String getQn_title() {
		return qn_title;
	}
	
	public void setQn_title(String qn_title) {
		this.qn_title = qn_title;
	}
	
	public String getQn_des() {
		return qn_des;
	}
	
	public void setQn_des(String qn_des) {
		this.qn_des = qn_des;
	}
	
	public String getQn_type() {
		return qn_type;
	}
	
	public void setQn_type(String qn_type) {
		this.qn_type = qn_type;
	}

	public String getQn_tag() {
		return qn_tag;
	}

	public void setQn_tag(String qn_tag) {
		this.qn_tag = qn_tag;
	}
	
	public String getQn_authority() {
		return qn_authority;
	}

	public void setQn_authority(String qn_authority) {
		this.qn_authority = qn_authority;
	}

	public String getQn_state() {
		return qn_state;
	}
	
	public void setQn_state(String qn_state) {
		this.qn_state = qn_state;
	}
	
	public String getQn_validity() {
		return qn_validity;
	}
	
	public void setQn_validity(String qn_validity) {
		this.qn_validity = qn_validity;
	}
	
	public BigDecimal getQn_q_count() {
		return qn_q_count;
	}
	
	public void setQn_q_count(BigDecimal qn_q_count) {
		this.qn_q_count = qn_q_count;
	}

	public Timestamp getQn_starttime() {
		return qn_starttime;
	}

	public void setQn_starttime(Timestamp qn_starttime) {
		this.qn_starttime = qn_starttime;
	}

	public Timestamp getQn_endtime() {
		return qn_endtime;
	}

	public void setQn_endtime(Timestamp qn_endtime) {
		this.qn_endtime = qn_endtime;
	}

	public BigDecimal getQn_a_count() {
		return qn_a_count;
	}

	public void setQn_a_count(BigDecimal qn_a_count) {
		this.qn_a_count = qn_a_count;
	}
	
	public void setAll(ResultSet rs) throws SQLException{
		try {
			this.setQn_id(rs.getString("qn_id"));
			this.setS_id(rs.getString("s_id"));
			this.setQn_title(rs.getString("qn_title"));
			this.setQn_des(rs.getString("qn_des"));
			this.setQn_type(rs.getString("qn_type"));
			this.setQn_tag(rs.getString("qn_tag"));
			this.setQn_authority(rs.getString("qn_authority"));
			this.setQn_state(rs.getString("qn_state"));
			this.setQn_validity(rs.getString("qn_validity"));
			this.setQn_q_count(rs.getBigDecimal("qn_q_count"));
			this.setQn_a_count(rs.getBigDecimal("qn_a_count"));
			this.setQn_starttime(rs.getTimestamp("qn_starttime"));
			this.setQn_endtime(rs.getTimestamp("qn_endtime"));
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
	
	public String get_transS_id() {
		return s_id;
	}
	
	public void set_transS_id(String s_id) {
		this.s_id = s_id;
	}
	
	public String get_transQn_title() {
		return qn_title;
	}
	
	public void set_transQn_title(String qn_title) {
		this.qn_title = qn_title;
	}
	
	public String get_transQn_des() {
		return qn_des;
	}
	
	public void set_transQn_des(String qn_des) {
		this.qn_des = qn_des;
	}
	
	public String get_transQn_type() {
		return qn_type;
	}
	
	public void set_transQn_type(String qn_type) {
		this.qn_type = qn_type;
	}

	public String get_transQn_tag() {
		return qn_tag;
	}

	public void set_transQn_tag(String qn_tag) {
		this.qn_tag = qn_tag;
	}
	
	public String get_transQn_authority() {
		if (qn_authority.equals("pub"))
		{
			return "公有";
		}
		else if (qn_authority.equals("pri"))
		{
			return "私有";
		}
		return qn_authority;
	}

	public void set_transQn_authority(String qn_authority) {
		if (qn_authority.equals("公有"))
		{
			this.qn_authority = "pub";
		}
		else if (qn_authority.equals("私有"))
		{
			this.qn_authority = "pri";
		}
		else {
			this.qn_authority = qn_authority;
		}
	}

	public String get_transQn_state() {
		if (qn_state.equals("ing"))
		{
			return "进行中";
		}
		else if (qn_state.equals("end"))
		{
			return "已结束";
		}
		return qn_state;
	}
	
	public void set_transQn_state(String qn_state) {
		if (qn_state.equals("进行中"))
		{
			this.qn_state = "ing";
		}
		else if (qn_state.equals("已结束"))
		{
			this.qn_state = "end";
		}
		else {
			this.qn_state = qn_state;
		}
	}
	
	public String get_transQn_validity() {
		if (qn_validity.equals("P"))
		{
			return "合法";
		}
		else if (qn_validity.equals("N"))
		{
			return "非法";
		}
		return qn_validity;
	}
	
	public void set_transQn_validity(String qn_validity) {
		if (qn_validity.equals("合法"))
		{
			this.qn_validity = "P";
		}
		else if (qn_validity.equals("非法"))
		{
			this.qn_validity = "N";
		}
		else {
			this.qn_validity = qn_validity;
		}
	}
	
	public long get_transQn_q_count() {
		return qn_q_count.longValue();
	}
	
	public void set_transQn_q_count(long qn_q_count) {
		this.qn_q_count = BigDecimal.valueOf(qn_q_count);
	}

	public String get_transQn_starttime() {
		return qn_starttime.toString();
	}

	public void set_transQn_starttime(String qn_starttime) {
		this.qn_starttime = Timestamp.valueOf(qn_starttime);
	}

	public String get_transQn_endtime() {
		return qn_endtime.toString();
	}

	public void set_transQn_endtime(String qn_endtime) {
		this.qn_endtime = Timestamp.valueOf(qn_endtime);
	}

	public long get_transQn_a_count() {
		return qn_a_count.longValue();
	}

	public void set_transQn_a_count(long qn_a_count) {
		this.qn_a_count = BigDecimal.valueOf(qn_a_count);
	}
}
