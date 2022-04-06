package com.infotrack.capacitacionandroidit.modelos;

import java.io.Serializable;
import java.util.UUID;

public class Cancion implements Serializable {

    //region Atributos
    private String idCancion;
    private String nombreCancion;
    private String nombreAlbum;
    private String nombreArtista;
    private boolean esFolKMetal;
    private boolean esMetal;
    private boolean esAlternativo;
    //endregion


    public Cancion() {
        this.idCancion = idCancion == null || idCancion.isEmpty() ? UUID.randomUUID().toString() : idCancion;
    }

    //region Propiedades
    public String getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(String idCancion) {
        this.idCancion = idCancion;
    }

    public String getNombreCancion() {
        return nombreCancion == null ? "" : nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public Boolean getEsFolKMetal() {
        return esFolKMetal;
    }

    public void setEsFolKMetal(Boolean esFolKMetal) {
        this.esFolKMetal = esFolKMetal;
    }

    public Boolean getEsMetal() {
        return esMetal;
    }

    public void setEsMetal(Boolean esMetal) {
        this.esMetal = esMetal;
    }

    public Boolean getEsAlternativo() {
        return esAlternativo;
    }

    public void setEsAlternativo(Boolean esAlternativo) {
        this.esAlternativo = esAlternativo;
    }
    //endregion

}
