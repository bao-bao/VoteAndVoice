package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ExDbquestionnaire;

/**
 * Servlet implementation class CreateQuestionnaire2Servlet
 */
@WebServlet("/CreateQuestionnaire_payment")
public class CreateQuestionnaire2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestionnaire2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String authority = request.getParameter("authority");
		HttpSession session = request.getSession(true);
		ExDbquestionnaire newExQn = (ExDbquestionnaire) session.getAttribute("newExQn");
		if(authority != null && newExQn != null) {
			authority = new String(authority.getBytes("ISO-8859-1"), "utf-8");
			//System.out.println(authority + authority.equals("公开"));
			if(authority.equals("私有")) {
				newExQn.get_transQuestionnaire().set_transQn_authority("私有");
				session.setAttribute("newExQn", newExQn);
				//锅
				//System.out.println(newExQn.getQuestionnaire().getQn_authority());
				//session.setAttribute("createQnStage", 2);
				request.getRequestDispatcher("creatingQuestionnaire3.jsp").forward(request, response);
			}
			else if (authority.equals("公开")) {
				newExQn.get_transQuestionnaire().set_transQn_authority("公有");
				session.setAttribute("newExQn", newExQn);
				//System.out.println(newExQn.getQuestionnaire().getQn_authority());
				//session.setAttribute("createQnStage", 4);
				request.getRequestDispatcher("creatingQuestionnaire5.jsp").forward(request, response);
			}
			else {
				response.sendRedirect("CreateQuestionnaire_authority");
			}
		}
		else {
			response.sendRedirect("CreateQuestionnaire_type");
		}
/*		if(newExQn == null) {
			session.setAttribute("createQnStage", 0);
			response.sendRedirect("CreateQuestionnaire_type");
		}
		Integer createQnStage = (Integer) session.getAttribute("createQnStage");
		if(createQnStage == null) {
			createQnStage = 0;
		}
		if(createQnStage > 1) {
			if(newExQn.get_transQuestionnaire().get_transQn_authority().equals("公有")) {
				request.getRequestDispatcher("creatingQuestionnaire3.jsp").forward(request, response);
			}
			else if(newExQn.get_transQuestionnaire().get_transQn_authority().equals("私有")) {
				response.sendRedirect("CreateQuestionnaire_main");
			}
		}
		else if(createQnStage == 1) {
			response.sendRedirect("CreateQuestionnaire_authority");
		}
		else {
			response.sendRedirect("CreateQuestionnaire_type");
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
