package com.androfast.server.appcursovolley.negocio;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class Deporte {

    private int id;
    private String nombre;
    private String descripcion;

    public Deporte(){}

    public Deporte(int id, String nombre, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   /* public Bundle getPoetInfoBundle(){
        Bundle bundle = new Bundle();
        bundle.putInt("id",this.id);
        bundle.putString("nombre",this.nombre);
        bundle.putString("descripcion",this.descripcion);
        return bundle;
    }*/
}