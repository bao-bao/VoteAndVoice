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
import net.sf.json.JSONObject;
import vo.*;

/**
 * Servlet implementation class MyQuestionnaire
 */
@WebServlet("/QuestionnaireFromTemplate")
public class QuestionnaireFromTemplateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionnaireFromTemplateServlet() {
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
        String qn_id = request.getParameter("qn_id");
        ArrayList<ExDbquestionnaire> ExQnList = new ArrayList<>();
        int message = DAOFactory.getCreateQnDAO().loadQnDelayByQnId(qn_id, ExQnList);
        switch (message) {
            case CreateQnDAO.SUCCESS:
                System.out.println(1);
                ExDbquestionnaire template = ExQnList.get(0);
                JSONObject qnaireJSON = JSONObject.fromObject(template.getQuestionnaire().get_transQn_delay());
                template.getQuestionnaire().set_transQn_delay("");
                String order = qnaireJSON.getJSONObject("order").toString().replaceAll("\"","\'");
                String single = qnaireJSON.getJSONObject("single").toString().replaceAll("\"","\'");
                String multiple = qnaireJSON.getJSONObject("multiple").toString().replaceAll("\"","\'");
                String qanda = qnaireJSON.getJSONObject("qanda").toString().replaceAll("\"","\'");

                System.out.println(2);
                String title = template.getQuestionnaire().get_transQn_title();
                String des = "";

                System.out.println(3);
                request.setAttribute("u_id", u_id);
                request.setAttribute("qn_id", qn_id);
                request.setAttribute("title", title);
                request.setAttribute("des", des);
                request.setAttribute("order", order);
                request.setAttribute("single", single);
                request.setAttribute("multiple", multiple);
                request.setAttribute("qanda", qanda);

                System.out.println(4);
                request.getRequestDispatcher("/reconstructingQuestionnaire.jsp").forward(request, response);

                // TODO: JSON to Web
                break;
            case CreateQnDAO.EXCEPTION:
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
        doGet(request, response);
    }

}
