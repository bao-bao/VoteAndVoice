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
import dao.UserInfoDAO;
import vo.Dbuser;

/**
 * Servlet implementation class Safe
 */
@WebServlet("/Safe")
public class Safe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Safe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Dbuser user = (Dbuser)session.getAttribute("loginUser");
		String u_id = null;
		if(user != null) u_id = user.getU_id(); 
		if (u_id == null)
		{
			u_id = "yyf123";
		}
		request.getRequestDispatcher("/safe.jsp").forward(request, response);
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
		String o_pwd = request.getParameter("o_pwd");
		String n_pwd = request.getParameter("n_pwd");
		String nc_pwd = request.getParameter("nc_pwd");
		System.out.println(o_pwd + ' ' + n_pwd + ' ' + nc_pwd);
		ArrayList<Dbuser> InfoUserList = new ArrayList<Dbuser>();			
		int message = DAOFactory.getUserInfoDAO().getUserInfo(u_id, InfoUserList);
		switch(message) {
		case UserInfoDAO.SUCCESS:
			Dbuser InfoUser = InfoUserList.get(0);
			if (o_pwd.equals(InfoUser.getU_pwd()) && n_pwd.equals(nc_pwd))
			{
				int message1 = DAOFactory.getChangePwdDAO().changePwd(u_id, o_pwd, n_pwd);
				switch(message1) {
				case ChangePwdDAO.SUCCESS:
					session.setAttribute("safeInfo", "success");
					System.out.println("success");
					break;
				case ChangePwdDAO.FAILED:
					session.setAttribute("safeInfo", "fail");
					System.out.println("fail");
					break;
				case ChangePwdDAO.EXCEPTION:
					session.setAttribute("safeInfo", "exception");
					System.out.println("exception");
					break;
				default:
					break;
				}
				break;
			}
		case UserInfoDAO.WRONG_ID:
			response.getWriter().append("there is no such id");
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
