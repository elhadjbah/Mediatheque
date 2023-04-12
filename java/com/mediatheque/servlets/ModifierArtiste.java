package com.mediatheque.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mediatheque.beans.Artiste;
import com.mediatheque.dao.AlbumDao;
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
    private AlbumDao albumDao;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.artisteDao = daoFactory.getArtisteDao();
        this.albumDao = daoFactory.getAlbumDao();
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String action = request.getParameter("action");
    	System.out.println("Action : " + action);
    	if (action != null && action.equals("delete")) {
    		Long id = Long.parseLong(request.getParameter("id"));
    		Boolean isDelete = this.artisteDao.supprimer(id);
    		if (isDelete) {
				String msg = "Artiste supprimé avec succés !";
				request.setAttribute( "errorMessage", null);
				request.setAttribute( "successMessage", msg);
    			
    		} else {
				String msg = "Problème survenu (Veuillez réessayer plus tard !)";
				request.setAttribute( "successMessage", null);
				request.setAttribute( "errorMessage", msg);
    		}
    		
    		request.setAttribute("albums", this.albumDao.lister());
            this.getServletContext().getRequestDispatcher("/lister_album.jsp").forward(request, response);
    	}else if (action != null && action.equals("detail")) {
    		Long id = Long.parseLong(request.getParameter("id"));
    		Artiste artiste = artisteDao.search(id);
    		request.setAttribute("artiste", artiste);
    		this.getServletContext().getRequestDispatcher( "/detail_artiste.jsp" ).forward( request, response );

    	}
    		
    	else if(action != null && action.equals("modifier")) {
			long id = Long.parseLong(request.getParameter("id"));
			Artiste artiste = this.artisteDao.search(id);
			request.setAttribute("artiste", artiste);
			this.getServletContext().getRequestDispatcher( "/modifier_artiste.jsp" ).forward( request, response );
    	}
    	else {
    		request.setAttribute("artites", artisteDao.lister());
    		this.getServletContext().getRequestDispatcher( "/lister_artiste.jsp" ).forward( request, response );
    	}
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
		Artiste artiste = this.artisteDao.search(id);
		
		artiste.setNom((String) request.getParameter("nom"));
		artiste.setNationalite((String) request.getParameter("nationalite"));
		
		this.artisteDao.modifier(artiste);
		
		request.setAttribute("albums", this.albumDao.lister());
        this.getServletContext().getRequestDispatcher("/lister_album.jsp").forward(request, response);
    }
}