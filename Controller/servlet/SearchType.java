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
 * Servlet implementation class SearchType
 */
@WebServlet("/SearchType")
public class SearchType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String keyType = request.getParameter("keyType");
		if(keyType == null) request.getRequestDispatcher("fillindex.jsp").forward(request, response);
		
		System.out.println(keyType);
		int totalCount = 10;
		String orderBy = "qn_title";
		
		ArrayList<ExDbquestionnaire> qnList = new ArrayList<ExDbquestionnaire>();
		int message = DAOFactory.getSearchQnDAO().getAllExQnsByQnTypeOrTag(keyType, qnList, totalCount, orderBy);
		if(message == SearchQnDAO.SUCCESS){
			request.setAttribute("qnaire", qnList);
			System.out.println(qnList.size());
			request.getRequestDispatcher("fill.jsp").forward(request, response);
		}else{
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
