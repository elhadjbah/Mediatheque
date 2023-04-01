package com.mediatheque.dao;

import com.mediatheque.beans.Album;

public interface AlbumDao {
	void creer( Album utilisateur ) throws DAOException;

	Album trouver( String email ) throws DAOException;
}
