package com.mediatheque.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediatheque.beans.Album;
import com.mediatheque.dao.*;

/**
 * Servlet implementation class GestionAlbum
 */
@WebServlet("/GestionAlbum")
public class GestionAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AlbumDao albumDao;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.albumDao = daoFactory.getAlbumDao();
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionAlbum() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
    	System.out.println("Action : " + action);
    	if (action != null && action.equals("delete")) {
    		Long id = Long.parseLong(request.getParameter("id"));
    		Boolean isDelete = this.albumDao.supprimer(id);
    		if (isDelete) {
				String msg = "Album supprimé avec succés !";
				request.setAttribute( "errorMessage", null);
				request.setAttribute( "successMessage", msg);
    		} else {
				String msg = "Problème survenu (Veuillez réessayer plus tard !)";
				request.setAttribute( "successMessage", null);
				request.setAttribute( "errorMessage", msg);
    		}

			request.setAttribute("albums", albumDao.lister());
			this.getServletContext().getRequestDispatcher( "/lister_album.jsp" ).forward( request, response );
    	}else if (action != null && action.equals("detail")) {
    		Long id = Long.parseLong(request.getParameter("id"));
    		Album album = albumDao.search(id);
    		request.setAttribute("album", album);
    		this.getServletContext().getRequestDispatcher( "/detail_album.jsp" ).forward( request, response );

    	}
    		
    	else if(action != null && action.equals("modifier")) {
			long id = Long.parseLong(request.getParameter("id"));
			Album album = this.albumDao.search(id);
			request.setAttribute("album", album);
			this.getServletContext().getRequestDispatcher( "/modifier_album.jsp" ).forward( request, response );
    	}
    	else {
    		request.setAttribute("albums", albumDao.lister());
    		this.getServletContext().getRequestDispatcher( "/lister_album.jsp" ).forward( request, response );
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));

		String date_s = request.getParameter("date_sortie");
	    
		Album album = this.albumDao.search(id);
		album.setDescription((String) request.getParameter("description"));
		album.setDateSortie(date_s);
				
		String prixAchatString = request.getParameter("prix_achat");
		double prixAchat = Double.parseDouble(prixAchatString);
		album.setPrixAchat(prixAchat);

		
		this.albumDao.modifier(album);
		
		
		request.setAttribute("albums", albumDao.lister());
		this.getServletContext().getRequestDispatcher( "/lister_album.jsp" ).forward( request, response );
	}

}
