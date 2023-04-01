package com.mediatheque.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mediatheque.beans.Artiste;
import com.mediatheque.dao.ArtisteDao;
import com.mediatheque.dao.DAOException;

public class CreationArtisteForm {
	
	private ArtisteDao artisteDao;
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_NATIONALITE = "nationalite";

	public CreationArtisteForm(ArtisteDao artisteDao) {
		this.artisteDao = artisteDao;
	}

	public Artiste creerArtiste(HttpServletRequest request) {
		System.out.println("debut creation artiste");
		String nom = getValeurChamp(request, CHAMP_NOM);
		String nationalite = getValeurChamp(request, CHAMP_NATIONALITE);
		
		Artiste artiste = new Artiste(null, nom,nationalite);

		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}

		try {
			validationNationalite(nationalite);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NATIONALITE, e.getMessage());
		}

		if (erreurs.isEmpty()) {
			try {
				artisteDao.creer(artiste);
				resultat = "Succès de la création de l'artiste.";
			} catch (DAOException e) {
				resultat = "Échec de la création de l'artiste : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
				e.printStackTrace();
			}
		} else {
			resultat = "Échec de la création de l'artiste.";
		}
		System.out.println(resultat);
		return artiste;
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

	private void validationNom(String nom) throws FormValidationException {
		if (nom == null || nom.trim().length() < 2) {
			throw new FormValidationException("Le nom de l'artiste doit contenir au moins 2 caractères.");
		}
	}

	private void validationNationalite(String nationalite) throws FormValidationException {
		if (nationalite == null || nationalite.trim().length() < 2) {
			throw new FormValidationException("La nationalité de l'artiste doit contenir au moins 2 caractères.");
		}
	}

}
