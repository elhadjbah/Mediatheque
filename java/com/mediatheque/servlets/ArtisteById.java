package com.mediatheque.servlets;
import com.mediatheque.dao.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediatheque.beans.Album;
import com.mediatheque.beans.Artiste;

/**
 * Servlet implementation class ArtisteById
 */
@WebServlet("/ArtisteById")
public class ArtisteById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AlbumDao albumDao;
	private ArtisteDao artisteDao;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.albumDao = daoFactory.getAlbumDao();
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtisteById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
        
        // Récupérer l'artiste correspondant à l'id depuis la base de données
        Artiste artiste = artisteDao.search(id);
        
        // Récupérer la liste des albums de l'artiste depuis la base de données
        List<Album> albums = albumDao.getAlbumsByArtiste(id);
        
        // Ajouter l'artiste et la liste d'albums à la requête
        request.setAttribute("artiste", artiste);
        request.setAttribute("albums", albums);
        
        // Afficher la page d'artiste

        this.getServletContext().getRequestDispatcher("/artiste.jsp.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
