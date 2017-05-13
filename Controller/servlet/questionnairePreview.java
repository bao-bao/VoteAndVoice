package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class questionnairePreview
 */
@WebServlet("/questionnairePreview")
public class questionnairePreview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public questionnairePreview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String des = request.getParameter("des");
		String  order = request.getParameter("order");
		String single = request.getParameter("single");
		String multiple = request.getParameter("multiple");
		String qanda = request.getParameter("qanda");
		
		request.setAttribute("title", title);
		request.setAttribute("des", des);
		request.setAttribute("order", order);
		request.setAttribute("single", single);
		request.setAttribute("multiple", multiple);
		request.setAttribute("qanda", qanda);
		
		System.out.println("It is over");
		request.getRequestDispatcher("previewQuestionnaire.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
