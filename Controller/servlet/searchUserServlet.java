package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChangePwdDAO;
import dao.DAOFactory;
import dao.FollowDAO;
import dao.UserInfoDAO;
import vo.Dbuser;

/**
 * Servlet implementation class searchUserServlet
 */
@WebServlet("/searchUser")
public class searchUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchUserServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        System.out.println(name);
        ArrayList<Dbuser> UserList = new ArrayList<Dbuser>();
        int message = DAOFactory.getUserInfoDAO().getUserInfoByName(name, UserList, 30);
        switch (message) {
            case UserInfoDAO.EXCEPTION:
                response.getWriter().append("MYSQL fault.");
                return;
            case UserInfoDAO.SUCCESS:
                request.setAttribute("nameList", UserList);
                System.out.println("success" + UserList.size());
                break;
            default:
                break;
        }
        request.getRequestDispatcher("/searchUser.jsp").forward(request, response);

}

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
