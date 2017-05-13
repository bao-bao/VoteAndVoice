package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import vo.Dbown;
import vo.ExDbquestionnaire;

/**
 * Servlet implementation class CreateQuestionnaire4Servlet
 */
@WebServlet("/CreateQuestionnaire_main")
public class CreateQuestionnaire4Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestionnaire4Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = request.getParameter("followed_users");
		HttpSession session = request.getSession(true);
		ExDbquestionnaire newExQn = (ExDbquestionnaire)session.getAttribute("newExQn");
		if(json != null && newExQn != null) {
			json = new String(json.getBytes("ISO-8859-1"), "utf-8");
			JSONArray array = JSONArray.fromObject(json);
			newExQn.get_transOwnList().clear();
			Dbown sponsorOwn = new Dbown();
			sponsorOwn.set_transU_id(newExQn.get_transQuestionnaire().get_transS_id());
			sponsorOwn.set_transQn_id(newExQn.get_transQuestionnaire().get_transQn_id());
			newExQn.get_transOwnList().add(sponsorOwn);
			for(int i = 0; i != array.size(); i++) {
				String[] subStrings = array.get(i).toString().split("#");
				String followed_u_id = subStrings[subStrings.length-1];
				Dbown own = new Dbown();
				own.set_transU_id(followed_u_id);
				own.set_transQn_id(newExQn.get_transQuestionnaire().get_transQn_id());
				newExQn.get_transOwnList().add(own);
			}
			session.setAttribute("newExQn", newExQn);
			System.out.println(newExQn.get_transQuestionnaire().get_transS_id());
			System.out.println(newExQn.get_transQuestionnaire().get_transQn_type());
			System.out.println(newExQn.get_transQuestionnaire().get_transQn_tag());
			System.out.println(newExQn.get_transQuestionnaire().get_transQn_authority());
			System.out.println(newExQn.get_transQn_cost());
			System.out.println(newExQn.get_transOwnList().size());
			request.getRequestDispatcher("creatingQuestionnaire5.jsp").forward(request, response);
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
