package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DatabaseConnection;
import vo.ExDbquestion;
import vo.ExDbquestionnaire;

public class SearchQnDAO {
	private DatabaseConnection dbconn = null;
	private Connection conn = null;
	//message return
	static public final int SUCCESS = 1;
	static public final int EXCEPTION = -1;
	//authority parameter
	static public final int ALL = 0;
	static public final int PUBONLY = 1;
	
	public SearchQnDAO() {
		try {
			dbconn = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn = dbconn.getConnection();
	}
	
	public int getExQnsByQnTitle(String qn_title, ArrayList<ExDbquestionnaire> exQuestionnaireList, String authority,  int limit, String orderBy, String state, long q_min, long q_max, String type, long a_min, long a_max) {
		int message = EXCEPTION;
		String screenString = "";
		if(authority != null) {
			screenString += " and qn_authority = '" + authority + "' ";
		}
		if(state != null) {
			screenString += " and qn_state = '" + state + "' ";
		}
		if(q_min != -1) {
			screenString += " and qn_q_count >= " + q_min + " ";
		}
		if(q_max != -1) {
			screenString += " and qn_q_count <= " + q_max + " ";
		}
		if(type != null) {
			screenString += " and qn_type = '" + type + "' ";
		}
		if(a_min != -1) {
			screenString += " and qn_a_count >= " + a_min + " ";
		}
		if(a_max != -1) {
			screenString += " and qn_a_count <= " + a_max + " ";
		}
		if(orderBy == null) orderBy = "";
		if(!orderBy.equals("")) {
			orderBy = "order by " + orderBy + " ";
		}
		String sql = "select * from "
				+ "(select * from db_16.questionnaire where qn_title like ? " + screenString + " ) questionnaire "
				+ "join (select u_id s_id, u_name s_name from db_16.user) user using(s_id) "
				+ orderBy;
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		exQuestionnaireList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + qn_title + "%");
			System.out.println(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ExDbquestionnaire exQuestionnaire = new ExDbquestionnaire();
				exQuestionnaire.setAll(rs);
				exQuestionnaireList.add(exQuestionnaire);
			}
			message = SUCCESS;
		} catch (SQLException e) {
			message = EXCEPTION;
			System.out.println("MySQL fault.");
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
	public int getExQnsByQnTitle(String qn_title, ArrayList<ExDbquestionnaire> exQuestionnaireList, int authority,  int limit, String orderBy) {
		int message = EXCEPTION;
		String authorityString = "";
		switch(authority) {
		case ALL:
			authorityString = "";
			break;
		case PUBONLY:
			authorityString = " and qn_authority = 'pub' ";
			break;
		default:
			break;	
		}
		if(orderBy == null) orderBy = "";
		if(!orderBy.equals("")) {
			orderBy = "order by " + orderBy + " ";
		}
		String sql = "select * from "
				+ "(select * from db_16.questionnaire where qn_title like ? " + authorityString + " ) questionnaire "
				+ "join (select u_id s_id, u_name s_name from db_16.user) user using(s_id) "
				+ orderBy;
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		exQuestionnaireList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + qn_title + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ExDbquestionnaire exQuestionnaire = new ExDbquestionnaire();
				exQuestionnaire.setAll(rs);
				exQuestionnaireList.add(exQuestionnaire);
			}
			message = SUCCESS;
		} catch (SQLException e) {
			message = EXCEPTION;
			System.out.println("MySQL fault.");
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
	public int getExQnsByQnTitle(String qn_title, ArrayList<ExDbquestionnaire> exQuestionnaireList, int authority, int limit) {
		return this.getExQnsByQnTitle(qn_title, exQuestionnaireList, authority, limit, "");
	}
	public int getAllExQnsByQnTitle(String qn_title, ArrayList<ExDbquestionnaire> exQuestionnaireList, int limit, String orderBy) {
		return this.getExQnsByQnTitle(qn_title, exQuestionnaireList, ALL, limit, orderBy);
	}
	public int getAllExQnsByQnTitle(String qn_title, ArrayList<ExDbquestionnaire> exQuestionnaireList, int limit) {
		return this.getAllExQnsByQnTitle(qn_title, exQuestionnaireList, limit, "");
	}
	public int getPubExQnsByQnTitle(String qn_title, ArrayList<ExDbquestionnaire> exQuestionnaireList, int limit, String orderBy) {
		return this.getExQnsByQnTitle(qn_title, exQuestionnaireList, PUBONLY, limit, orderBy);
	}
	public int getPubExQnsByQnTitle(String qn_title, ArrayList<ExDbquestionnaire> exQuestionnaireList, int limit) {
		return this.getPubExQnsByQnTitle(qn_title, exQuestionnaireList, limit, "");
	}
	
	public int getExQnsByQnTypeOrTag(String qn_typeOrTag, ArrayList<ExDbquestionnaire> exQuestionnaireList, int authority, int limit, String orderBy, String state) {
		int message = EXCEPTION;
		String authorityString = "";
		String screenString = "";
		switch(authority) {
		case ALL:
			authorityString = "";
			break;
		case PUBONLY:
			authorityString = " and qn_authority = 'pub' ";
			break;
		default:
			break;	
		}
		if(orderBy == null) orderBy = "";
		if(!orderBy.equals("")) {
			orderBy = "order by " + orderBy + " ";
		}
		if(state != null) {
			screenString += " and qn_state = '" + state + "' ";
		}
		String sql = "select * from "
				+ "(select * from db_16.questionnaire where (qn_type = ? or qn_tag = ?) " + authorityString + screenString + ") questionnaire "
				+ "join (select u_id s_id, u_name s_name from db_16.user) user using(s_id) "
				+ orderBy;
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		exQuestionnaireList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qn_typeOrTag);
			pstmt.setString(2, qn_typeOrTag);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ExDbquestionnaire exQuestionnaire = new ExDbquestionnaire();
				exQuestionnaire.setAll(rs);
				exQuestionnaireList.add(exQuestionnaire);
			}
			message = SUCCESS;
		} catch (SQLException e) {
			message = EXCEPTION;
			System.out.println("MySQL fault.");
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
	public int getExQnsByQnTypeOrTag(String qn_typeOrTag, ArrayList<ExDbquestionnaire> exQuestionnaireList, int authority, int limit, String orderBy) {
		return this.getExQnsByQnTypeOrTag(qn_typeOrTag, exQuestionnaireList, authority, limit, orderBy, "ing");
	}
	public int getExQnsByQnTypeOrTag(String qn_typeOrTag, ArrayList<ExDbquestionnaire> exQuestionnaireList, int authority, int limit) {
		return this.getExQnsByQnTypeOrTag(qn_typeOrTag, exQuestionnaireList, authority, limit, "");
	}
	public int getAllExQnsByQnTypeOrTag(String qn_typeOrTag, ArrayList<ExDbquestionnaire> exQuestionnaireList, int limit, String orderBy) {
		return this.getExQnsByQnTypeOrTag(qn_typeOrTag, exQuestionnaireList, ALL, limit, orderBy);
	}
	public int getAllExQnsByQnTypeOrTag(String qn_typeOrTag, ArrayList<ExDbquestionnaire> exQuestionnaireList, int limit) {
		return this.getAllExQnsByQnTypeOrTag(qn_typeOrTag, exQuestionnaireList, limit, "");
	}
	public int getPubExQnsByQnTypeOrTag(String qn_typeOrTag, ArrayList<ExDbquestionnaire> exQuestionnaireList, int limit, String orderBy) {
		return this.getExQnsByQnTypeOrTag(qn_typeOrTag, exQuestionnaireList, PUBONLY, limit, orderBy);
	}
	public int getPubExQnsByQnTypeOrTag(String qn_typeOrTag, ArrayList<ExDbquestionnaire> exQuestionnaireList, int limit) {
		return this.getPubExQnsByQnTypeOrTag(qn_typeOrTag, exQuestionnaireList, limit, "");
	}	

	

	public int getExQByAStem(String q_stem, ArrayList<ExDbquestion> exQuestionList, String authority, int limit, String orderBy, String q_type, String qn_type) {
		int message = EXCEPTION;
		String qScreenString = "";
		String qnScreenString = "";
		if(q_type != null) {
			qScreenString += " and q_type = '" + q_type + "' ";
		}
		if(authority != null) {
			qnScreenString += " and qn_authority = '" + authority + "' ";
		}
		if(qn_type != null) {
			qnScreenString += " and qn_type = '" + qn_type + "' ";
		}
		if(!qnScreenString.equals("")) {
			qnScreenString = " where" + qnScreenString.substring(4, qnScreenString.length());
		}
		if(orderBy == null) orderBy = "";
		if(!orderBy.equals("")) {
			orderBy = "order by " + orderBy + " ";
		}
		String sql = "select * from "
				+ "(select * from db_16.question where q_stem like ? " + qScreenString + " ) question "
				+ "join (select qn_id, qn_title from db_16.questionnaire " + qnScreenString + " ) questionnaire using(qn_id) "
				+ orderBy;
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		exQuestionList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + q_stem + "%");
			System.out.println(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ExDbquestion exQuestion = new ExDbquestion();
				exQuestion.setAll(rs);
				exQuestionList.add(exQuestion);
			}
			message = SUCCESS;
		} catch (SQLException e) {
			message = EXCEPTION;
			System.out.println("MySQL fault.");
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
	public int getExQByAStem(String q_stem, ArrayList<ExDbquestion> exQuestionList, int authority, int limit, String orderBy) {
		int message = EXCEPTION;
		String authorityString = "";
		switch(authority) {
		case ALL:
			authorityString = "";
			break;
		case PUBONLY:
			authorityString = " where qn_authority = 'pub' ";
			break;
		default:
			break;	
		}
		if(orderBy == null) orderBy = "";
		if(!orderBy.equals("")) {
			orderBy = "order by " + orderBy + " ";
		}
		String sql = "select * from "
				+ "(select * from db_16.question where q_stem like ?) question "
				+ "join (select qn_id, qn_title from db_16.questionnaire " + authorityString + " ) questionnaire using(qn_id) "
				+ orderBy;
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		exQuestionList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + q_stem + "%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ExDbquestion exQuestion = new ExDbquestion();
				exQuestion.setAll(rs);
				exQuestionList.add(exQuestion);
			}
			message = SUCCESS;
		} catch (SQLException e) {
			message = EXCEPTION;
			System.out.println("MySQL fault.");
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
	public int getExQByAStem(String q_stem, ArrayList<ExDbquestion> exQuestionList, int authority, int limit) {
		return this.getExQByAStem(q_stem, exQuestionList, authority, limit, "");
	}
	public int getAllExQByAStem(String q_stem, ArrayList<ExDbquestion> exQuestionList, int limit, String orderBy) {
		return this.getExQByAStem(q_stem, exQuestionList, ALL, limit, orderBy);
	}
	public int getAllExQByAStem(String q_stem, ArrayList<ExDbquestion> exQuestionList, int limit) {
		return this.getAllExQByAStem(q_stem, exQuestionList, limit, "");
	}
	public int getPubExQByAStem(String q_stem, ArrayList<ExDbquestion> exQuestionList, int limit, String orderBy) {
		return this.getExQByAStem(q_stem, exQuestionList, PUBONLY, limit, orderBy);
	}
	public int getPubExQByAStem(String q_stem, ArrayList<ExDbquestion> exQuestionList, int limit) {
		return this.getPubExQByAStem(q_stem, exQuestionList, limit, "");
	}
}
