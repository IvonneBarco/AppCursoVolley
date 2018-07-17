package com.androfast.server.appcursovolley;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androfast.server.appcursovolley.datos.Conexion;
import com.androfast.server.appcursovolley.negocio.DeportesVo;
import com.androfast.server.appcursovolley.negocio.LigasVo;
import com.androfast.server.appcursovolley.negocio.Usuario;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ligas extends AppCompatActivity {

    private Usuario user;
    private LigasVo liga;
    private DeportesVo sport;
    TextView nomdeporte, iddeporte;

    //RECYCLER
    ArrayList<LigasVo> listaLigas;

    //Refencia al reclycler
    RecyclerView recyclerLigas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligas);

        // Recuperando variables usuario y deporte
        Bundle bundle = getIntent().getExtras();
        user = bundle.getParcelable("DATOS_USER");
        ((TextView) findViewById(R.id.idSesion)).setText("ID USUARIO: " + user.getId());
        ((TextView) findViewById(R.id.roleSesion)).setText("ROL: " + user.getRole());

        String recuperamos_iddeporte = getIntent().getStringExtra("iddeporte");
        ((TextView) findViewById(R.id.id_deporte)).setText("ID DEPORTE: " + recuperamos_iddeporte);
        // Fin * Recuperando variables usuario y deporte

        //Inicio * Recycler
        listaLigas = new ArrayList<>();
        recyclerLigas = (RecyclerView) findViewById(R.id.RecyclerLigasId);
        recyclerLigas.setLayoutManager(new LinearLayoutManager(this));
        recyclerLigas.setHasFixedSize(true);
        //Fin * Recycler

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(Ligas.this);

        // Initialize a new JsonArrayRequest instance
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Conexion.URL_WEB_SERVICES + "listar-ligas.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        LigasVo liga = null;

                        try {
                            JSONObject objresultado = new JSONObject(response);
                            JSONArray ligas = objresultado.getJSONArray("ligas");

                            if (ligas.length()<=0){
                                Toast.makeText(Ligas.this, "NO HAY DATOS", Toast.LENGTH_LONG).show();

                            }else{

                                for (int i = 0; i < ligas.length(); i++){
                                    liga = new LigasVo();
                                    JSONObject objligas = ligas.getJSONObject(i);

                                    liga.setIdliga(String.valueOf(objligas.optInt("idliga")));
                                    liga.setNombreliga(objligas.optString("nombre"));
                                    liga.setFoto(R.drawable.icon_sports);
                                    listaLigas.add(liga);


                                }
                                AdaptadorLigas adapter = new AdaptadorLigas(listaLigas);
                                //evento click
                                adapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(getApplicationContext(), "Selección: " +
                                                listaLigas.get(recyclerLigas.getChildAdapterPosition(view)).getNombreliga(), Toast.LENGTH_LONG).show();

                                        //Envio de variables DATOS_USER
                                        Intent intentd = new Intent(Ligas.this, Ligas.class);
                                        intentd.putExtra("DATOS_USER", user);
                                        //Envio variable iddeporte
                                        intentd.putExtra("ideliga", listaLigas.get(recyclerLigas.getChildAdapterPosition(view)).getIdliga());
                                        startActivity(intentd);

                                    }
                                });
                                //fin evento click
                                recyclerLigas.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Ligas.this, "NO HAY CONEXIÓN", Toast.LENGTH_LONG).show();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Do something when error occurred
            }
        }) {

            //LOS CAMPOS EN VERDE DEBEN SER IGUAL AL DEL ARCHIVO PHP
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(user.getId()));
                params.put("role", user.getRole());
                params.put("deporte",getIntent().getStringExtra("iddeporte"));
                return params;
            }
        };

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(stringRequest);


    }

}