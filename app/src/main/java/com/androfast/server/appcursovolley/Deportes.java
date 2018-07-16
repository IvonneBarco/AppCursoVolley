package com.androfast.server.appcursovolley;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androfast.server.appcursovolley.datos.Conexion;
import com.androfast.server.appcursovolley.negocio.Deporte;
import com.androfast.server.appcursovolley.negocio.Liga;
import com.androfast.server.appcursovolley.negocio.Usuario;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Deportes extends AppCompatActivity {

    TextView listarDeportes, mTextViewResult;
    Button btnListarDeporte, btnIrLiga;
    private Usuario user;
    private ArrayList<String> mEntries;

    TextView username, contrasena;
    private TextView mTextView;


    //Creamos una lista igual a la de AdaptadorDeportes
    ArrayList<DeportesVo> listaDeportes;

    //Referencia al recycler_id
    RecyclerView recyclerDeportes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deportes);

        listarDeportes = (TextView) findViewById(R.id.txtListarDeportes);
        btnListarDeporte = (Button) findViewById(R.id.btnListDeporte);

        username = (TextView) findViewById(R.id.txtUsuario);
        contrasena = (TextView) findViewById(R.id.txtContrasena);

        btnListarDeporte.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listar();
            }
        });

        btnIrLiga = (Button) findViewById(R.id.btnIrLiga);
        btnIrLiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentd = new Intent(Deportes.this, Ligas.class);
                intentd.putExtra("DATOS_USER", user);
                startActivity(intentd);
                listar();
            }
        });

        Bundle bundle = getIntent().getExtras();
        user = bundle.getParcelable("DATOS_USER");
        ((TextView) findViewById(R.id.idSesion)).setText(user.getId() + "");
        ((TextView) findViewById(R.id.roleSesion)).setText(user.getRole());

        //INICIO CODIGO RECYCLER

        /*listaDeportes = new ArrayList<>();
        recyclerDeportes = (RecyclerView) findViewById(R.id.RecyclerId);
        recyclerDeportes.setLayoutManager(new LinearLayoutManager(this));

        llenarDeportes();
        AdaptadorDeportes adapter = new AdaptadorDeportes(listaDeportes);
        recyclerDeportes.setAdapter(adapter);*/

        //FIN CODIGO RECYCLER
    }

    public void llenarDeportes() {
        listaDeportes.add(new DeportesVo(user.getNombre(), R.drawable.profile));


    }


    public void listar() {
        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(Deportes.this);

        // Initialize a new JsonArrayRequest instance
        StringRequest stringRequest = new StringRequest(Method.POST, Conexion.URL_WEB_SERVICES + "listar-deportes.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String datosd = "";

                        try {
                            JSONObject objresultado = new JSONObject(response);
                            JSONArray deportes = objresultado.getJSONArray("resultado");

                            if (deportes.length()>0){
                                for (int i = 0; i < deportes.length(); i++){
                                    JSONObject deporte = deportes.getJSONObject(i);
                                    datosd = deporte.getString("nombre").toString();

                                    //listarDeportes.setText(deporte.getString("iddeporte").toString());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        listarDeportes.setText(datosd);
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
                return params;
            }
        };

        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(stringRequest);
    }


}
