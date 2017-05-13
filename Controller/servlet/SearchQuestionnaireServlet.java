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
import vo.ExDbquestionnaire;

/**
 * Servlet implementation class SearchQuestionnaireServlet
 */
@WebServlet("/SearchQuestionnaire")
public class SearchQuestionnaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchQuestionnaireServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = toUTF8(request.getParameter("key"));
		String orderby = toUTF8(request.getParameter("orderby"));
		String totalNum = toUTF8(request.getParameter("totalNum"));
		String qType = toUTF8(request.getParameter("qType"));
		String collectNum = toUTF8(request.getParameter("collectNum"));
		//System.out.println(key + "\n" + orderby + "\n" + totalNum + "\n" + qType + "\n" + collectNum);
		if(key==null || orderby==null || totalNum==null || qType==null || collectNum==null) {
			request.getRequestDispatcher("searchQuestionnaire.jsp").forward(request, response);
			return;
		}
		String order = null;
		String state = null;
		long q_min = -1;
		long q_max = -1;
		String type = null;
		long a_min = -1;
		long a_max = -1;
		if(orderby.equals("最近开始")) order = "qn_starttime desc";
		else if(orderby.equals("最近结束")) order = "qn_endtime desc";
		else if(orderby.equals("最多问题")) order = "qn_q_count desc";
		else if(orderby.equals("最少问题")) order = "qn_q_count";
		else if(orderby.equals("最多回答")) order = "qn_a_count desc";
		state = null;//这里改状态////////////////////////
		String[] q_count = totalNum.split("-");
		if(q_count.length == 2 && !totalNum.equals("全部")) {
			q_min = Long.parseLong(q_count[0]);
			if(!q_count[1].equals("more")) q_max = Long.parseLong(q_count[1]);
		}
		if(!qType.equals("全部")) type = qType;
		String[] a_count = collectNum.split("-");
		if(a_count.length == 2 && !totalNum.equals("全部")) {
			a_min = Long.parseLong(a_count[0]);
			if(!a_count[1].equals("more")) a_max = Long.parseLong(a_count[1]);
		}
		ArrayList<ExDbquestionnaire> exQnList = new ArrayList<ExDbquestionnaire>();
		HttpSession session = request.getSession(true);
		int message = DAOFactory.getSearchQnDAO().getExQnsByQnTitle(key, exQnList, "pub", 100, order, state, q_min, q_max, type, a_min, a_max);
		if(message < 1) {
			response.getWriter().print("<script> alert(\"lost connection to mysql database!\"); </script>");
		}
		else {
			session.setAttribute("exQnListSearch", exQnList);
		}
		request.getRequestDispatcher("searchQuestionnaire.jsp").forward(request, response);
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
