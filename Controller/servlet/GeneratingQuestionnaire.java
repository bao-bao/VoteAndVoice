package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CreateQnDAO;
import dao.DAOFactory;
import net.sf.json.*;
import vo.Dbitem;
import vo.Dbquestion;
import vo.Dbquestionnaire;
import vo.ExDbitem;
import vo.ExDbquestion;
import vo.ExDbquestionnaire;

/**
 * Servlet implementation class questionnaire
 */
@WebServlet("/Genquestionnaire")
public class GeneratingQuestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneratingQuestionnaire() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
			request.setAttribute("previewInfo","û�о���֮ǰ�Ľ�������");
			request.getRequestDispatcher("previewQuestionnaire.jsp").forward(request, response);
			return;
		}
		ArrayList<ExDbquestion> exQuestionList = newExQn.get_transExQuestionList();
		Dbquestionnaire questionnaire = newExQn.getQuestionnaire();
		//�����ʾ���������
		questionnaire.set_transQn_title(title);
		questionnaire.set_transQn_des(des);
		
		SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		questionnaire.setQn_endtime(Timestamp.valueOf(df.format(new Date())));
		
		//����json�ַ���,�õ���ά����
		JSONObject jorder = JSONObject.fromObject(order);
		JSONArray  orderarr = jorder.getJSONArray("order");
		
		JSONObject jsingle = JSONObject.fromObject(single);
		JSONArray singlearr = jsingle.getJSONArray("single");
		
		JSONObject jmultiple = JSONObject.fromObject(multiple);
		JSONArray multiplearr = jmultiple.getJSONArray("multiple");
		
		JSONObject jqanda = JSONObject.fromObject(qanda);
		JSONArray qandaarr = jqanda.getJSONArray("qanda");
		
		//������������
		questionnaire.set_transQn_q_count(orderarr.size());
		//��װExDbquestionnaire
		//�����������
		int isingle = 0, imultiple = 0, iqanda = 0;
		//��װ�����ѡ���б�
		for(int i = 0; i < orderarr.size(); ++ i){
			//��װExDbquestion
			ExDbquestion exquestion = new ExDbquestion();
			Dbquestion question = exquestion.get_transQuestion();
			ArrayList<ExDbitem> exItemList = exquestion.get_transExItemList();
			
			String type = null;
			JSONArray arr_question = null;
			if(orderarr.getString(i).equals("single") == true){
				type = "sin";
				arr_question = singlearr.getJSONArray(isingle ++);
			}
			else if(orderarr.getString(i).equals("multiple") == true){
				type = "mul";
				arr_question = multiplearr.getJSONArray(imultiple ++);
			}
			else if(orderarr.getString(i).equals("qanda") == true){
				type="que";
				arr_question = qandaarr.getJSONArray(iqanda ++);
			}
			//������ʴ����û��ѡ��������
			if (arr_question != null) {
				question.setQ_stem(arr_question.getString(0));
				question.setQ_i_count(new BigDecimal(arr_question.size() - 1));
				question.setQ_type(type);
				// ���ѡ��
				for (int j = 1; j < arr_question.size(); ++j) {
					ExDbitem exItem = new ExDbitem();
					Dbitem item = exItem.get_transItem();
					item.setI_type(type);
					item.set_transI_des(arr_question.getString(j));
					exItemList.add(exItem);
				}
				if(type.equals("que") == true){
					ExDbitem exItem = new ExDbitem();
					Dbitem item = exItem.get_transItem();
					item.setI_type(type);
					item.set_transI_des("�ʴ���");
					exItemList.add(exItem);
				}
			}
			else{
				request.setAttribute("previewInfo", "��Ŀ����ȫΪ��");
				request.getRequestDispatcher("prviewQuestionnaire.jsp").forward(request, response);
				return;
			}
			//�����ʾ�
			exQuestionList.add(exquestion);
		}
		ArrayList<ExDbquestionnaire> exDbarr = new ArrayList<ExDbquestionnaire>();
		exDbarr.add(newExQn);
		int message = DAOFactory.getCreateQnDAO().createQnAll(exDbarr);
		switch(message){
		case CreateQnDAO.EXCEPTION: {
			request.setAttribute("previewInfo", "��̨����");
			System.out.println("��̨����");
			request.getRequestDispatcher("previewQuestionnaire.jsp").forward(request, response);
			return;
		}
		case CreateQnDAO.FAILED:{
			request.setAttribute("previewInfo", "����ʧ��,�ʾ����ϱ�ƽ̨�淶");
			System.out.println("����ʧ��,�ʾ����ϱ�ƽ̨�淶");
			request.getRequestDispatcher("previewQuestionnaire.jsp").forward(request, response);
			return;
		}
		case CreateQnDAO.SUCCESS:{
			request.setAttribute("questionnaireInfo", "�����ɹ�");
			System.out.println( "�����ɹ�");
			request.setAttribute("title", title);
			request.setAttribute("des", des);
			request.setAttribute("order", order);
			request.setAttribute("single", single);
			request.setAttribute("multiple", multiple);
			request.setAttribute("qanda", qanda);
			request.getRequestDispatcher("questionnaire.jsp").forward(request, response);
			return;
		}
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
