package servlet;/* Created by AMXPC on 2017/5/13. */

import dao.CreateQnDAO;
import dao.DAOFactory;
import net.sf.json.JSONObject;
import vo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ContinueQuestionnaire")
public class ContinueQuestionnaire extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        int type = Integer.valueOf(request.getParameter("btntype"));
        String qn_id = request.getParameter("qn_id");
        int message;
        switch (type) {
            // delete
            case 1:
                message = DAOFactory.getCreateQnDAO().deleteQnDelayByQnId(qn_id);
                switch (message) {
                    case CreateQnDAO.SUCCESS:
                        request.getRequestDispatcher("/savedQuestionnaire.jsp").forward(request, response);
                        break;
                    case CreateQnDAO.EXCEPTION:
                        response.getWriter().append("somthing wrong with our website");
                        break;
                    default:
                        break;
                }
                break;
            // edit
            case 0:
                ArrayList<ExDbquestionnaire> ExQnList = new ArrayList<>();
                message = DAOFactory.getCreateQnDAO().loadQnDelayByQnId(qn_id, ExQnList);
                switch (message) {
                    case CreateQnDAO.SUCCESS:
                        JSONObject qnaireJSON = JSONObject.fromObject(ExQnList.get(0).getQuestionnaire().get_transQn_delay());
                        JSONObject order = qnaireJSON.getJSONObject("order");
                        JSONObject single = qnaireJSON.getJSONObject("single");
                        JSONObject multiple = qnaireJSON.getJSONObject("multiple");
                        JSONObject qanda = qnaireJSON.getJSONObject("qanda");
                        // TODO: JSON to Web
                        break;
                    case CreateQnDAO.EXCEPTION:
                        response.getWriter().append("somthing wrong with our website");
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
