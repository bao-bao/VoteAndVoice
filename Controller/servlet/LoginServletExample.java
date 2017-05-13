package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.*;
import vo.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServletExample extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServletExample() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String u_id = request.getParameter("u_id");
		String u_pwd = request.getParameter("u_pwd");
		if(u_id!=null && u_pwd!=null) {
			System.out.println("There are parameters.");
			ArrayList<Dbuser> loginUserList = new ArrayList<Dbuser>();
			int message = DAOFactory.getLoginDAO().login(u_id, u_pwd, loginUserList);
			switch(message) {
			case LoginDAO.SUCCESS:
				HttpSession session = request.getSession(true);
				Dbuser loginUser = loginUserList.get(0);
				session.setAttribute("loginuser", loginUser);
				response.getWriter().append("login success: " + ((Dbuser) session.getAttribute("loginuser")).getU_id());
				break;
			case LoginDAO.WRONG_ID:
				response.getWriter().append("there is no such id");
				break;
			case LoginDAO.WRONG_PWD:
				response.getWriter().append("password is wrong");
				break;
			case LoginDAO.EXCEPTION:
				response.getWriter().append("somthing wrong with our website");
				break;
			default:
				break;
			}
		}
		else {
			System.out.println("There is no parameter.");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
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
