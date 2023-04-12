package com.mediatheque.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediatheque.beans.Album;
import com.mediatheque.beans.Artiste;
import com.mediatheque.dao.*;
import com.mediatheque.forms.CreationAlbumForm;
import com.mediatheque.forms.CreationArtisteForm;


/**
 * Servlet implementation class CreationAlbum
 */
@WebServlet("/CreationAlbum")
public class CreationAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AlbumDao albumDao;
    
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
        this.albumDao = daoFactory.getAlbumDao();
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationAlbum() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher("/ajout_album.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("début servlet");
		CreationAlbumForm form = new CreationAlbumForm(this.albumDao);
		form.creerAlbum(request);
		request.setAttribute("albums", this.albumDao.lister());
        this.getServletContext().getRequestDispatcher("/lister_album.jsp").forward(request, response);
	}

}
