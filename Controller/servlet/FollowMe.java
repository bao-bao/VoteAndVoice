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
 * Servlet implementation class FollowMe
 */
@WebServlet("/FollowMe")
public class FollowMe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowMe() {
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
		ArrayList<Dbuser> followedList = new ArrayList<Dbuser>();			
		int message = DAOFactory.getFollowDAO().getFollowingUser(u_id, followedList);
		switch(message) {
		case FollowDAO.SUCCESS:
			System.out.println("id");
			request.setAttribute("followedList", followedList);
			request.getRequestDispatcher("/followMe.jsp").forward(request, response);
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
		String add_fed_id = (String)request.getParameter("add_fed_id");
		if (add_fed_id == null)
		{
			System.out.println("mdzz");
			//request.getRequestDispatcher("/safe.jsp").forward(request, response);
		}
		else
		{
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
			System.out.println(u_id + ' ' + add_fed_id);
			int message = DAOFactory.getFollowDAO().follow(u_id, add_fed_id);
			switch(message) {
			case FollowDAO.SUCCESS:
				break;
			case FollowDAO.FAILED:
				System.out.println("failed");
				break;
			case FollowDAO.EXCEPTION:
				System.out.println("exception");
				break;
			default:
				break;
			}
		}
		doGet(request, response);
	}

}
