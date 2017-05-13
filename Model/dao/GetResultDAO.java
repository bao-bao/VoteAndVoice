package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DatabaseConnection;
import vo.ExDbanswer;
import vo.ExDbitem;
import vo.ExDbquestion;
import vo.ExDbquestionnaire;

public class GetResultDAO {
	private DatabaseConnection dbconn = null;
	private Connection conn = null;

	static public final int EXCEPTION = -1;
	static public final int SUCCESS = 1;
	static public final int FAILED = -2;

	public GetResultDAO() {
		try {
			dbconn = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn = dbconn.getConnection();
	}

	public int getQnResultByQnId(String qn_id, ArrayList<ExDbquestionnaire> exQnList) {
		int message = FAILED;
		String sqlQn = "select * from "
				+ "(select * from db_16.questionnaire where qn_id=?) questionnaire "
				+ "join (select u_id s_id, u_name s_name from db_16.user) user using(s_id) ";
		String sqlQ = "select * from db_16.question "
				+ "where qn_id=? ";
		String sqlI = "select * from db_16.item "
				+ "where qn_id=? and q_id=? ";
		String sqlAC = "select count(*) i_a_count from db_16.answer "
				+ "where qn_id=? and q_id=? and i_id=? ";
		String sqlAQ = "select * from db_16.answer "
				+ "where qn_id=? and q_id=? and i_id=? ";
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
						BigDecimal i_id = rsI.getBigDecimal("i_id");
						ExDbitem exI = new ExDbitem();
						exI.setPart(rsI);
						//根据qn_id, q_id, i_id取ExA
						PreparedStatement pstmtAC = conn.prepareStatement(sqlAC);
						pstmtAC.setString(1, qn_id);
						pstmtAC.setBigDecimal(2, q_id);
						pstmtAC.setBigDecimal(3, i_id);
						ResultSet rsAC = pstmtAC.executeQuery();
						rsAC.next();
						exI.setI_a_count(rsAC.getBigDecimal("i_a_count"));
						if(exI.getItem().getI_type().equals("que")) {
							PreparedStatement pstmtAQ = conn.prepareStatement(sqlAQ);
							pstmtAQ.setString(1, qn_id);
							pstmtAQ.setBigDecimal(2, q_id);
							pstmtAQ.setBigDecimal(3, i_id);
							ResultSet rsAQ = pstmtAQ.executeQuery();
							while(rsAQ.next()) {
								ExDbanswer exA = new ExDbanswer();
								exA.setPart(rsAQ);
								exI.getExAnswerList().add(exA);
							}
						}
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
	public int getQResultByQnIdQId(String qn_id, BigDecimal q_id, ArrayList<ExDbquestion> exQList) {
		int message = FAILED;
		String sqlQ = "select * from "
				+ "(select * from db_16.question where qn_id=? and q_id=?) question "
				+ "join (select qn_id, qn_title from db_16.questionnaire where qn_id=?) questionnaire "
				+ "using(qn_id) ";
		String sqlI = "select * from db_16.item "
				+ "where qn_id=? and q_id=? ";
		String sqlAC = "select count(*) i_a_count from db_16.answer "
				+ "where qn_id=? and q_id=? and i_id=? ";
		String sqlAQ = "select * from db_16.answer "
				+ "where qn_id=? and q_id=? and i_id=? ";
		exQList.clear();
		try {
			//根据qn_id,q_id取ExQ
			PreparedStatement pstmtQ = conn.prepareStatement(sqlQ);
			pstmtQ.setString(1, qn_id);
			pstmtQ.setBigDecimal(2, q_id);
			pstmtQ.setString(3, qn_id);
			ResultSet rsQ = pstmtQ.executeQuery();
			if(rsQ.next()) {
				ExDbquestion exQ = new ExDbquestion();
				exQ.setAll(rsQ);
				//根据qn_id, q_id取ExI
				PreparedStatement pstmtI = conn.prepareStatement(sqlI);
				pstmtI.setString(1, qn_id);
				pstmtI.setBigDecimal(2, q_id);
				ResultSet rsI = pstmtI.executeQuery();
				while(rsI.next()) {
					BigDecimal i_id = rsI.getBigDecimal("i_id");
					ExDbitem exI = new ExDbitem();
					exI.setPart(rsI);
					//根据qn_id, q_id, i_id取ExA
					PreparedStatement pstmtAC = conn.prepareStatement(sqlAC);
					pstmtAC.setString(1, qn_id);
					pstmtAC.setBigDecimal(2, q_id);
					pstmtAC.setBigDecimal(3, i_id);
					ResultSet rsAC = pstmtAC.executeQuery();
					rsAC.next();
					exI.setI_a_count(rsAC.getBigDecimal("i_a_count"));
					if(exI.getItem().getI_type().equals("que")) {
						PreparedStatement pstmtAQ = conn.prepareStatement(sqlAQ);
						pstmtAQ.setString(1, qn_id);
						pstmtAQ.setBigDecimal(2, q_id);
						pstmtAQ.setBigDecimal(3, i_id);
						ResultSet rsAQ = pstmtAQ.executeQuery();
						while(rsAQ.next()) {
							ExDbanswer exA = new ExDbanswer();
							exA.setPart(rsAQ);
							exI.getExAnswerList().add(exA);
						}
					}
					exQ.getExItemList().add(exI);
				}
				exQList.add(exQ);
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
	public int getQnResultAutho(String u_id, String qn_id) {
		int message = FAILED;
		String sql = "select * from db_16.own "
				+ "where u_id=? and qn_id=? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			pstmt.setString(2, qn_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
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
}
