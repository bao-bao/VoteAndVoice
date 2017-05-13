package servlet;/* Created by AMXPC on 2017/5/13. */

import dao.DAOFactory;
import dao.FollowDAO;
import vo.Dbuser;
import vo.ExDbquestionnaire;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ContinueQuestionnaire")
public class ContinueQuestionnaire extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Dbuser user = (Dbuser)session.getAttribute("loginUser");
        int type = Integer.valueOf(request.getParameter("btntype"));
        String u_id = null;
        if(user != null) u_id = user.getU_id();
        if (u_id == null)
        {
            //u_id = "yyf123";
            request.getRequestDispatcher("/index.jsp").forward(request, response);/////
            return;/////
        }
        ArrayList<ExDbquestionnaire> savedQustionnaireList = new ArrayList<ExDbquestionnaire>();
        int message = DAOFactory.getCreateQnDAO().loadQnDelayByQnId(u_id, savedQustionnaireList);
        switch(message) {
            case FollowDAO.SUCCESS:
                System.out.println("id");
                request.setAttribute("savedQuestionnaireList", savedQustionnaireList);
                request.getRequestDispatcher("/savedQuestionnaire.jsp").forward(request, response);
                break;
            case FollowDAO.EXCEPTION:
                response.getWriter().append("somthing wrong with our website");
                break;
            default:
                break;
        }
        switch (type) {
            case 1:
                break;
            case 0:
                break;
                default:
                    break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
