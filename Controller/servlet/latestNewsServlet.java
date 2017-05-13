package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.NewsDAO;
import vo.Dbuser;
import vo.ExDbanswer;
import vo.ExDbquestionnaire;


/**
 * Servlet implementation class latestNewsServlet
 */
@WebServlet("/latestNews")
public class latestNewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Comparator For News Map
    static class MapKeyComparator implements Comparator<String>{
        @Override
        public int compare(String str1, String str2) {
            return str2.compareTo(str1);
        }
    }

    // Sort In Map By Key
    static private Map<String, ArrayList<Object>> sortMapByKey(Map<String, ArrayList<Object>> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, ArrayList<Object>> sortMap = new TreeMap<String, ArrayList<Object>>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public latestNewsServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String u_id = "yyf123";
        HttpSession session = request.getSession(true);
        Dbuser user = (Dbuser)session.getAttribute("loginUser");
        if(user != null) u_id = user.getU_id();
        // Array For Questionnaire/Answer
        ArrayList<ExDbquestionnaire> NewQuestionnaireList = new ArrayList<>();
        ArrayList<ExDbanswer> NewAnswerList = new ArrayList<>();
        // Mapping For All News
        Map<String, ArrayList<Object>> NewsMap = new HashMap<String, ArrayList<Object>>();
        // Key Of Map
        String Date;
        // Message From DAO
        int QuestionnaireMessage = DAOFactory.getNewsDAO().getFedExQuestionnairesByUId(u_id, NewQuestionnaireList, 100);
        int AnswerMessage = DAOFactory.getNewsDAO().getFedExAnswersByUId(u_id, NewAnswerList, 100);

        // Exceptions Judge
        switch(QuestionnaireMessage) {
            case NewsDAO.EXCEPTION:
                response.getWriter().append("MySQL fault.");
                break;
            case NewsDAO.SUCCESS:
                break;
            default:
                break;
        }
        switch(AnswerMessage) {
            case NewsDAO.EXCEPTION:
                response.getWriter().append("MySQL fault.");
                break;
            case NewsDAO.SUCCESS:
                break;
            default:
                break;
        }

        if(NewsDAO.SUCCESS == QuestionnaireMessage && NewsDAO.SUCCESS == AnswerMessage) {
            // Create Map For 5 Days
            Calendar cal = Calendar.getInstance();
            for(int i = 0; i < 6; i++) {
                String date = new SimpleDateFormat("YYYY-MM-dd").format(cal.getTime());
                NewsMap.computeIfAbsent(date, k ->new ArrayList<Object>());
                cal.add(Calendar.DATE, -1);
            }
            // Add Answered Questionnaires To Map
            for(ExDbanswer Answer : NewAnswerList) {
                Date = Answer.getAnswer().get_transA_timestamp().substring(0, 10);
                if(NewsMap.get(Date) != null) {
                    NewsMap.get(Date).add(Answer);
                }

                // Remove Duplicate Answer In Same Questionnaire
                for(ArrayList<Object> oneDayNews : NewsMap.values()) {

                    for(int i = 0; i < oneDayNews.size(); i++) {
                        ExDbanswer answer1 = (ExDbanswer)oneDayNews.get(i);

                        for(int j = i + 1; j < oneDayNews.size(); j++) {
                            ExDbanswer answer2 = (ExDbanswer)oneDayNews.get(j);

                            if(answer1.getAnswer().get_transQn_id().equals( answer2.getAnswer().get_transQn_id())) {
                                oneDayNews.remove(j);
                                j--;
                            }
                        }
                    }
                }
            }
            // Add Questionnaires To Map
            for(ExDbquestionnaire Questionnaire : NewQuestionnaireList) {
                Date = Questionnaire.getQuestionnaire().get_transQn_starttime().substring(0, 10);
                if(NewsMap.get(Date) != null) {
                    NewsMap.get(Date).add(Questionnaire);
                }
            }
            // Map To Array, Easy For Show
            Map<String, ArrayList<Object>> resultMap = sortMapByKey(NewsMap);
            List<String> dateList = new ArrayList<>(resultMap.keySet());
            List<ArrayList<Object>> newsList = new ArrayList<>(resultMap.values());

            // Date For Today And Yesterday
            Date nowDate = new Date();
            String today = DateFormat.getDateInstance().format(nowDate);

            cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            String yesterday = new SimpleDateFormat( "YYYY-MM-dd").format(cal.getTime());
            // Push Into Request
            request.setAttribute("dateList", dateList);
            request.setAttribute("newsList", newsList);
            request.setAttribute("today", today);
            request.setAttribute("yesterday", yesterday);
        }
        request.getRequestDispatcher("/latestNews.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}