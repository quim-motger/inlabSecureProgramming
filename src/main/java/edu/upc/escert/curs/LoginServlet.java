package edu.upc.escert.curs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.upc.escert.curs.repositori.RepositoriUsuaris;
import edu.upc.escert.curs.repositori.jdbc.RepositoriUsuarisJDBC;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	RepositoriUsuaris usuaris=RepositoriUsuarisJDBC.getInstance();

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username=request.getParameter("username");
		String password=request.getParameter("password");

		if (usuaris.autenticar(username,password)) {
			request.getSession().setAttribute("username", username);
			response.sendRedirect("comentaris");
		} else {
			request.setAttribute("login_error",1);
			doGet(request,response);
		}
	}

}
