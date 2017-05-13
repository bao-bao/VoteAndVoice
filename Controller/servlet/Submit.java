package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.GetResultDAO;
import vo.ExDbquestionnaire;
import vo.*;
import java.util.*;
import net.sf.json.*;
/**
 * Servlet implementation class Submit
 */
@WebServlet("/submit")
public class Submit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Submit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u_id = request.getParameter("u_id");
		String qn_id = request.getParameter("qn_id");
		String answer = request.getParameter("answer");
		System.out.println(answer);
		System.out.println(u_id + "   "+qn_id);
		JSONObject janswer = JSONObject.fromObject(answer);

		ArrayList<ExDbquestionnaire> exQnList = new ArrayList<ExDbquestionnaire>();
		int message = DAOFactory.getGetResultDAO().getQnResultByQnId(qn_id, exQnList);

		String rejson = null;
		if(message == GetResultDAO.EXCEPTION || message == GetResultDAO.FAILED){
			rejson = "{'errorMessage': '获取故障'}";
			return;
		}
		ArrayList<ExDbquestion> qList = exQnList.get(0).get_transExQuestionList();

		ArrayList<Dbanswer> result = new ArrayList<Dbanswer>();
		for(int i = 0; i < qList.size(); ++ i){
			Dbquestion thisq = qList.get(i).get_transQuestion();
			ArrayList<ExDbitem> itemList = qList.get(i).getExItemList();

			BigDecimal q_id = thisq.getQ_id();

			System.out.println(q_id);
			String type = thisq.getQ_type();
			if(janswer.containsKey(String.valueOf(i))) {/////
				if(type.equals("sin") == true){
					Dbanswer an = new Dbanswer();
					an.set_transQn_id(qn_id);
					an.set_transU_id(u_id);
					an.setQ_id(q_id);
					int option = janswer.getInt(String.valueOf(i));
					an.setI_id(itemList.get(option).get_transItem().getI_id());
					result.add(an);
				}
				else if(type.equals("mul") == true){
					JSONArray options = janswer.getJSONArray(String.valueOf(i));
					for(int j = 0; j < options.size(); ++ j){
						Dbanswer an = new Dbanswer();
						an.set_transQn_id(qn_id);
						an.set_transU_id(u_id);
						an.setQ_id(q_id);

						int option = options.getInt(j);
						an.setI_id(itemList.get(option).get_transItem().getI_id());
						result.add(an);
					}
				}
				else if(type.equals("que") == true){
					Dbanswer an = new Dbanswer();
					an.set_transQn_id(qn_id);
					an.set_transU_id(u_id);
					an.setQ_id(q_id);
					System.out.println(itemList.size());
					if(itemList.size() > 0) an.setI_id(itemList.get(0).get_transItem().getI_id());
					else an.set_transI_id(1);
					String a_content = janswer.getString(String.valueOf(i));
					an.set_transA_content(a_content);
					result.add(an);
				}
			}/////
		}
		message = DAOFactory.getAnswerQnDAO().answerQn(result);
		if(message == GetResultDAO.EXCEPTION || message == GetResultDAO.FAILED){
			rejson = "{'errorMessage': '提交故障'}";
			return;
		}
		else if(message == GetResultDAO.SUCCESS){
			rejson = "{'errorMessage': 'success'}";
		}
		System.out.println(rejson);
		//写回JSON
		PrintWriter pw = response.getWriter();
		pw.print(rejson);
		pw.flush();
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}