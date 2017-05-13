package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import net.sf.json.JSONArray;
import vo.Dbquestion;
import vo.ExDbanswer;
import vo.ExDbitem;
import vo.ExDbquestion;

/**
 * Servlet implementation class GetQuestionResult
 */
@WebServlet("/GetQuestionResult")
public class GetQuestionResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQuestionResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int pos =  Integer.parseInt(request.getParameter("pos"));
		
		ArrayList<String> qnidList = (ArrayList<String>)session.getAttribute("questionqnid");
		ArrayList<Long> qidList = (ArrayList<Long>) session.getAttribute("questionqid");
		String qn_id = qnidList.get(pos);
		Long q_id = qidList.get(pos);
		System.out.println(qn_id + "  " + q_id + "  " + pos);
		
		ArrayList<ExDbquestion> newExQList = new ArrayList<ExDbquestion>();
		int message = DAOFactory.getGetResultDAO().getQResultByQnIdQId(qn_id, BigDecimal.valueOf(q_id), newExQList);
		if (message<0) {
			return;
		}
		ExDbquestion exQ = newExQList.get(0);
		String title = exQ.get_transQn_title();
		String des =  "";
		String order = "{'order': [";
		String single = "{'single':[";
		String multiple = "{'multiple':[";
		String qanda = "{'qanda':[";
		
		JSONArray jan = new JSONArray();
		int length = 1;
		System.out.println(length);
		for(int i = 0; i < length; ++ i){
			Dbquestion thisq = exQ.get_transQuestion();
			ArrayList<ExDbitem> itemList = exQ.getExItemList();
			
			String type = thisq.getQ_type();
			String stem  =thisq.get_transQ_stem();
			
			if(type.equals("sin")){
				//得到答案
				JSONArray options = new JSONArray();
				ArrayList<ExDbitem> optionList = exQ.get_transExItemList();
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
			}
			else if( type.equals("mul")){
				//得到答案
				JSONArray options = new JSONArray();
				ArrayList<ExDbitem> optionList = exQ.get_transExItemList();
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
			}
			else if(type.equals("que")){
				//得到答案
				JSONArray options = new JSONArray();
				ExDbitem optionList = exQ.get_transExItemList().get(0);
				ArrayList<ExDbanswer> exanList = optionList.get_transExAnswerList();
				for(int k = 0; k < exanList.size(); ++ k){
					options.add(exanList.get(k).get_transAnswer().get_transA_content());
				}
				jan.add(options);
				//组装问题
				if(i != length - 1) order += "'qanda',";
				else order += "'qanda'";
				qanda += "['" + stem +"']";
			}
		
		}
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
