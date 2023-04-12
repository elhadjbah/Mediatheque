package com.mediatheque.dao;
import java.util.List;

import com.mediatheque.beans.Artiste;

public interface ArtisteDao {
	
	void creer( Artiste artiste ) throws DAOException;
	
    List<Artiste> lister();
    
    public void modifier(Artiste artiste) throws DAOException;

	public Artiste search(Long id);
	
	public Boolean supprimer(long id);
}
