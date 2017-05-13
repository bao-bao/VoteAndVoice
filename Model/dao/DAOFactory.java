package dao;

public class DAOFactory {
	static public LoginDAO getLoginDAO() {
		return new LoginDAO();
	}
	
	static public RegisterDAO getRegisterDAO() {
		return new RegisterDAO();
	}
	
	static public ChangePwdDAO getChangePwdDAO() {
		return new ChangePwdDAO();
	}
	
	static public FollowDAO getFollowDAO() {
		return new FollowDAO();
	}
	
	static public NewsDAO getNewsDAO() {
		return new NewsDAO();
	}
	
	static public UserInfoDAO getUserInfoDAO() {
		return new UserInfoDAO();
	}
	
	static public SearchQnDAO getSearchQnDAO() {
		return new SearchQnDAO();
	}
	
	static public CreateQnDAO getCreateQnDAO() {
		return new CreateQnDAO();
	}
	
	static public AnswerQnDAO getAnswerQnDAO() {
		return new AnswerQnDAO();
	}
	
	static public GetResultDAO getGetResultDAO() {
		return new GetResultDAO();
	}
}
