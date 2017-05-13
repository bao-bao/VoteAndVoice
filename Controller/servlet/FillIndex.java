package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.SearchQnDAO;
import vo.ExDbquestionnaire;

/**
 * Servlet implementation class FillIndex
 */
@WebServlet("/FillIndex")
public class FillIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FillIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String key = request.getParameter("key");
		System.out.println(key);
		ArrayList<ExDbquestionnaire> qnaire =  new ArrayList<ExDbquestionnaire>();
		int authority = 0;
		int totalCount = 10;
		String  orderBy = "qn_title";
		String state = "ing";
		String authorityString = null;
		//int message = DAOFactory.getSearchQnDAO().getExQnsByQnTitle(key, qnaire, authority, totalCount, orderBy);
		int message = DAOFactory.getSearchQnDAO().getExQnsByQnTitle(key, qnaire, authorityString, totalCount, orderBy, state, -1, -1, null, -1, -1);
		if(message == SearchQnDAO.SUCCESS) {
			request.setAttribute("qnaire", qnaire);
			System.out.println(qnaire.size());
			request.getRequestDispatcher("fill.jsp").forward(request, response);
		}
		else if(message == SearchQnDAO.EXCEPTION){
			request.setAttribute("errorMessage", "exception");
			request.getRequestDispatcher("fillIndex.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
