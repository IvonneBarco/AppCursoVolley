package com.androfast.server.appcursovolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androfast.server.appcursovolley.datos.Conexion;
import com.androfast.server.appcursovolley.negocio.Usuario;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    TextView listarUsuario;
    Button btn_listar, btndeportes, btnEntrenoProg, btnInciarEntreno;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listarUsuario = (TextView) findViewById(R.id.txtListar);

        btnEntrenoProg = (Button) findViewById(R.id.btn_entreno_programado);
        btnEntrenoProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, Registrar.class);
                startActivity(intent3);

            }
        });

        btnInciarEntreno = (Button) findViewById(R.id.btn_iniciar_entreno);
        btnInciarEntreno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(MainActivity.this, Registrar2.class);
                startActivity(intent2);
            }
        });

        btn_listar = (Button) findViewById(R.id.btn_listar_user);
        btn_listar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listar();
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_LONG).show();
            }
        });

        btndeportes = (Button) findViewById(R.id.btnIrDeportes);
        btndeportes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Deportes.class);
                intent.putExtra("DATOS_USER", user);
                startActivity(intent);
            }
        });
        Bundle bundle = getIntent().getExtras();
        user = bundle.getParcelable("DATOS_USER");
        ((TextView) findViewById(R.id.txtIdSesion)).setText(user.getId() + "");
        ((TextView) findViewById(R.id.txtNombreSesion)).setText(user.getNombre());
        ((TextView) findViewById(R.id.txtUsuarioSesion)).setText(user.getUsername());
        ((TextView) findViewById(R.id.txtRole)).setText(user.getRole());
    }

    public void listar() {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Conexion.URL_WEB_SERVICES + "listar-usuario.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        listarUsuario.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

        };
        queue.add(stringRequest);
    }
}