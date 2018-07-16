package com.androfast.server.appcursovolley;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorDeportes extends RecyclerView.Adapter<AdaptadorDeportes.ViewHolderDeportes> {

    ArrayList<DeportesVo> listaDeportes;

    public AdaptadorDeportes(ArrayList<DeportesVo> listaDeportes) {
        this.listaDeportes = listaDeportes;
    }

    @Override
    public ViewHolderDeportes onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_deportes, null, false);
        return new ViewHolderDeportes(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDeportes holder, int position) {
        holder.etiqueta_deporte.setText(listaDeportes.get(position).getNombredeporte());
        holder.etiqueta_foto.setImageResource(listaDeportes.get(position).getFoto());

    }

    @Override
    public int getItemCount() {
        return listaDeportes.size();
    }

    public class ViewHolderDeportes extends RecyclerView.ViewHolder {

        TextView etiqueta_deporte;
        ImageView etiqueta_foto;

        public ViewHolderDeportes(View itemView) {
            super(itemView);
            etiqueta_deporte = (TextView)itemView.findViewById(R.id.id_deporte_list);
            etiqueta_foto = (ImageView)itemView.findViewById(R.id.id_imagen_deporte_list);
        }
    }
}