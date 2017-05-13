package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbc.DatabaseConnection;
import vo.Dbown;
import vo.Dbquestionnaire;
import vo.Dbquestionnairetype;
import vo.Dbuser;
import vo.ExDbitem;
import vo.ExDbquestion;
import vo.ExDbquestionnaire;

public class CreateQnDAO {
	private DatabaseConnection dbconn = null;
	private Connection conn = null;

	static public final int EXCEPTION = -1;
	static public final int SUCCESS = 1;
	static public final int FAILED = -2;
	static public final int INSUFFICIENT = -5;
	
	public CreateQnDAO() {
		try {
			dbconn = new DatabaseConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = dbconn.getConnection();
	}
	
	public int getQnTypes(ArrayList<Dbquestionnairetype> qnTypeList) {
		int message = EXCEPTION;
		String sql = "select * from db_16.questionnairetype ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Dbquestionnairetype questionnairetype = new Dbquestionnairetype();
				questionnairetype.setAll(rs);
				qnTypeList.add(questionnairetype);
			}
			message = SUCCESS;
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
	
	public int createQn(ArrayList<Dbquestionnaire> questionnaireList, long cost) {
		int message = FAILED;
		String sqlQn = "insert into db_16.questionnaire(qn_id, s_id, qn_title, qn_des, qn_type, qn_tag, qn_authority, qn_endtime) "
				+ "values(?,?,?,?,?,?,?,?) ";
		String sqlGetQnId = "select * from db_16.questionnaire "
				+ "where s_id = ? "
				+ "order by qn_starttime desc ";
		String sqlChangeVcoin = "update db_16.user "
				+ "set v_coin = ? "
				+ "where u_id = ? ";
		try {
			conn.setAutoCommit(false);
			if(questionnaireList.size() != 0) {
				String s_id = questionnaireList.get(0).getS_id();
				PreparedStatement pstmtQn = conn.prepareStatement(sqlQn);
				pstmtQn.setString(1, questionnaireList.get(0).getQn_id());
				pstmtQn.setString(2, s_id);
				pstmtQn.setString(3, questionnaireList.get(0).getQn_title());
				pstmtQn.setString(4, questionnaireList.get(0).getQn_des());
				pstmtQn.setString(5, questionnaireList.get(0).getQn_type());
				pstmtQn.setString(6, questionnaireList.get(0).getQn_tag());
				pstmtQn.setString(7, questionnaireList.get(0).getQn_authority());
				pstmtQn.setTimestamp(8, questionnaireList.get(0).getQn_endtime());
				int i = pstmtQn.executeUpdate();
				if(i > 0) {
					PreparedStatement pstmtGetQnId = conn.prepareStatement(sqlGetQnId);
					pstmtGetQnId.setString(1, s_id);
					ResultSet rs = pstmtGetQnId.executeQuery();
					if(rs.next()) {
						if(rs.getBigDecimal("qn_q_count").longValue() == 0) {
							questionnaireList.get(0).setAll(rs);
							ArrayList<Dbuser> userList = new ArrayList<Dbuser>();
							int checkMessage = DAOFactory.getUserInfoDAO().getUserInfo(s_id, userList);
							if(checkMessage > 0) {
								long oldVCoin = userList.get(0).getV_coin().longValue();
								long newVCoin = oldVCoin - cost;
								if(newVCoin >= 0) {
									PreparedStatement pstmtChangeVcoin = conn.prepareStatement(sqlChangeVcoin);
									pstmtChangeVcoin.setBigDecimal(1, BigDecimal.valueOf(newVCoin));
									pstmtChangeVcoin.setString(2, s_id);
									int changeVcoinCount = pstmtChangeVcoin.executeUpdate();
									if(changeVcoinCount > 0) {
										message = SUCCESS;
									}
								}
								else {
									message = INSUFFICIENT;
								}
							}
							else {
								message = checkMessage;
							}
						}
					}
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
	
	public int setVCoin(String u_id, long vCoinChange) {
		int message = EXCEPTION;
		String sql = "update db_16.user "
				+ "set v_coin = ? "
				+ "where u_id = ? ";
		try {
			ArrayList<Dbuser> userList = new ArrayList<Dbuser>();
			int checkMessage = DAOFactory.getUserInfoDAO().getUserInfo(u_id, userList);
			if(checkMessage < 0) {
				message = checkMessage;
				dbconn.close();
				return message;
			}
			long oldVCoin = userList.get(0).getV_coin().longValue();
			long newVCoin = oldVCoin + vCoinChange;
			if(newVCoin < 0) {
				message = INSUFFICIENT;
				dbconn.close();
				return message;
			}
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setBigDecimal(1, BigDecimal.valueOf(newVCoin));
			pstmt.setString(2, u_id);
			int i = pstmt.executeUpdate();
			if(i > 0) {
				message = SUCCESS;
			}
			else {
				message = FAILED;
			}
		} catch (Exception e) {
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
	
	public int createOwnList(ArrayList<Dbown> ownList) {
		int message = EXCEPTION;
		String sql = "insert into db_16.own(u_id, qn_id) "
				+ "values(?,?) ";
		try {
			conn.setAutoCommit(false);
			for(int i = 0; i != ownList.size(); i++) {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ownList.get(i).getU_id());
				pstmt.setString(2, ownList.get(i).getQn_id());
				int count = pstmt.executeUpdate();
				if(count == 0){
					message = FAILED;
					conn.rollback();
					dbconn.close();
					return message;
				}
			}
			conn.commit();
			message = SUCCESS;
		} catch (Exception e) {
			message = EXCEPTION;
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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

	public int createQsIs(ExDbquestionnaire exQuestionnaire) {
		int message = FAILED;
		String sqlQn = "update db_16.questionnaire "
				+ "set qn_q_count = ? "
				+ "where qn_id = ? ";
		String sqlQ = "insert into db_16.question(qn_id, q_id, q_type, q_stem, q_des, q_i_count) "
				+ "values(?,?,?,?,?,?)";
		String sqlI = "insert into db_16.item(qn_id, q_id, i_id, i_type, i_des) "
				+ "values(?,?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			String qn_id = exQuestionnaire.getQuestionnaire().getQn_id();
			ArrayList<ExDbquestion> exQuestionList = exQuestionnaire.getExQuestionList();
			PreparedStatement pstmtQn = conn.prepareStatement(sqlQn);
			pstmtQn.setBigDecimal(1, BigDecimal.valueOf((long)exQuestionList.size()));
			pstmtQn.setString(2, qn_id);
			int countQn = pstmtQn.executeUpdate();
			if(countQn > 0) {
				message = SUCCESS;
				createQsIsOUT: for(int i = 0; i != exQuestionList.size(); i++) {
					ExDbquestion exQuestion = exQuestionList.get(i);
					ArrayList<ExDbitem> exItemList = exQuestionList.get(i).getExItemList();
					PreparedStatement pstmtQ = conn.prepareStatement(sqlQ);
					pstmtQ.setString(1, qn_id);
					pstmtQ.setBigDecimal(2, BigDecimal.valueOf((long)i + 1));
					pstmtQ.setString(3, exQuestion.getQuestion().getQ_type());
					pstmtQ.setString(4, exQuestion.getQuestion().getQ_stem());
					pstmtQ.setString(5, exQuestion.getQuestion().getQ_des());
					pstmtQ.setBigDecimal(6, BigDecimal.valueOf((long)exItemList.size()));
					int countQ = pstmtQ.executeUpdate();
					if(countQ == 0){
						message = FAILED;
						break createQsIsOUT;
					}
					for(int j = 0; j != exItemList.size(); j++) {
						ExDbitem exItem = exItemList.get(j);
						PreparedStatement pstmtI = conn.prepareStatement(sqlI);
						pstmtI.setString(1, qn_id);
						pstmtI.setBigDecimal(2, BigDecimal.valueOf((long)i + 1));
						pstmtI.setBigDecimal(3, BigDecimal.valueOf((long)j + 1));
						pstmtI.setString(4, exItem.getItem().getI_type());
						pstmtI.setString(5, exItem.getItem().getI_des());
						int countI = pstmtI.executeUpdate();
						if(countI == 0){
							message = FAILED;
							break createQsIsOUT;
						}
					}
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

	public int createQnAll(ArrayList<ExDbquestionnaire> exQnList) {
		int message = FAILED;
		String sqlQn = "insert into db_16.questionnaire(qn_id, s_id, qn_title, qn_des, qn_type, qn_tag, qn_authority, qn_endtime) "
				+ "values(?,?,?,?,?,?,?,?) ";
		String sqlGetQnId = "select * from db_16.questionnaire "
				+ "where s_id = ? "
				+ "order by qn_starttime desc ";
		String sqlChangeVcoin = "update db_16.user "
				+ "set v_coin = ? "
				+ "where u_id = ? ";
		String sqlO = "insert into db_16.own(u_id, qn_id) "
				+ "values(?,?) ";
		String sqlQnUp = "update db_16.questionnaire "
				+ "set qn_q_count = ? "
				+ "where qn_id = ? ";
		String sqlQ = "insert into db_16.question(qn_id, q_id, q_type, q_stem, q_des, q_i_count) "
				+ "values(?,?,?,?,?,?)";
		String sqlI = "insert into db_16.item(qn_id, q_id, i_id, i_type, i_des) "
				+ "values(?,?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			ExDbquestionnaire exQn = null;
			String qn_id = null;
			if(exQnList.size() != 0) {
				exQn = exQnList.get(0);
				String s_id = exQn.getQuestionnaire().getS_id();
				qn_id = exQn.getQuestionnaire().getQn_id();
				long cost = exQn.getQn_cost();
				PreparedStatement pstmtQn = conn.prepareStatement(sqlQn);
				pstmtQn.setString(1, "");
				pstmtQn.setString(2, s_id);
				pstmtQn.setString(3, exQn.getQuestionnaire().getQn_title());
				pstmtQn.setString(4, exQn.getQuestionnaire().getQn_des());
				pstmtQn.setString(5, exQn.getQuestionnaire().getQn_type());
				pstmtQn.setString(6, exQn.getQuestionnaire().getQn_tag());
				pstmtQn.setString(7, exQn.getQuestionnaire().getQn_authority());
				pstmtQn.setTimestamp(8, exQn.getQuestionnaire().getQn_endtime());
				int i = pstmtQn.executeUpdate();
				if(i > 0) {
					PreparedStatement pstmtGetQnId = conn.prepareStatement(sqlGetQnId);
					pstmtGetQnId.setString(1, s_id);
					ResultSet rs = pstmtGetQnId.executeQuery();
					if(rs.next()) {
						if(rs.getBigDecimal("qn_q_count").longValue() == 0) {
							exQn.setPart(rs);
							ArrayList<Dbuser> userList = new ArrayList<Dbuser>();
							int checkMessage = DAOFactory.getUserInfoDAO().getUserInfo(s_id, userList);
							if(checkMessage > 0) {
								long oldVCoin = userList.get(0).getV_coin().longValue();
								long newVCoin = oldVCoin - cost;
								if(newVCoin >= 0) {
									PreparedStatement pstmtChangeVcoin = conn.prepareStatement(sqlChangeVcoin);
									pstmtChangeVcoin.setBigDecimal(1, BigDecimal.valueOf(newVCoin));
									pstmtChangeVcoin.setString(2, s_id);
									int changeVcoinCount = pstmtChangeVcoin.executeUpdate();
									if(changeVcoinCount > 0) {
										message = SUCCESS;
									}
								}
								else {
									message = INSUFFICIENT;
								}
							}
							else {
								message = checkMessage;
							}
						}
					}
				}
			}
			
			if(message == SUCCESS) {
				qn_id = exQn.getQuestionnaire().getQn_id();	
			}
			
			if(message == SUCCESS && exQn.getQuestionnaire().getQn_authority().equals("pri")) {
				ArrayList<Dbown> ownList = exQn.getOwnList();
				conn.setAutoCommit(false);
				for(int i = 0; i != ownList.size(); i++) {
					PreparedStatement pstmt = conn.prepareStatement(sqlO);
					pstmt.setString(1, ownList.get(i).getU_id());
					pstmt.setString(2, qn_id);
					int count = pstmt.executeUpdate();
					if(count == 0){
						message = FAILED;
						break;
					}
				}
			}
			
			if(message == SUCCESS) {
				message = FAILED;
				ArrayList<ExDbquestion> exQuestionList = exQn.getExQuestionList();
				PreparedStatement pstmtQnUp = conn.prepareStatement(sqlQnUp);
				pstmtQnUp.setBigDecimal(1, BigDecimal.valueOf((long)exQuestionList.size()));
				System.out.println(exQuestionList.size());
				System.out.println("qnid" + qn_id);
				pstmtQnUp.setString(2, qn_id);
				int countQn = pstmtQnUp.executeUpdate();
				System.out.println(countQn);
				if(countQn > 0) {
					System.out.println("qnup");
					message = SUCCESS;
					createQsIsOUT: for(int i = 0; i != exQuestionList.size(); i++) {
						ExDbquestion exQuestion = exQuestionList.get(i);
						ArrayList<ExDbitem> exItemList = exQuestionList.get(i).getExItemList();
						PreparedStatement pstmtQ = conn.prepareStatement(sqlQ);
						pstmtQ.setString(1, qn_id);
						pstmtQ.setBigDecimal(2, BigDecimal.valueOf((long)i + 1));
						pstmtQ.setString(3, exQuestion.getQuestion().getQ_type());
						pstmtQ.setString(4, exQuestion.getQuestion().getQ_stem());
						pstmtQ.setString(5, exQuestion.getQuestion().getQ_des());
						pstmtQ.setBigDecimal(6, BigDecimal.valueOf((long)exItemList.size()));
						int countQ = pstmtQ.executeUpdate();
						if(countQ == 0){
							message = FAILED;
							break createQsIsOUT;
						}
						for(int j = 0; j != exItemList.size(); j++) {
							ExDbitem exItem = exItemList.get(j);
							PreparedStatement pstmtI = conn.prepareStatement(sqlI);
							pstmtI.setString(1, qn_id);
							pstmtI.setBigDecimal(2, BigDecimal.valueOf((long)i + 1));
							pstmtI.setBigDecimal(3, BigDecimal.valueOf((long)j + 1));
							pstmtI.setString(4, exItem.getItem().getI_type());
							pstmtI.setString(5, exItem.getItem().getI_des());
							int countI = pstmtI.executeUpdate();
							if(countI == 0){
								message = FAILED;
								break createQsIsOUT;
							}
						}
					}
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
}
