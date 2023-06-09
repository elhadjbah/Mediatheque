package com.mediatheque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mediatheque.beans.Album;
import com.mediatheque.beans.Artiste;
import static com.mediatheque.dao.DAOUtilitaire.*;



public class ArtisteDaoImp implements ArtisteDao {
	
	
	private DAOFactory          daoFactory;

	public ArtisteDaoImp( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	private static final String SQL_SELECT        = "SELECT id, nom, nationalite FROM Artiste ORDER BY id desc";
	private static final String SQL_SELECT_BY_ID = "SELECT id, nom,nationalite FROM Artiste WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO Artiste (nom, nationalite) VALUES (?, ?)";
	private static final String SQL_UPDATE = "UPDATE Artiste SET nom = ?, nationalite = ? WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM Artiste WHERE id = ? ";

	
	
    @Override
    public void creer( Artiste artiste ) throws DAOException {
    	
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true,
                    artiste.getNom(),
                    artiste.getNationalite());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du client, aucune ligne ajoutée dans la table." );
            }
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        }
    	
    }
    
    @Override
    public List<Artiste> lister() {
        List<Artiste> artistes = new ArrayList<Artiste>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
            resultat = preparedStatement.executeQuery();

            while (resultat.next()) {

                artistes.add(map(resultat));
            }
        } catch (SQLException e) {
        	throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultat, preparedStatement, connexion );
        }
        return artistes;
    }
    
    private static Artiste map( ResultSet resultSet ) throws SQLException {
    	Artiste artiste = new Artiste(
    									resultSet.getLong( "id" ),
										resultSet.getString( "nom" ),
										resultSet.getString( "nationalite" )
						);
        return artiste;
    }
    
    @Override
    public void modifier(Artiste artiste) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion=DAOFactory.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (artiste.getId() == null) {
                throw new DAOException("Artiste id is null, cannot update.");
            }

            preparedStatement = connexion.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1,artiste.getNom());
            preparedStatement.setString(2,artiste.getNationalite());
            preparedStatement.setLong(3, artiste.getId());
            int rowsUpdated = preparedStatement.executeUpdate();

            System.out.println(rowsUpdated + " row(s) updated.");
        } catch (SQLException e) {
            throw new DAOException("Error updating artist with ID: " + artiste.getId(), e);
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

	@Override
	public Artiste search(Long id) {
		
		try ( Connection connection = daoFactory.getConnection() ) {
		
			
			try ( PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID) ) {
				statement.setLong(1, id);
				ArrayList<Artiste> artistes = new ArrayList<Artiste>();
				try ( ResultSet resultSet = statement.executeQuery() ) {
					while (resultSet.next()) {
						Artiste history = new Artiste(
								resultSet.getLong( "id" ),
								resultSet.getString( "nom" ), 
								resultSet.getString( "nationalite" )
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
	
	public List<Album> getAlbumsByArtiste(int idArtiste) {
        List<Album> albums = new ArrayList<>();
        
		try ( Connection connection = daoFactory.getConnection() ) {
			PreparedStatement stmt = connection.prepareStatement("SELECT a.* FROM Album a JOIN AlbumArtiste aa ON a.id = aa.id_album WHERE aa.id_artiste = ?");
	        stmt.setInt(1, idArtiste);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Long id = (long) rs.getInt("id");
	                String description = rs.getString("description");
	                String dateSortie = rs.getString("date_sortie");
	                Double prixAchat = rs.getDouble("prix_achat");
	                albums.add(new Album(id, description, dateSortie, prixAchat));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return albums;
	}
}
