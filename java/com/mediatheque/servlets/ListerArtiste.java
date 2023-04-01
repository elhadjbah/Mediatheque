package com.mediatheque.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediatheque.dao.ArtisteDao;
import com.mediatheque.dao.DAOFactory;

/**
 * Servlet implementation class ListerArtiste
 */
@WebServlet("/ListerArtiste")
public class ListerArtiste extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArtisteDao artisteDao;
       
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
        this.artisteDao = daoFactory.getArtisteDao();
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListerArtiste() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setAttribute("artites", artisteDao.lister());
		this.getServletContext().getRequestDispatcher( "/lister_artiste.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
