package edu.upc.escert.curs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.upc.escert.curs.repositori.RepositoriComentaris;
import edu.upc.escert.curs.repositori.jdbc.RepositoriComentarisJDBC;

@WebServlet("/esborrar")
public class EsborrarComentariServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RepositoriComentaris repositoriComentaris=RepositoriComentarisJDBC.getInstance();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		repositoriComentaris.esborrarComentari(Integer.parseInt(id));
		response.sendRedirect("comentaris");
	}

}
