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
 * Servlet implementation class MyFriend
 */
@WebServlet("/MyFriend")
public class MyFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyFriend() {
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
			//u_id = "zhz123";
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		ArrayList<Dbuser> friendList = new ArrayList<Dbuser>();			
		int message = DAOFactory.getFollowDAO().getFriends(u_id, friendList);
		switch(message) {
		case FollowDAO.SUCCESS:
			System.out.println("id");
			request.setAttribute("friendList", friendList);
			request.getRequestDispatcher("/myFriend.jsp").forward(request, response);
			break;
		case FollowDAO.EXCEPTION:
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
		String del_fing_id = (String)request.getParameter("del_fing_id");
		if (del_fing_id == null)
		{
			System.out.println("mdzz");
			//request.getRequestDispatcher("/safe.jsp").forward(request, response);
		}
		else
		{
			HttpSession session = request.getSession(true);
			Dbuser user = (Dbuser)session.getAttribute("loginUser");
			if (user == null)
			{
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
			}
			String u_id  = user.getU_id();
			System.out.println(u_id + ' ' + del_fing_id);
			int message = DAOFactory.getFollowDAO().unfollow(u_id, del_fing_id);
			System.out.println(message);
//			switch(message) {
//			case FollowDAO.SUCCESS:
//				break;
//			case FollowDAO.FAILED:
//				System.out.println("failed");
//				break;
//			case FollowDAO.EXCEPTION:
//				System.out.println("exception");
//				break;
//			default:
//				break;
//			}
		}
		doGet(request, response);
	}

}
