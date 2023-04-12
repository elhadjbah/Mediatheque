package com.mediatheque.dao;

import java.util.List;

import com.mediatheque.beans.Album;


public interface AlbumDao {
	
	
	public void creer( Album album ) throws DAOException;
	
    public List<Album> lister();
    
    public void modifier(Album album) throws DAOException;

	public Album search(Long id);
	
	public Boolean supprimer(long id);
	
	//public List<Album> getAlbumsByArtiste(int idArtiste);
}
