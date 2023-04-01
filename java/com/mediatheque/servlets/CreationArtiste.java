package com.mediatheque.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mediatheque.dao.*;
import com.mediatheque.forms.CreationArtisteForm;
/**
 * Servlet implementation class CreationArtiste
 */

@WebServlet("/CreationArtiste")
public class CreationArtiste extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArtisteDao artisteDao;
       
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
        this.artisteDao = daoFactory.getArtisteDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationArtiste() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher("/ajout_artiste.jsp").forward(request, response);
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("début servlet");
		CreationArtisteForm form = new CreationArtisteForm(artisteDao);
		form.creerArtiste(request);
		request.setAttribute("form", form);
        this.getServletContext().getRequestDispatcher("/ListerArtiste").forward(request, response);
        
	}

}
