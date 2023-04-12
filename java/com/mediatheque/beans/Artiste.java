package com.mediatheque.beans;

public class Artiste {
	
    private Long id;
    private String nom;
    private String nationalite;
    
    public Artiste(Long id, String nom, String nationalite) {
		if (id != null) { 
			this.id = id;
		}
		this.nom = nom;
		this.nationalite = nationalite;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nation) {
        this.nationalite = nation;
    }
    
   
}

