package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.LoginDAO;
import vo.Dbuser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("login_name");
		String user_pw = request.getParameter("login_pw");
		
		String rejson = null;
		
		if(user_id != null && user_pw != null){
			ArrayList<Dbuser> loginUserList = new ArrayList<Dbuser>();
			int message = DAOFactory.getLoginDAO().login(user_id, user_pw, loginUserList);
			switch(message){
			case LoginDAO.SUCCESS:
				HttpSession session = request.getSession(true);
				Dbuser loginUser = loginUserList.get(0);
				session.setAttribute("loginUser", loginUser);
				rejson = "{'errorMessage':'*µÇÂ½³É¹¦'}";
				break;
			case LoginDAO.WRONG_ID:
				rejson = "{'errorMessage':'*ÓÃ»§id´íÎó'}";
				break;
			case LoginDAO.WRONG_PWD:
				rejson = "{'errorMessage':'*ÃÜÂë´íÎó'}";
				break;
			case LoginDAO.EXCEPTION:
				rejson = "{'errorMessage':'*ºóÌ¨¹ÊÕÏ'}";
				break;
				default: break;
			}
			
		}
		else{
			rejson = "{'errorMessage':'*¿Õ×Ö¶Î£¬ÇëÌîÐ´'}";
		}
		System.out.println(rejson);
		//Ð´»ØJSON
		PrintWriter pw = response.getWriter();
		pw.print(rejson);
		pw.flush();
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
