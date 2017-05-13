package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.*;
import vo.*;
import dao.*;
/**
 * Servlet implementation class GetQuestionnaireResult
 */
@WebServlet("/GetQuestionnaireResult")
public class GetQuestionnaireResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQuestionnaireResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int pos =  Integer.parseInt(request.getParameter("pos"));

		@SuppressWarnings("unchecked")
		ArrayList<String> idList = (ArrayList<String>)session.getAttribute("questionnaire");
		String qn_id = idList.get(pos);
		ArrayList<ExDbquestionnaire> newExQnList = new ArrayList<ExDbquestionnaire>();
		System.out.println(qn_id + "  " + pos);
		
		int message = DAOFactory.getGetResultDAO().getQnResultByQnId(qn_id, newExQnList);
		ArrayList<ExDbquestion> qList = newExQnList.get(0).get_transExQuestionList();
		
		String title = newExQnList.get(0).getQuestionnaire().get_transQn_title();
		String des =  newExQnList.get(0).get_transQuestionnaire().getQn_des();
		String order = "{'order': [ ";/////加了个空格
		String single = "{'single':[ ";/////加了个空格
		String multiple = "{'multiple':[ ";/////加了个空格
		String qanda = "{'qanda':[ ";/////加了个空格
		
		JSONArray jan = new JSONArray();
		int length = qList.size();
		System.out.println(length);
		for(int i = 0; i < length; ++ i){
			Dbquestion thisq = qList.get(i).getQuestion();
			ArrayList<ExDbitem> itemList = qList.get(i).getExItemList();
			
			String type = thisq.getQ_type();
			String stem  =thisq.get_transQ_stem();
			
			if(type.equals("sin")){
				//得到答案
				JSONArray options = new JSONArray();
				ArrayList<ExDbitem> optionList = qList.get(i).get_transExItemList();
				for(int k = 0; k < optionList.size(); ++ k){
					options.add(optionList.get(k).get_transI_a_count());
				}
				jan.add(options);
				//组装问题
				if(i != length - 1 ) order += "'single',";
				else order += "'single'";
				if(itemList.size() > 0) single += "['" + stem + "',";
				else  single += "['" + stem + "']";
				for(int j = 0; j < itemList.size(); ++ j){
					if(j != itemList.size() -1) single += "'" + itemList.get(j).getItem().get_transI_des() + "',";
					else single += "'" + itemList.get(j).getItem().get_transI_des() + "']";
				}
				single += ",";/////
			}
			else if( type.equals("mul")){
				//得到答案
				JSONArray options = new JSONArray();
				ArrayList<ExDbitem> optionList = qList.get(i).get_transExItemList();
				for(int k = 0; k < optionList.size(); ++ k){
					options.add(optionList.get(k).get_transI_a_count());
				}
				jan.add(options);
				//组装问题
				if(i != length - 1) order += "'multiple',";
				else order += "'multiple'";
				if(itemList.size() > 0) multiple += "['" + stem + "',";
				else multiple += "['" + stem + "']";
				for(int j  = 0; j < itemList.size(); ++ j){
					if(j != itemList.size() - 1) multiple += "'" + itemList.get(j).getItem().get_transI_des() + "',";
					else multiple += "'" + itemList.get(j).getItem().get_transI_des() + "']";
				}
				multiple += ",";/////
			}
			else if(type.equals("que")){
				//得到答案
				JSONArray options = new JSONArray();
				ExDbitem optionList = qList.get(i).get_transExItemList().get(0);
				ArrayList<ExDbanswer> exanList = optionList.get_transExAnswerList();
				for(int k = 0; k < exanList.size(); ++ k){
					options.add(exanList.get(k).get_transAnswer().get_transA_content());
				}
				jan.add(options);
				//组装问题
				if(i != length - 1) order += "'qanda',";
				else order += "'qanda'";
				qanda += "['" + stem +"']";
				qanda += ",";/////
			}
		
		}
		single = single.substring(0, single.length() - 1);/////
		multiple = multiple.substring(0, multiple.length() - 1);/////
		qanda = qanda.substring(0, qanda.length() - 1);/////
		order += "]}";
		single += "]}";
		multiple += "]}";
		qanda += "]}";
		
		request.setAttribute("title", title);
		request.setAttribute("des", des);
		request.setAttribute("order", order);
		request.setAttribute("single", single);
		request.setAttribute("multiple", multiple);
		request.setAttribute("qanda", qanda);
		
		String answer = jan.toString();
		request.setAttribute("answer", answer);

		System.out.println(title);
		System.out.println(order);
		System.out.println(single);
		System.out.println(multiple);
		System.out.println(qanda);
		System.out.println(answer);
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
