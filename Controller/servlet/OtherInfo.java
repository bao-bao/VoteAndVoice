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
import dao.FollowDAO;
import dao.UserInfoDAO;
import vo.Dbuser;

/**
 * Servlet implementation class OtherInfo
 */
@WebServlet("/OtherInfo")
public class OtherInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String o_u_id = (String)request.getParameter("o_u_id");
		if (o_u_id == null)
		{
			o_u_id = "zhz123";
		}
		ArrayList<Dbuser> OhterUserList = new ArrayList<Dbuser>();			
		int message = DAOFactory.getUserInfoDAO().getUserInfo(o_u_id, OhterUserList);
		switch(message) {
		case UserInfoDAO.SUCCESS:
			System.out.println("id");
			Dbuser OtherUser = OhterUserList.get(0);
			request.setAttribute("OtherUser", OtherUser);
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
		
		String u_id = (String)session.getAttribute("u_id");
		if (u_id == null)
		{
			u_id = "yyf123";
		}
		ArrayList<Dbuser> followingList = new ArrayList<Dbuser>();
		int message1 = DAOFactory.getFollowDAO().getFollowedUser(u_id, followingList);
		switch(message1) {
		case FollowDAO.SUCCESS:
			System.out.println("id");
			int ifFollow = 0;
			for (int i = 0; i < followingList.size(); ++i)
			{
				if (followingList.get(i).get_transU_id().equals(o_u_id))
				{
					ifFollow = 1;
				}
			}
			request.setAttribute("ifFollow", ifFollow);
			request.getRequestDispatcher("/otherInfo.jsp").forward(request, response);
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
		HttpSession session = request.getSession(true);
		String u_id = (String)session.getAttribute("u_id");
		if (u_id == null)
		{
			u_id = "yyf123";
		}
		String followway_u_id = (String)request.getParameter("followway_u_id");
		DAOFactory.getFollowDAO().unfollow(u_id, followway_u_id);
		if (followway_u_id == null)
		{
			System.out.println("mdzz");
			request.getRequestDispatcher("/safe.jsp").forward(request, response);
		}
		else
		{
			String followway = (String)request.getParameter("followway");
			int message = 0;
			if (followway.equals("off"))
			{
				message = DAOFactory.getFollowDAO().unfollow(u_id, followway_u_id);
			}
			else
			{
				message = DAOFactory.getFollowDAO().follow(u_id, followway_u_id);
			}
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
			doGet(request, response);
		}
	}

}
