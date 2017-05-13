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
 * Servlet implementation class AnswerServlet
 */
@WebServlet("/Answer")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String qn_id = "00000000000000000001";
		ArrayList<ExDbquestionnaire> AnswerQuestionnaireList = new ArrayList<ExDbquestionnaire>();
		int message = DAOFactory.getAnswerQnDAO().getQnByQnId(qn_id, AnswerQuestionnaireList);
		switch(message) {
		case AnswerQnDAO.SUCCESS:
			HttpSession session = request.getSession(true);
			System.out.println("qn_id");
			ExDbquestionnaire AnswerQuestionnaire = AnswerQuestionnaireList.get(0);
			request.setAttribute("AnswerQuestionnaire", AnswerQuestionnaire);
			request.getRequestDispatcher("/answer.jsp").forward(request, response);
			break;
		case AnswerQnDAO.FAILED:
			response.getWriter().append("there is no such id");
			break;
		case AnswerQnDAO.EXCEPTION:
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
