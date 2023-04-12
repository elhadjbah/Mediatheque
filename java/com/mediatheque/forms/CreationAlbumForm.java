package com.mediatheque.forms;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mediatheque.beans.Album;
import com.mediatheque.dao.AlbumDao;
import com.mediatheque.dao.DAOException;

public class CreationAlbumForm {
	private AlbumDao albumDao;
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	

	private static final String CHAMP_DESCRIPTION = "description";
	private static final String CHAMP_DATE_SORTIE = "date_sortie";
	private static final String PRIX_ACHAT = "prix_achat";

	public CreationAlbumForm(AlbumDao albumDao) {
		this.albumDao = albumDao;
	}

	@SuppressWarnings("deprecation")
	public Album creerAlbum(HttpServletRequest request) {
		System.out.println("debut creation artiste");
		String description = getValeurChamp(request, CHAMP_DESCRIPTION);
		double prix = Double.parseDouble(getValeurChamp(request, PRIX_ACHAT));


		String date_s = getValeurChamp(request, CHAMP_DATE_SORTIE);
		
		Album album = new Album(null, description, date_s,prix);

		try {
			validationDescription(description);
		} catch (FormValidationException e) {
			setErreur(CHAMP_DESCRIPTION, e.getMessage());
		}

		try {
			validationPrix(prix);
		} catch (FormValidationException e) {
			setErreur(PRIX_ACHAT, e.getMessage());
		}
		


		if (erreurs.isEmpty()) {
			try {
				albumDao.creer(album);
				resultat = "Succès de la création de l'artiste.";
			} catch (DAOException e) {
				resultat = "Échec de la création de l'artiste : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
				e.printStackTrace();
			}
		} else {
			resultat = "Échec de la création de l'artiste.";
		}
		System.out.println(resultat);
		return album;
	}

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

	private void validationDescription(String nom) throws FormValidationException {
		if (nom == null || nom.trim().length() < 2) {
			throw new FormValidationException("La description doit contenir au moins 2 caractères.");
		}
	}

	private void validationPrix(double prix) throws FormValidationException {
		if (prix <= 0) {
			throw new FormValidationException("Le prix de l'album doit être supérieur à null.");
		}
	}

}
