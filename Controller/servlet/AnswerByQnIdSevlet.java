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
 * Created by cn_yyf on 2017/6/7.
 */
@WebServlet("/AnswerByQnId")
public class AnswerByQnIdSevlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerByQnIdSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Dbuser loginUser = (Dbuser) session.getAttribute("loginUser");
        String qn_id = (String)request.getParameter("qn_id");
        String u_id = "guest0000";
        if(loginUser != null) {
            u_id = loginUser.getU_id();
        }
        session.setAttribute("u_id", u_id);
        ArrayList<ExDbquestionnaire> exQnList = new ArrayList<>();
        int message = DAOFactory.getGetResultDAO().getQnResultByQnId(qn_id, exQnList);
        switch (message) {
            case GetResultDAO.SUCCESS:
                session.setAttribute("questionnaire", exQnList);
                request.getRequestDispatcher("/fetchingQuestionnaire.jsp?pos=0").forward(request, response);
                break;
            case GetResultDAO.FAILED:
                response.getWriter().append("no such questionnaire");
                break;
            default:
                response.getWriter().append("somthing wrong with our website");
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
