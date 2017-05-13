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
 * Servlet implementation class BasicInfo
 */
@WebServlet("/BasicInfo")
public class BasicInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasicInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String u_id = request.getParameter("u_id");
		HttpSession session = request.getSession(true);
		Dbuser user = (Dbuser)session.getAttribute("loginUser");
		String u_id = null;
		if(user != null) u_id = user.getU_id(); 
			
		if (u_id == null)
		{
			u_id = "yyf123";
		}
		
		System.out.println(u_id);
		
		ArrayList<Dbuser> InfoUserList = new ArrayList<Dbuser>();			
		int message = DAOFactory.getUserInfoDAO().getUserInfo(u_id, InfoUserList);
		switch(message) {
		case UserInfoDAO.SUCCESS:
			System.out.println("id");
			Dbuser InfoUser = InfoUserList.get(0);
			request.setAttribute("InfoUser", InfoUser);
			request.getRequestDispatcher("/basicinfo.jsp").forward(request, response);
			break;
		case UserInfoDAO.WRONG_ID:
			response.getWriter().append("somthing wrong with our website");
			break;
		case UserInfoDAO.EXCEPTION:
			response.getWriter().append("somthing wrong with our website");
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Dbuser user = (Dbuser)session.getAttribute("loginUser");
		String u_id = null;
		if(user != null) u_id = user.getU_id(); 
		if (u_id == null)
		{
			u_id = "yyf123";
		}
		ArrayList<Dbuser> ChangeUserList = new ArrayList<Dbuser>();			
		int message = DAOFactory.getUserInfoDAO().getUserInfo(u_id, ChangeUserList);
		switch(message) {
		case UserInfoDAO.SUCCESS:
			Dbuser ChangeUser = ChangeUserList.get(0);
			String user_name = request.getParameter("user_name");
			user_name = new String(user_name.getBytes("ISO-8859-1"), "utf-8");
			String user_sex = request.getParameter("user_sex");
			user_sex = new String(user_sex.getBytes("ISO-8859-1"), "utf-8");
			String user_tel = request.getParameter("user_tel");
			user_tel = new String(user_tel.getBytes("ISO-8859-1"), "utf-8");
			String user_nation = request.getParameter("user_nation");
			user_nation = new String(user_nation.getBytes("ISO-8859-1"), "utf-8");
			String user_province = request.getParameter("user_province");
			user_province = new String(user_province.getBytes("ISO-8859-1"), "utf-8");
			String user_city = request.getParameter("user_city");
			user_city = new String(user_city.getBytes("ISO-8859-1"), "utf-8");
			String user_street = request.getParameter("user_street");
			user_street = new String(user_street.getBytes("ISO-8859-1"), "utf-8");
			System.out.println(ChangeUser.get_transU_id() + user_name + user_sex + user_tel + user_nation + user_province + user_city + user_street);
			ChangeUser.set_transU_name(user_name);
			ChangeUser.set_transU_gender(user_sex);
			ChangeUser.set_transU_phone(user_tel);
			ChangeUser.set_transU_ad_country(user_nation);
			ChangeUser.set_transU_ad_province(user_province);
			ChangeUser.set_transU_ad_city(user_city);
			ChangeUser.set_transU_ad_street(user_street);
			int message1 = DAOFactory.getUserInfoDAO().changeUserInfo(ChangeUser);
			switch(message1) {
				case UserInfoDAO.SUCCESS:
					System.out.println("mdaky");
					break;
				case UserInfoDAO.WRONG_ID:
					System.out.println("mdak");
					break;
				case UserInfoDAO.EXCEPTION:
					System.out.println("mda");
					break;
				default:
					break;
			}
			break;
		case UserInfoDAO.WRONG_ID:
			response.getWriter().append("somthing wrong with our website");
			break;
		case UserInfoDAO.EXCEPTION:
			response.getWriter().append("somthing wrong with our website");
			break;
		default:
			break;
		}
		doGet(request, response);
	}

}
