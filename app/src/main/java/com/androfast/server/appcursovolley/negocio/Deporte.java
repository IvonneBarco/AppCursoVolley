package com.androfast.server.appcursovolley.negocio;

import android.os.Parcel;
import android.os.Parcelable;

public class Deporte implements Parcelable{

    private int id;
    private String nombre;
    private String descripcion;



    protected Deporte(Parcel in) {
        setId(in.readInt());
        setNombre(in.readString());
        setDescripcion(in.readString());
    }

    public static final Creator<Deporte> CREATOR = new Creator<Deporte>() {
        @Override
        public Deporte createFromParcel(Parcel in) {
            return new Deporte(in);
        }

        @Override
        public Deporte[] newArray(int size) {
            return new Deporte[size];
        }
    };

    public  Deporte(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getId());
        parcel.writeString(getNombre());
        parcel.writeString(getDescripcion());
    }

    public int getId() {
        return id;
    }

    public void setId(int idnombre) {
        this.id = idnombre;
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
}
