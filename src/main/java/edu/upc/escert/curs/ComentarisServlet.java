package edu.upc.escert.curs;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Llistar
 */
@WebServlet("/comentaris")
public class ComentarisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RepositoriComentaris repositoriComentaris=RepositoriComentaris.getInstance();


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
		Comentari c=new Comentari();
		c.setComentari(request.getParameter("comentari"));
		c.setData(new Date());
		c.setAutor(request.getParameter("autor"));
		repositoriComentaris.afegirComentari(c);
		response.sendRedirect("comentaris");
	}

}
