package com.mediatheque.dao;

import static com.mediatheque.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.mediatheque.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mediatheque.beans.*;

public class AlbumDaoImp implements AlbumDao{
	private DAOFactory          daoFactory;

	public AlbumDaoImp( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	private static final String SQL_SELECT       = "SELECT id, description, date_sortie, prix_achat from Album ORDER BY id desc limit 5";
	private static final String SQL_SELECT_BY_ID = "SELECT id, description, date_sortie, prix_achat from Album WHERE id = ?";
	private static final String SQL_INSERT       = "INSERT INTO Album (description, date_sortie, prix_achat) VALUES (?, ?, ?)";
	private static final String SQL_UPDATE       = "UPDATE Album SET description = ?, date_sortie = ?,prix_achat = ? WHERE id = ?";
	private static final String SQL_DELETE       = "DELETE FROM Album WHERE id = ? ";

	
	

    
    public void creer( Album album ) throws DAOException {
    	
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
                    album.getDescription(),
                    album.getDateSortie(),
                    album.getPrixAchat()
                    );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du client, aucune ligne ajoutée dans la table." );
            }
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        }
    	
    }
    
    
    public List<Album> lister() {
        List<Album> albums = new ArrayList<Album>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
            resultat = preparedStatement.executeQuery();

            while (resultat.next()) {

                albums.add(map(resultat));
            }
        } catch (SQLException e) {
        	throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultat, preparedStatement, connexion );
        }
        return albums;
    }
    
    private static Album map( ResultSet resultSet ) throws SQLException {
    	Album album = new Album(
    									resultSet.getLong( "id" ),
										resultSet.getString( "description" ),
										resultSet.getString( "date_sortie" ),
										resultSet.getDouble( "prix_achat" )
						);
        return album;
    }
    
    
    public void modifier(Album album) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion=DAOFactory.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (album.getId() == null) {
                throw new DAOException("Artiste id is null, cannot update.");
            }

            preparedStatement = connexion.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1,album.getDescription());
            preparedStatement.setString(2, album.getDateSortie());
            preparedStatement.setDouble(3,album.getPrixAchat());
            preparedStatement.setLong(4, album.getId());
            int rowsUpdated = preparedStatement.executeUpdate();

            System.out.println(rowsUpdated + " row(s) updated.");
        } catch (SQLException e) {
            throw new DAOException("Error updating album with ID: " + album.getId(), e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	
	public Album search(Long id) {
		
		try ( Connection connection = daoFactory.getConnection() ) {
			
			try ( PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID) ) {
				statement.setLong(1, id);
				ArrayList<Album> artistes = new ArrayList<Album>();
				try ( ResultSet resultSet = statement.executeQuery() ) {
					while (resultSet.next()) {
						Album history = new Album(
								resultSet.getLong( "id" ),
								resultSet.getString( "description" ),
								resultSet.getString( "date_sortie" ),
								resultSet.getDouble( "prix_achat" )
						);

						artistes.add(history);
					}
				}
				return artistes.get(0);
			}

 		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	
	public Boolean supprimer(long id) {
		
		try ( Connection connection = daoFactory.getConnection() ) {
			try ( PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE) ) {
				preparedStatement.setLong(1, id);
				preparedStatement.execute();
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/*
	public List<Album> getAlbumsByArtiste(int idArtiste) throws SQLException {
        List<Album> albums = new ArrayList<>();
        try (Connection connection = daoFactory.getConnection()) {
        	PreparedStatement stmt = connection.prepareStatement("SELECT a.* FROM Album a JOIN AlbumArtiste aa ON a.id = aa.id_album WHERE aa.id_artiste = ?");
            stmt.setInt(1, idArtiste);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String description = rs.getString("description");
                    String dateSortie = rs.getString("date_sortie");
                    double prixAchat = rs.getDouble("prix_achat");
                    albums.add(new Album(id, description, dateSortie, prixAchat));
                }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return albums;
	}*/
}