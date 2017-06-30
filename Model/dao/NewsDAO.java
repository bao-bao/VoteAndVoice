package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dbc.DatabaseConnection;
import vo.Dbanswer;
import vo.Dbquestionnaire;
import vo.ExDbanswer;
import vo.ExDbquestionnaire;

public class NewsDAO {
	private DatabaseConnection dbconn = null;
	private Connection conn = null;
	
	static public final int SUCCESS = 1;
	static public final int EXCEPTION = -1;
	static public final int FAILED = -2;
	static public final int DEFAULT_LIMIT = 100;
	public NewsDAO() {
		try {
			dbconn = new DatabaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = dbconn.getConnection();
	}
	
	public int endQuestionnaireByQnId(String qn_id) {
		int message = EXCEPTION;
		String sql = "update db_16.questionnaire "
				+ "set qn_state = 'end', qn_endtime = ? "
				+ "where qn_id = ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(2, qn_id);
			int i = pstmt.executeUpdate();
			if(i > 0) {
				message = SUCCESS;
			}
			else {
				message = FAILED;
			}
		} catch (SQLException e) {
			message = EXCEPTION;
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
	
	public int getQuestionnairesByUId(String s_id, ArrayList<Dbquestionnaire> questionnaireList, int limit) {
		int message = EXCEPTION;
		String sql = "select * "
				+ "from db_16.questionnaire "
				+ "where s_id=? and qn_state!='dly' "
				+ "order by qn_starttime desc ";
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Dbquestionnaire questionnaire = new Dbquestionnaire();
				questionnaire.setAll(rs);
				questionnaireList.add(questionnaire);
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
	public int getQuestionnairesByUId(String s_id, ArrayList<Dbquestionnaire> questionnaireList) {
		return getQuestionnairesByUId(s_id, questionnaireList, DEFAULT_LIMIT);
	}
	
	public int getExQuestionnairesByUId(String s_id, ArrayList<ExDbquestionnaire> exQuestionnaireList, int limit) {
		int message = EXCEPTION;
		String sql = "select * from "
				+ "(select * from db_16.questionnaire where s_id=? and qn_state!='dly') questionnaire "
				+ "join (select u_id s_id, u_name s_name from db_16.user) user using(s_id) "
				+ "order by qn_starttime desc ";
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		exQuestionnaireList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_id);
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
	public int getExQuestionnairesByUId(String s_id, ArrayList<ExDbquestionnaire> exQuestionnaireList) {
		return getExQuestionnairesByUId(s_id, exQuestionnaireList, DEFAULT_LIMIT);
	}
	
	public int getAnswersByUId(String u_id, ArrayList<Dbanswer> answerList, int limit) {
		int message = EXCEPTION;
		String sql = "select * "
				+ "from db_16.answer "
				+ "where u_id=? "
				+ "order by a_timestamp desc ";
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		answerList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Dbanswer answer = new Dbanswer();
				answer.setAll(rs);
				answerList.add(answer);
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
	public int getAnswersByUId(String u_id, ArrayList<Dbanswer> answerList) {
		return getAnswersByUId(u_id, answerList, DEFAULT_LIMIT);
	}
	
	public int getExAnswersByUId(String u_id, ArrayList<ExDbanswer> exAnswerList, int limit) {
		int message = EXCEPTION;
		String sql = "select * from "
				+ "(select * from db_16.answer where u_id=?) answer "
				+ "join (select u_id, u_name from db_16.user) user using(u_id)"
				+ "join (select qn_id, qn_title from db_16.questionnaire) questionnaire using(qn_id) "
				+ "join (select qn_id, q_id, q_stem from db_16.question) question using(qn_id, q_id) "
				+ "join (select qn_id, q_id, i_id, i_des from db_16.item) item using(qn_id, q_id, i_id) "
				+ "order by a_timestamp desc ";
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		exAnswerList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ExDbanswer exAnswer = new ExDbanswer();
				exAnswer.setAll(rs);
				exAnswerList.add(exAnswer);
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
	public int getExAnswersByUId(String u_id, ArrayList<ExDbanswer> exAnswerList) {
		return getExAnswersByUId(u_id, exAnswerList, DEFAULT_LIMIT);
	}
	
	public int getFedQuestionnairesByUId(String following_u_id, ArrayList<Dbquestionnaire> questionnaireList, int limit) {
		int message = EXCEPTION;
		String sql = "select * "
				+ "from db_16.questionnaire "
				+ "where s_id in (select followed_u_id from db_16.follow where following_u_id=? )  and qn_state!='dly' "
				+ "order by qn_starttime desc ";
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		questionnaireList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, following_u_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Dbquestionnaire questionnaire = new Dbquestionnaire();
				questionnaire.setAll(rs);
				questionnaireList.add(questionnaire);
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
	public int getFedQuestionnairesByUId(String following_u_id, ArrayList<Dbquestionnaire> questionnaireList) {
		return getFedQuestionnairesByUId(following_u_id, questionnaireList, DEFAULT_LIMIT);
	}
	
	public int getFedExQuestionnairesByUId(String following_u_id, ArrayList<ExDbquestionnaire> exQuestionnaireList, int limit) {
		int message = EXCEPTION;
		String sql = "select * from "
				+ "(select * from db_16.questionnaire where s_id in (select followed_u_id from db_16.follow where following_u_id=? )  and qn_state!='dly'  ) questionnaire "
				+ "join (select u_id s_id, u_name s_name from db_16.user) user using(s_id) "
				+ "order by qn_starttime desc ";
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		exQuestionnaireList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, following_u_id);
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
	public int getFedExQuestionnairesByUId(String following_u_id, ArrayList<ExDbquestionnaire> exQuestionnaireList) {
		return getFedExQuestionnairesByUId(following_u_id, exQuestionnaireList, DEFAULT_LIMIT);
	}
	
	public int getFedAnswersByUId(String following_u_id, ArrayList<Dbanswer> answerList, int limit) {
		int message = EXCEPTION;
		String sql = "select * "
				+ "from db_16.answer "
				+ "where u_id in (select followed_u_id from db_16.follow where following_u_id=? ) "
				+ "order by a_timestamp desc ";
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		answerList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, following_u_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Dbanswer answer = new Dbanswer();
				answer.setAll(rs);
				answerList.add(answer);
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
	public int getFedAnswersByUId(String following_u_id, ArrayList<Dbanswer> answerList) {
		return getFedAnswersByUId(following_u_id, answerList, DEFAULT_LIMIT);
	}
	
	public int getFedExAnswersByUId(String following_u_id, ArrayList<ExDbanswer> exAnswerList, int limit) {
		int message = EXCEPTION;
		String sql = "select * from "
				+ "(select * from db_16.answer where u_id in (select followed_u_id from db_16.follow where following_u_id=? ) ) answer "
				+ "join (select u_id, u_name from db_16.user) user using(u_id)"
				+ "join (select qn_id, qn_title from db_16.questionnaire) questionnaire using(qn_id) "
				+ "join (select qn_id, q_id, q_stem from db_16.question) question using(qn_id, q_id) "
				+ "join (select qn_id, q_id, i_id, i_des from db_16.item) item using(qn_id, q_id, i_id) "
				+ "order by a_timestamp desc ";
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
		}
		exAnswerList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, following_u_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ExDbanswer exAnswer = new ExDbanswer();
				exAnswer.setAll(rs);
				exAnswerList.add(exAnswer);
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
	public int getFedExAnswersByUId(String following_u_id, ArrayList<ExDbanswer> exAnswerList) {
		return getFedExAnswersByUId(following_u_id, exAnswerList, DEFAULT_LIMIT);
	}

	public int getOwnExQuestionnairesByUId(String s_id, ArrayList<ExDbquestionnaire> exQuestionnaireList, int limit) {
		int message = EXCEPTION;
		String sql = "select * from "
				+ "(select * from db_16.questionnaire where s_id=? and qn_state!='dly') questionnaire "
				+ "join (select u_id s_id, u_name s_name from db_16.user) user using(s_id) "
				+ "order by qn_starttime desc ";
		String sqlOwn = "select * from "
				+ "(select * from db_16.questionnaire where qn_id in (select qn_id from db_16.own where u_id=?) and s_id!=? and qn_state!='dly') questionnaire "
				+ "join (select u_id s_id, u_name s_name from db_16.user) user using(s_id) "
				+ "order by qn_starttime desc ";
		if(limit > 0) {
			sql += "limit " + Integer.toString(limit) + " ";
			sqlOwn += "limit " + Integer.toString(limit) + " ";
		}
		exQuestionnaireList.clear();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ExDbquestionnaire exQuestionnaire = new ExDbquestionnaire();
				exQuestionnaire.setAll(rs);
				exQuestionnaireList.add(exQuestionnaire);
			}
			PreparedStatement pstmtOwn = conn.prepareStatement(sqlOwn);
			pstmtOwn.setString(1, s_id);
			pstmtOwn.setString(2, s_id);
			ResultSet rsOwn = pstmtOwn.executeQuery();
			while(rsOwn.next()) {
				ExDbquestionnaire exQuestionnaire = new ExDbquestionnaire();
				exQuestionnaire.setAll(rsOwn);
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
	public int getOwnExQuestionnairesByUId(String s_id, ArrayList<ExDbquestionnaire> exQuestionnaireList) {
		return getOwnExQuestionnairesByUId(s_id, exQuestionnaireList, DEFAULT_LIMIT);
	}

}
