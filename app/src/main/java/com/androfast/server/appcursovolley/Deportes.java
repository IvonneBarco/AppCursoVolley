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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Conexion.URL_WEB_SERVICES + "listar-deportes.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Deporte sport = new Deporte();
                        try {
                            JSONObject objResultado = new JSONObject(response);
                            String estado = objResultado.get("estado").toString();
                            if (!estado.equalsIgnoreCase("exito")) {
                                Toast.makeText(Deportes.this, "No hay datos", Toast.LENGTH_LONG).show();
                            } else {
                                sport.setId(objResultado.getJSONObject("deportes").optInt("id"));
                                sport.setNombre(objResultado.getJSONObject("deportes").optString("nombre"));
                                Intent intent = new Intent(Deportes.this, Ligas.class);
                                intent.putExtra("DATOS_SPORT", sport);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        listarDeportes.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
            }
        }) {

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(user.getId()));
                params.put("role", user.getRole());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


}
