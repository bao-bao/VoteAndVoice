package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;

/**
 * Servlet implementation class EndQuestionnaireServlet
 */
@WebServlet("/DeleteQuestionnaire")
public class DeleteQuestionnaireServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteQuestionnaireServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String qn_id = request.getParameter("qn_id");
        if(qn_id == null) {
            return;
        }
        int message = DAOFactory.getCreateQnDAO().deleteQnByQnId(qn_id);
        if(message < 0) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("MyQuestionnaire");
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
