package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DatabaseConnection;
import vo.Dbanswer;
import vo.ExDbitem;
import vo.ExDbquestion;
import vo.ExDbquestionnaire;

public class AnswerQnDAO {
	private DatabaseConnection dbconn = null;
	private Connection conn = null;

	static public final int EXCEPTION = -1;
	static public final int SUCCESS = 1;
	static public final int FAILED = -2;

	public AnswerQnDAO() {
		try {
			dbconn = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn = dbconn.getConnection();
	}

	public int getQnByQnId(String qn_id, ArrayList<ExDbquestionnaire> exQnList) {
		int message = FAILED;
		String sqlQn = "select * from "
				+ "(select * from db_16.questionnaire where qn_id=?) questionnaire "
				+ "join (select u_id s_id, u_name s_name from db_16.user) user using(s_id) ";
		String sqlQ = "select * from db_16.question "
				+ "where qn_id=? ";
		String sqlI = "select * from db_16.item "
				+ "where qn_id=? and q_id=? ";
		exQnList.clear();
		try {
			//根据qn_id取ExQn
			PreparedStatement pstmtQn = conn.prepareStatement(sqlQn);
			pstmtQn.setString(1, qn_id);
			ResultSet rsQn = pstmtQn.executeQuery();
			if(rsQn.next()) {
				ExDbquestionnaire exQn = new ExDbquestionnaire();
				exQn.setAll(rsQn);
				//根据qn_id取ExQ
				PreparedStatement pstmtQ = conn.prepareStatement(sqlQ);
				pstmtQ.setString(1, qn_id);
				ResultSet rsQ = pstmtQ.executeQuery();
				while(rsQ.next()) {
					BigDecimal q_id = rsQ.getBigDecimal("q_id");
					ExDbquestion exQ = new ExDbquestion();
					exQ.setPart(rsQ);
					//根据qn_id, q_id取ExI
					PreparedStatement pstmtI = conn.prepareStatement(sqlI);
					pstmtI.setString(1, qn_id);
					pstmtI.setBigDecimal(2, q_id);
					ResultSet rsI = pstmtI.executeQuery();
					while(rsI.next()) {
						ExDbitem exI = new ExDbitem();
						exI.setPart(rsI);
						exQ.getExItemList().add(exI);
					}
					exQn.getExQuestionList().add(exQ);
				}
				exQnList.add(exQn);
				message = SUCCESS;
			}
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

	private int answerQnCore(String u_id, String qn_id, ArrayList<Dbanswer> answerList) {
		int message = SUCCESS;
		String sqlA = "insert into db_16.answer(u_id, qn_id, q_id, i_id, a_content) "
				+ "values(?,?,?,?,?) ";
		String sqlQn = "update db_16.questionnaire "
				+ "set qn_a_count = qn_a_count + 1 "
				+ "where qn_id=? ";
		try {
			conn.setAutoCommit(false);
			for(int i = 0; i != answerList.size(); i++) {
				PreparedStatement pstmtA = conn.prepareStatement(sqlA);
				pstmtA.setString(1, u_id);
				pstmtA.setString(2, qn_id);
				pstmtA.setBigDecimal(3, answerList.get(i).getQ_id());
				pstmtA.setBigDecimal(4, answerList.get(i).getI_id());
				pstmtA.setString(5, answerList.get(i).getA_content());
				int count = pstmtA.executeUpdate();
				if(count == 0) {
					message = FAILED;
				}
			}
			if(message == SUCCESS) {
				PreparedStatement pstmtQn = conn.prepareStatement(sqlQn);
				pstmtQn.setString(1, qn_id);
				int count = pstmtQn.executeUpdate();
				if(count == 0) {
					message = FAILED;
				}
			}
		} catch (Exception e) {
			message = EXCEPTION;
			System.out.println("MySQL fault.");
			e.printStackTrace();
		} finally {
			try {
				if(message == SUCCESS) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
				dbconn.close();
			} catch (Exception e) {
				message = EXCEPTION;
				e.printStackTrace();
			}
		}
		return message;
	}
	public int answerQn(ArrayList<Dbanswer> answerList) {
		int message = SUCCESS;
		String u_id = null;
		String qn_id = null;
		if(answerList.size() == 0) {
			message = FAILED;
		}
		if(message == SUCCESS) {
			u_id = answerList.get(0).getU_id();
			qn_id = answerList.get(0).getQn_id();
			for(int i = 1; i < answerList.size(); i++) {
				if(!u_id.equals(answerList.get(i).getU_id()) || !qn_id.equals(answerList.get(i).getQn_id())) {
					message = FAILED;
					break;
				}
			}
		}
		if(message == SUCCESS) {
			return this.answerQnCore(u_id, qn_id, answerList);
		}
		try {
			dbconn.close();
		} catch (Exception e) {
			message = EXCEPTION;
			e.printStackTrace();
		}
		return message;
	}
}
