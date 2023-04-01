package com.mediatheque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mediatheque.beans.Artiste;
import static com.mediatheque.dao.DAOUtilitaire.*;



public class ArtisteDaoImp implements ArtisteDao {
	
	
	private DAOFactory          daoFactory;

	public ArtisteDaoImp( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	
	private static final String SQL_SELECT        = "SELECT id, nom, nationalite FROM Artiste ORDER BY id desc";
	//private static final String SQL_SELECT_PAR_ID = "SELECT id, nom,nationalite FROM Artiste WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO Artiste (nom, nationalite) VALUES (?, ?)";

	
	/*
	 * Initialise la requête préparée basée sur la connexion passée en argument,
	 * avec la requête SQL et les objets donnés.
	 */
	public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
	}
	
	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des artistes (un
	 * ResultSet) et un bean Utilisateur.
	 */
	
	
	
	/* Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao */
	/*
    @Override
    public Artiste trouver( Long id ) throws DAOException {
    	
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Artiste artiste = null;

        try {
            //Récupération d'une connexion depuis la Factory 
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            // Parcours de la ligne de données de l'éventuel ResulSet retourné 
            if ( resultSet.next() ) {
            	artiste = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return artiste;
    
    }
    */

    /* Implémentation de la méthode creer() définie dans l'interface UtilisateurDao */
    @Override
    public void creer( Artiste artiste ) throws DAOException {
    	
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        //ResultSet valeursAutoGenerees = null;

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
//            Long id = artiste.getId();
//            System.out.println(id + " Cette id");
            if (artiste.getId() == null) {
                throw new DAOException("Artiste id is null, cannot update.");
            }

            preparedStatement = connexion.prepareStatement("UPDATE Artiste SET nom = ?, nationalite = ? WHERE id = ?");
            // Set the new values for the artist
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
		// TODO Auto-generated method stub
		try ( Connection connection = daoFactory.getConnection() ) {
			String sql = "SELECT * FROM Artiste WHERE id = ?";
			
			try ( PreparedStatement statement = connection.prepareStatement(sql) ) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	
	public Boolean supprimer(long id) {
		
		try ( Connection connection = daoFactory.getConnection() ) {
			String mysql = "DELETE FROM Artiste WHERE id = ? ";
			try ( PreparedStatement preparedStatement = connection.prepareStatement(mysql) ) {
				preparedStatement.setLong(1, id);
				preparedStatement.execute();
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
  

}
