package servlet;/* Created by AMXPC on 2017/5/13. */

import dao.CreateQnDAO;
import dao.DAOFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import vo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ContinueQuestionnaire")
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
                        response.sendRedirect("/VoteAndVoice/SavedQuestionnaire");
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
                        System.out.println(1);
                        ExDbquestionnaire newExQn = ExQnList.get(0);
                        JSONObject qnaireJSON = JSONObject.fromObject(newExQn.getQuestionnaire().get_transQn_delay());
                        newExQn.getQuestionnaire().set_transQn_delay("");
                        session.setAttribute("newExQn", newExQn);
                        String order = qnaireJSON.getJSONObject("order").toString().replaceAll("\"","\'");
                        String single = qnaireJSON.getJSONObject("single").toString().replaceAll("\"","\'");
                        String multiple = qnaireJSON.getJSONObject("multiple").toString().replaceAll("\"","\'");
                        String qanda = qnaireJSON.getJSONObject("qanda").toString().replaceAll("\"","\'");

                        System.out.println(2);
                        String u_id = newExQn.getQuestionnaire().get_transS_id();
                        String title = newExQn.getQuestionnaire().get_transQn_title();
                        String des = newExQn.getQuestionnaire().get_transQn_des();

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
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
