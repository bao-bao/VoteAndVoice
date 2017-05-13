package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.RegisterDAO;
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String user_name =  request.getParameter("register_name");
		String user_pw = request.getParameter("register_pw");
		String user_pw_confirm = request.getParameter("register_pw_confirm");
		
		String rejson  = null;
		
		int message = DAOFactory.getRegisterDAO().register(user_name, user_pw, null, null, null, null, null, null, null);
		switch(message){
		case RegisterDAO.SUCCESS:
			rejson = "{'errorMessage':'注册成功'}";
			break;
		case RegisterDAO.EXIST:
			rejson = "{'errorMessage': '该用户id已经被抢注'}";
			break;
		case RegisterDAO.EXCEPTION:
			rejson = "{'errorMessage': '后台故障'}";
			break;
		}
		System.out.println(rejson);
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
