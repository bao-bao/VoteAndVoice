package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import vo.Dbuser;
import vo.ExDbquestionnaire;

/**
 * Servlet implementation class CreateQuestionnaire0Servlet
 */
@WebServlet("/CreateQuestionnaire_type")
public class CreateQuestionnaire0Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestionnaire0Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		Dbuser loginUser = (Dbuser) session.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		String u_id = loginUser.get_transU_id();
		if(u_id != null) {
			ExDbquestionnaire newExQn = new ExDbquestionnaire();
			newExQn.get_transQuestionnaire().set_transS_id(u_id);
			session.setAttribute("newExQn", newExQn);
			ArrayList<String> typeList = new ArrayList<String>();
			int message = DAOFactory.getCreateQnDAO().getQnTypesNums("guest0000", typeList);
			if(message<0){
				response.sendRedirect("index.jsp");
				return;
			}
			session.setAttribute("typeList", typeList);
			request.getRequestDispatcher("creatingQuestionnaire1.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("index.jsp");
			return;
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
