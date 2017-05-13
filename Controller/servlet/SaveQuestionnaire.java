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
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/SaveQuestionnaire")
public class SaveQuestionnaire extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        String des = request.getParameter("des");
        String order = request.getParameter("order");
        String single = request.getParameter("single");
        String multiple = request.getParameter("multiple");
        String qanda = request.getParameter("qanda");

        HttpSession session = request.getSession();
        ExDbquestionnaire newExQn = (ExDbquestionnaire)session.getAttribute("newExQn");

        if(newExQn == null){
            request.setAttribute("previewInfo","没有经过之前的建立步骤");
            request.getRequestDispatcher("previewQuestionnaire.jsp").forward(request, response);
            return;
        }
        Dbquestionnaire questionnaire = newExQn.getQuestionnaire();
        //设置问卷标题和描述
        questionnaire.set_transQn_title(title);
        questionnaire.set_transQn_des(des);

        SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        questionnaire.setQn_endtime(Timestamp.valueOf(df.format(new Date())));

        Map qmap = new HashMap();
        qmap.put("order", order);
        qmap.put("single", single);
        qmap.put("multiple", multiple);
        qmap.put("qanda", qanda);


        String qnaireString = (JSONObject.fromObject(qmap)).toString();
        questionnaire.set_transQn_delay(qnaireString);

        // TODO:
//        JSONObject qnaireJSON = JSONObject.fromObject(qnaireString);
//        JSONObject forder = qnaireJSON.getJSONObject("order");
//        JSONArray fsingle = qnaireJSON.getJSONArray("single");
//        JSONArray fmultiple = qnaireJSON.getJSONArray("multiple");
//        JSONArray fqanda = qnaireJSON.getJSONArray("qanda");
//

        int message = DAOFactory.getCreateQnDAO().saveQnDelay(newExQn);
        switch(message){
            case CreateQnDAO.EXCEPTION: {
                request.setAttribute("previewInfo", "后台故障");
                System.out.println("后台故障");
                request.getRequestDispatcher("previewSavedQuestionnaire.jsp").forward(request, response);
                return;
            }
            case CreateQnDAO.FAILED:{
                request.setAttribute("previewInfo", "保存失败,问卷不符合本平台规范");
                System.out.println("保存失败,问卷不符合本平台规范");
                request.getRequestDispatcher("previewSavedQuestionnaire.jsp").forward(request, response);
                return;
            }
            case CreateQnDAO.SUCCESS:{
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
        }
    }
}
