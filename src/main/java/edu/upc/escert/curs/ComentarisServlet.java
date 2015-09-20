package edu.upc.escert.curs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.upc.escert.curs.repositori.jdbctemplate.RepositoriComentarisJDBCTemplate;

/**
 * Servlet implementation class Llistar
 */
@WebServlet("/comentaris")
public class ComentarisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RepositoriComentarisJDBCTemplate repositoriComentaris=RepositoriComentarisJDBCTemplate.getInstance();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Comentari> comentaris=null;
		String autor=request.getParameter("autor");
		if (autor!=null) {
			comentaris=repositoriComentaris.getComentarisPerAutor(autor);
		} else {
			comentaris=repositoriComentaris.getComentaris();
		}
		request.setAttribute("comentaris",comentaris);
		request.getRequestDispatcher("/comentaris.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comentari=request.getParameter("comentari");
		String autor=request.getParameter("autor");
		String titol=request.getParameter("titol");
		repositoriComentaris.afegirComentari(new Comentari(autor,titol,comentari));
		response.sendRedirect("comentaris");
	}

}
