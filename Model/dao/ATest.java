package dao;

public class ATest {
	public static void main(String args[]) {
		//ChangePwdDAO
		/*int result = DAOFactory.getChangePwdDAO().changePwd("zhz123", "123", "zhz123");
		System.out.println(result);*/
		
		//FollowDAO
		/*int result;
		ArrayList<Dbuser> friendsList = new ArrayList<Dbuser>();
		//result = DAOFactory.getFollowDAO().getFriends("zhz123", friendsList);
		//result = DAOFactory.getFollowDAO().getFollowedUser("zhz123", friendsList);
		result = DAOFactory.getFollowDAO().getFollowingUser("zhz123", friendsList);
		//result = DAOFactory.getFollowDAO().follow("zhz123", "yyf123");
		//result = DAOFactory.getFollowDAO().unfollow("zhz123", "yyf123");
		System.out.println(result);
		System.out.println("size:\t" + friendsList.size());
		for(int i = 0; i != friendsList.size(); i++) {
			System.out.println("No." + i + ":\t" + friendsList.get(i).getU_name());
		}*/
		
		//LoginDAO
		/*int result;
		ArrayList<Dbuser> userList = new ArrayList<Dbuser>();
		result = DAOFactory.getLoginDAO().login("zhz123", "zhz12", userList);
		System.out.println(result + "\nsize:\t" + userList.size());
		if(userList.size() > 0) System.out.println("Pass:\t" + userList.get(0).getU_pwd());*/
		
		//NewsDAO
		/*int result;
		//ArrayList<Dbanswer> list = new ArrayList<Dbanswer>();
		//result = DAOFactory.getNewsDAO().getAnswersByUId("guest0000", list);
		//result = DAOFactory.getNewsDAO().getFedAnswersByUId("yyf123", list, 0);
		//ArrayList<ExDbanswer> list = new ArrayList<ExDbanswer>();
		//result = DAOFactory.getNewsDAO().getExAnswersByUId("yyf123", list);
		//result = DAOFactory.getNewsDAO().getFedExAnswersByUId("yyf123", list, 0);
		//ArrayList<Dbquestionnaire> list = new ArrayList<Dbquestionnaire>();
		//result = DAOFactory.getNewsDAO().getQuestionnairesByUId("yyf123", list);
		//result = DAOFactory.getNewsDAO().getFedQuestionnairesByUId("bty123", list, 5);
		ArrayList<ExDbquestionnaire> list = new ArrayList<ExDbquestionnaire>();
		result = DAOFactory.getNewsDAO().getExQuestionnairesByUId("yyf123", list);
		//result = DAOFactory.getNewsDAO().getFedExQuestionnairesByUId("bty123", list, 0);
		System.out.println(result + "\nsize:\t" + list.size());*/
		System.out.println(DAOFactory.getNewsDAO().endQuestionnaireByQnId("00000000000000000020"));
		
		//RegisterDAO
		/*int result;
		Dbuser user = new Dbuser();
		user.setU_id("001");
		user.setU_pwd("123");
		user.setU_name("test");
		user.setU_gender("");
		result = DAOFactory.getRegisterDAO().register(user);
		System.out.println(result);*/
		
		//UserInfoDAO
		/*int result;
		ArrayList<Dbuser> userList = new ArrayList<Dbuser>();
		result = DAOFactory.getUserInfoDAO().getUserInfoByName("张", userList, -1);
		System.out.println(result);
		if(userList.size()>0)System.out.println(userList.get(0).getU_validity());
		userList.get(0).setU_ad_country("China");
		result = DAOFactory.getUserInfoDAO().changeUserInfo(userList.get(0));
		System.out.println(result);*/
		
		//SearchQnDAO
		/*int result;
		//ArrayList<ExDbquestionnaire> list = new ArrayList<ExDbquestionnaire>();
		//result = DAOFactory.getSearchQnDAO().getPubExQnsByQnTitle("", list, 2, "qn_title desc");
		//result = DAOFactory.getSearchQnDAO().getPubExQnsByQnTitleByQnTypeOrTag("ACG", list, 2, "qn_title desc");
		ArrayList<ExDbquestion> list = new ArrayList<ExDbquestion>();
		result = DAOFactory.getSearchQnDAO().getExQByAStem("", list, "pub", 0, null, "sin", null);
		System.out.println(result + "\nsize:\t" + list.size());*/
		
		//CreateQnDAO
		/*int result;		
		//ArrayList<Dbquestionnairetype> qnTypeList = new ArrayList<Dbquestionnairetype>();
		//result = DAOFactory.getCreateQnDAO().getQnTypes(qnTypeList);
		//System.out.println(result + "\nsize:\t" + qnTypeList.size());
		//for(int i = 0; i != qnTypeList.size(); i++)
		//	System.out.println(qnTypeList.get(i).getQn_type_name());
		
		ExDbquestionnaire exQn = new ExDbquestionnaire();
		exQn.getQuestionnaire().setQn_id("00000000000000000000");
		exQn.getQuestionnaire().setS_id("yyf123");
		exQn.getQuestionnaire().setQn_title("大学生读书时间调查");
		exQn.getQuestionnaire().setQn_des("读书使人进步");
		exQn.getQuestionnaire().setQn_type("文化");
		exQn.getQuestionnaire().setQn_tag("ACG");
		exQn.getQuestionnaire().setQn_authority("pri");
		exQn.getQuestionnaire().setQn_endtime(Timestamp.valueOf("2017-12-30 03:06:15"));
		
		//ArrayList<Dbquestionnaire> qnList = new ArrayList<Dbquestionnaire>();
		//qnList.add(exQn.getQuestionnaire());
		ArrayList<ExDbquestionnaire> exQnList = new ArrayList<ExDbquestionnaire>();
		exQnList.add(exQn);
		//result = DAOFactory.getCreateQnDAO().createQn(qnList, 1);
		
		//result = DAOFactory.getCreateQnDAO().setVCoin("yyf123", 100);
		for(int i = 0; i != 3; i++) {
			ExDbquestion exQ = new ExDbquestion();
			exQ.getQuestion().setQ_stem("测试题目");
			exQ.getQuestion().setQ_des("测试描述");
			exQ.getQuestion().setQ_type("sin");
			for(int j = 0; j != 2; j++) {
				ExDbitem exI = new ExDbitem();
				exI.getItem().setI_type("sin");
				exI.getItem().setI_des("测试选项");
				exQ.getExItemList().add(exI);
			}
			exQn.getExQuestionList().add(exQ);
		}
		//result = DAOFactory.getCreateQnDAO().createQsIs(exQn);
		
		ArrayList<Dbown> ownList = exQn.getOwnList();//new ArrayList<Dbown>();
		for(int i = 0; i != 3; i++) {
			Dbown own = new Dbown();
			//own.setQn_id("00000000000000000002");
			ownList.add(own);
		}
		ownList.get(0).setU_id("zhz123");
		ownList.get(1).setU_id("bty123");
		ownList.get(2).setU_id("xyj123");
		//result = DAOFactory.getCreateQnDAO().createOwnList(ownList);
		result = DAOFactory.getCreateQnDAO().createQnAll(exQnList);
		System.out.println(result + "\t" + exQn.getQuestionnaire().get_transQn_id());*/
		
		//AnswerQnDAO
		/*int result;
		//ArrayList<ExDbquestionnaire> exQnList = new ArrayList<ExDbquestionnaire>();
		//result = DAOFactory.getAnswerQnDAO().getQnByQnId("00000000000000000014", exQnList);
		ArrayList<Dbanswer> answerList = new ArrayList<Dbanswer>();
		for(int i = 0; i != 3; i++) {
			Dbanswer answer = new Dbanswer();
			answer.setU_id("zhz123");
			answer.setQn_id("00000000000000000014");
			answer.set_transI_id(1);
			answer.set_transQ_id(i + 1);
			answerList.add(answer);
		}
		result = DAOFactory.getAnswerQnDAO().answerQn(answerList);
		System.out.println(result);*/
		
		//GetResultDAO
		/*int result;
		//ArrayList<ExDbquestionnaire> exQnList = new ArrayList<ExDbquestionnaire>();
		//result = DAOFactory.getGetResultDAO().getQnResultByQnId("00000000000000000014", exQnList);
		ArrayList<ExDbquestion> exQList = new ArrayList<ExDbquestion>();
		result = DAOFactory.getGetResultDAO().getQResultByQnIdQId("00000000000000000027",BigDecimal.valueOf(3) , exQList);
		//result = DAOFactory.getGetResultDAO().getQnResultAutho("zhz123", "00000000000000000002");
		System.out.println(result);*/
	}
}
