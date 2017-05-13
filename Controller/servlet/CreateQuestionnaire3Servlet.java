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
 * Servlet implementation class CreateQuestionnaire3Servlet
 */
@WebServlet("/CreateQuestionnaire_owns")
public class CreateQuestionnaire3Servlet extends HttpServlet {
	public static final long COST = 20;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestionnaire3Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ExDbquestionnaire newExQn = (ExDbquestionnaire) session.getAttribute("newExQn");
		if(newExQn != null) {
			newExQn.set_transQn_cost(COST);
			session.setAttribute("newExQn", newExQn);
			request.getRequestDispatcher("creatingQuestionnaire4.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("CreateQuestionnaire_type");
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
