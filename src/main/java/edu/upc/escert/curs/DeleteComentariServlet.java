package edu.upc.escert.curs;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeleteComentariServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RepositoriComentaris repositoriComentaris=RepositoriComentaris.getInstance();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String id=request.getParameter("id");		
		repositoriComentaris.esborrarComentari(Integer.parseInt(id));
		response.sendRedirect("comentaris");
	}

}
