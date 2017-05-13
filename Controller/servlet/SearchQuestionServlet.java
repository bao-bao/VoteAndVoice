package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import vo.ExDbquestion;

/**
 * Servlet implementation class SearchQuestion
 */
@WebServlet("/SearchQuestion")
public class SearchQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchQuestionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = toUTF8(request.getParameter("key"));
		String questionType = toUTF8(request.getParameter("questionType"));
		String questionnaireType = toUTF8(request.getParameter("questionnaireType"));
		if(key==null || questionType==null || questionnaireType==null) {
			request.getRequestDispatcher("searchQuestion.jsp").forward(request, response);
			return;
		}
		String order = null;
		String q_type = null;
		String qn_type = null;
		if(questionType.equals("单选题")) q_type = "sin";
		else if(questionType.equals("多选题")) q_type = "mul";
		else if(questionType.equals("问答题")) q_type = "que";
		if(!questionnaireType.equals("全部")) {
			qn_type = questionnaireType;
		}
		ArrayList<ExDbquestion> exQList = new ArrayList<ExDbquestion>();
		HttpSession session = request.getSession(true);
		int message = DAOFactory.getSearchQnDAO().getExQByAStem(key, exQList, "pub", 100, order, q_type, qn_type);
		if(message < 1) {
			response.getWriter().print("<script> alert(\"lost connection to mysql database!\"); </script>");
		}
		else {
			session.setAttribute("exQListSearch", exQList);
		}
		request.getRequestDispatcher("searchQuestion.jsp").forward(request, response);
	}

	public String toUTF8(String origin) {
		if(origin == null) {
			return null;
		}
		try {
			return new String (origin.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
