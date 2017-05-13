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
					rejson = "{'errorMessage':'*登陆成功'}";
					break;
				case LoginDAO.WRONG_ID:
					rejson = "{'errorMessage':'*用户id错误'}";
					break;
				case LoginDAO.WRONG_PWD:
					rejson = "{'errorMessage':'*密码错误'}";
					break;
				case LoginDAO.EXCEPTION:
					rejson = "{'errorMessage':'*后台故障'}";
					break;
				default: break;
			}

		}
		else{
			rejson = "{'errorMessage':'*空字段，请填写'}";
		}
		System.out.println(rejson);
		//写回JSON
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
