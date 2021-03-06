package com.androfast.server.appcursovolley.negocio;

public class EntrenadoresVo {

    private String identrenador;
    private String nombreentrenador;
    private String apellidoentrenador;
    private int foto;

    //Constructor
    public EntrenadoresVo(String identrenador, String nombreentrenador, String apellidoentrenador, int foto) {
        this.identrenador = identrenador;
        this.nombreentrenador = nombreentrenador;
        this.apellidoentrenador = apellidoentrenador;
        this.foto = foto;
    }

    public String getIdentrenador() {
        return identrenador;
    }

    public void setIdentrenador(String identrenador) {
        this.identrenador = identrenador;
    }

    public String getNombreentrenador() {
        return nombreentrenador;
    }

    public void setNombreentrenador(String nombreentrenador) {
        this.nombreentrenador = nombreentrenador;
    }

    public String getApellidoentrenador() {
        return apellidoentrenador;
    }

    public void setApellidoentrenador(String apellidoentrenador) {
        this.apellidoentrenador = apellidoentrenador;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public EntrenadoresVo() {
    }



}
