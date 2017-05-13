package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import vo.*;

/**
 * Servlet implementation class MyQuestionnaire
 */
@WebServlet("/MyQuestionnaire")
public class MyQuestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyQuestionnaire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String u_id = request.getParameter("u_id");
		HttpSession session = request.getSession(true);
		Dbuser user = (Dbuser)session.getAttribute("loginUser");
		String u_id = null;
		if(user != null) u_id = user.getU_id(); 
		if (u_id == null)
		{
			//u_id = "yyf123";
			request.getRequestDispatcher("/index.jsp").forward(request, response);/////
			return;/////
		}
		ArrayList<ExDbquestionnaire> myQustionnaireList = new ArrayList<ExDbquestionnaire>();			
		int message = DAOFactory.getNewsDAO().getExQuestionnairesByUId(u_id, myQustionnaireList);
		switch(message) {
		case FollowDAO.SUCCESS:
			System.out.println("id");
			request.setAttribute("myQustionnaireList", myQustionnaireList);
			request.getRequestDispatcher("/myQuestionnaire.jsp").forward(request, response);
			break;
		case FollowDAO.EXCEPTION:
			response.getWriter().append("somthing wrong with our website");
			break;
		default:
			break;
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
