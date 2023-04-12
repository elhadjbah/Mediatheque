package com.mediatheque.beans;

public class Album {
	
    private Long id;
    private String description;
    private String dateSortie;
    private double prixAchat;
    
    public Album(Long id, String description, String dateSortie, double prixAchat) {
	
		if (id != null) { 
			this.id = id;
		}
		this.description = description;
		this.dateSortie = dateSortie;
		this.prixAchat = prixAchat;
	}

	

    // Getter pour l'identifiant de l'album
    public Long getId() {
        return id;
    }

    // Setter pour l'identifiant de l'album
    public void setId(Long id) {
        this.id = id;
    }

    // Getter pour la description de l'album
    public String getDescription() {
        return description;
    }

    // Setter pour la description de l'album
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter pour la date de sortie de l'album
    public String getDateSortie() {
        return dateSortie;
    }

    // Setter pour la date de sortie de l'album
    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    // Getter pour le prix d'achat de l'album
    public double getPrixAchat() {
        return prixAchat;
    }

    // Setter pour le prix d'achat de l'album
    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }
}

