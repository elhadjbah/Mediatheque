package com.mediatheque.beans;

public class AlbumArtiste {
    private Long idAlbum;
    private Long idArtiste;

    // Getter pour l'identifiant de l'album
    public Long getIdAlbum() {
        return idAlbum;
    }

    // Setter pour l'identifiant de l'album
    public void setIdAlbum(Long idAlbum) {
        this.idAlbum = idAlbum;
    }

    // Getter pour l'identifiant de l'artiste
    public Long getIdArtiste() {
        return idArtiste;
    }

    // Setter pour l'identifiant de l'artiste
    public void setIdArtiste(Long idArtiste) {
        this.idArtiste = idArtiste;
    }
}
