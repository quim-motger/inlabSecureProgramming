package edu.upc.escert.curs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.upc.escert.curs.repositori.IRepositoriComentaris;
import edu.upc.escert.curs.repositori.RepositoriFactory;

@WebServlet("/esborrar")
public class EsborrarComentariServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IRepositoriComentaris repositoriComentaris=RepositoriFactory.getRepositoriComentaris();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		if (request.getSession().getAttribute("username").equals(repositoriComentaris.getComentariPerId(Integer.parseInt(id)).getAutor())) {
			repositoriComentaris.esborrarComentari(Integer.parseInt(id));
			response.sendRedirect("comentaris");
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
		
	}

}
