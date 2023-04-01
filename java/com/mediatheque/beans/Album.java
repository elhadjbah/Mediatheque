package com.mediatheque.beans;

import java.math.BigDecimal;
import java.sql.Date;

public class Album {
	
    private Long id;
    private String description;
    private Date dateSortie;
    private BigDecimal prixAchat;

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
    public Date getDateSortie() {
        return dateSortie;
    }

    // Setter pour la date de sortie de l'album
    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    // Getter pour le prix d'achat de l'album
    public BigDecimal getPrixAchat() {
        return prixAchat;
    }

    // Setter pour le prix d'achat de l'album
    public void setPrixAchat(BigDecimal prixAchat) {
        this.prixAchat = prixAchat;
    }
}

