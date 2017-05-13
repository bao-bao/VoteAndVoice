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
 * Servlet implementation class CreateQuestionnaire1
 */
@WebServlet("/CreateQuestionnaire_authority")
public class CreateQuestionnaire1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestionnaire1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String tag = request.getParameter("tag");
		//System.out.println(type+tag);
		HttpSession session = request.getSession(true);
		ExDbquestionnaire newExQn = (ExDbquestionnaire) session.getAttribute("newExQn");
		if(type!=null && tag!=null && newExQn!=null) {
			type = new String (type.getBytes("ISO-8859-1"), "utf-8");
			tag = new String (tag.getBytes("ISO-8859-1"), "utf-8");
			newExQn.getQuestionnaire().set_transQn_type(type);
			newExQn.getQuestionnaire().set_transQn_tag(tag);
			session.setAttribute("newExQn", newExQn);
			//session.setAttribute("createQnStage", 1);
			request.getRequestDispatcher("creatingQuestionnaire2.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("CreateQuestionnaire_type");
		}
/*		Integer createQnStage = (Integer) session.getAttribute("createQnStage");
		if(createQnStage!=null && createQnStage>0) {
			request.getRequestDispatcher("creatingQuestionnaire2.jsp").forward(request, response);
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
