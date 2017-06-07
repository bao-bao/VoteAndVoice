package servlet;

import dao.CreateQnDAO;
import dao.DAOFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import vo.Dbown;
import vo.ExDbquestion;
import vo.ExDbquestionnaire;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by cn_yyf on 2017/6/7.
 */
@WebServlet("/CreateQuestionnaire_templates")
public class CreateQuestionnaire5Servlet extends HttpServlet  {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuestionnaire5Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        ExDbquestionnaire newExQn = (ExDbquestionnaire)session.getAttribute("newExQn");
        if(newExQn != null) {
            String qn_type = newExQn.getQuestionnaire().getQn_type();
            String adm_id = "guest0000";
            ArrayList<ExDbquestionnaire> exQnList = new ArrayList<ExDbquestionnaire>();
            int message = DAOFactory.getCreateQnDAO().getTemplatesByQnType(qn_type,adm_id,exQnList);
            switch (message) {
                case CreateQnDAO.SUCCESS:
                    session.setAttribute("templateList", exQnList);
                    request.getRequestDispatcher("creatingQuestionnaire6.jsp").forward(request, response);
                    break;
                case CreateQnDAO.EXCEPTION:
                    response.getWriter().append("somthing wrong with our website");
                    break;
                default:
                    break;
            }
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
