package edu.upc.escert.curs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import edu.upc.escert.curs.repositori.RepositoriFactory;
import edu.upc.escert.curs.repositori.IRepositoriComentaris;

@WebServlet("/comentar")
public class ComentarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IRepositoriComentaris repositoriComentaris=RepositoriFactory.getRepositoriComentaris();
	public static PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("username",request.getRemoteUser());
		request.getRequestDispatcher("/comentar.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comentari=request.getParameter("comentari");
		String autor=request.getRemoteUser();
		String titol=request.getParameter("titol");
		
		String comentariSafeHTML = policy.sanitize(comentari);
		
		if (request.getRemoteUser().equals(autor)) {
			repositoriComentaris.afegirComentari(new Comentari(autor,titol,comentariSafeHTML));
			response.sendRedirect("comentaris");
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
		
	}

}
