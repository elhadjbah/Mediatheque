package com.mediatheque.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mediatheque.beans.Artiste;
import com.mediatheque.dao.ArtisteDao;
import com.mediatheque.dao.DAOException;
import com.mediatheque.dao.DAOFactory;

/**
 * Servlet implementation class ModifierArtiste
 */
@WebServlet("/ModifierArtiste")

public class ModifierArtiste extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ArtisteDao artisteDao;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.artisteDao = daoFactory.getArtisteDao();
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//request.setAttribute("artites", artisteDao.lister());
		//this.getServletContext().getRequestDispatcher( "/WEB-INF/modifier_artiste.jsp" ).forward( request, response );
    	
    	String action = request.getParameter("action");
    	System.out.println("Action : " + action);
    	if (action != null && action.equals("delete")) {
    		Long id = Long.parseLong(request.getParameter("id"));
    		Boolean isDelete = this.artisteDao.supprimer(id);
    		if (isDelete) {
				String msg = "Artiste a été supprimé !";
				request.setAttribute( "errorMessage", null);
				request.setAttribute( "successMessage", msg);
    			request.setAttribute("artites", artisteDao.lister());
    			this.getServletContext().getRequestDispatcher( "/lister_artiste.jsp" ).forward( request, response );
    		} else {
				String msg = "Problème survenu (Veuillez réessayer plus tard !)";
				request.setAttribute( "successMessage", null);
				request.setAttribute( "errorMessage", msg);
    			request.setAttribute("artites", artisteDao.lister());
    			this.getServletContext().getRequestDispatcher( "/lister_artiste.jsp" ).forward( request, response );
    		}
    	} else {
			long id = Long.parseLong(request.getParameter("id"));
			Artiste artiste = this.artisteDao.search(id);
			request.setAttribute("artiste", artiste);
			this.getServletContext().getRequestDispatcher( "/modifier_artiste.jsp" ).forward( request, response );
    	}
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
		Artiste artiste = this.artisteDao.search(id);
		
		artiste.setNom((String) request.getParameter("nom"));
		artiste.setNationalite((String) request.getParameter("nationalite"));
		
		this.artisteDao.modifier(artiste);
		System.out.println("Id : " + id + "\n" + "Artiste : " + request.getParameter("nom") + ", Nationalité : " + artiste.getNationalite());
		
		request.setAttribute("artites", artisteDao.lister());
		this.getServletContext().getRequestDispatcher( "/lister_artiste.jsp" ).forward( request, response );
    }
}