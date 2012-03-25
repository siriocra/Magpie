package connect;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/logex"})
public class VKConnection extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response){
		String hello = "Hello, world!";
		request.setAttribute("tryHello", hello);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/login.jsp");
        try {
			rd.forward(request, response);
		} catch (ServletException e) {
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("ServletException");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e) {
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("IOException");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
}
