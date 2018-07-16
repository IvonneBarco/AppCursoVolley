package com.androfast.server.appcursovolley;

public class DeportesVo {

    private String nombredeporte;
    private int foto;

    public DeportesVo(){}

    //Constructor
    public DeportesVo(String nombredeporte, int foto){
        this.nombredeporte = nombredeporte;
        this.foto = foto;
    }

    public String getNombredeporte() {
        return nombredeporte;
    }

    public void setNombredeporte(String nombredeporte) {
        this.nombredeporte = nombredeporte;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }


}
