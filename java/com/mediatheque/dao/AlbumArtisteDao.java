package com.mediatheque.dao;

import com.mediatheque.beans.AlbumArtiste;

public interface AlbumArtisteDao {
	
	void creer( AlbumArtiste utilisateur ) throws DAOException;

	AlbumArtiste trouver( String email ) throws DAOException;

}
