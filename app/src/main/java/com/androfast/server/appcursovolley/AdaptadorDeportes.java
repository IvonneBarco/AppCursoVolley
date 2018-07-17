package com.androfast.server.appcursovolley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androfast.server.appcursovolley.negocio.Deporte;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorDeportes extends RecyclerView.Adapter<AdaptadorDeportes.ViewHolderDeportes> implements View.OnClickListener{

    /*private Context context;
    private List<Deporte> videoBeansList;

    public AdaptadorDeportes(ArrayList<Deporte> objects) {
        super(context, resource, objects);
        this.context = context;
        this.videoBeansList = objects;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        view = inflater.inflate(R.layout.item_lista_deportes, null);
        Deporte videoBean = videoBeansList.get(position);

        TextView titleTextVIew = (TextView) view.findViewById(R.id.id_nombredeporte_list);
        titleTextVIew.setText(videoBean.getNombre());

        return view;
    }*/


    ArrayList<DeportesVo> listaDeportes;
    private  View.OnClickListener listener;

    public AdaptadorDeportes(ArrayList<DeportesVo> listaDeportes) {
        this.listaDeportes = listaDeportes;
    }

    @Override
    public ViewHolderDeportes onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_deportes, null, false);
        //Escucha la selección
        view.setOnClickListener(this);
        return new ViewHolderDeportes(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDeportes holder, int position) {
        holder.etiqueta_iddeporte.setText(listaDeportes.get(position).getNombredeporte());
        holder.etiqueta_deporte.setText(listaDeportes.get(position).getNombredeporte());
        holder.etiqueta_foto.setImageResource(listaDeportes.get(position).getFoto());

    }

    @Override
    public int getItemCount() {
        return listaDeportes.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }

    }

    public class ViewHolderDeportes extends RecyclerView.ViewHolder {

        TextView etiqueta_deporte, etiqueta_iddeporte;
        ImageView etiqueta_foto;

        public ViewHolderDeportes(View itemView) {
            super(itemView);
            etiqueta_deporte = (TextView)itemView.findViewById(R.id.id_nombredeporte_list);
            etiqueta_iddeporte = (TextView)itemView.findViewById(R.id.id_deporte_list);
            etiqueta_foto = (ImageView)itemView.findViewById(R.id.id_imagen_deporte_list);
        }
    }
}
