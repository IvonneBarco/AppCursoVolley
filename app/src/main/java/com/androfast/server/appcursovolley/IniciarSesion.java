package com.androfast.server.appcursovolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class IniciarSesion extends AppCompatActivity {

    TextView username, contrasena;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        username = (TextView) findViewById(R.id.txtUsuario);
        contrasena = (TextView) findViewById(R.id.txtContrasena);
        login = (Button) findViewById(R.id.btnIniciarSesion);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

    }



    public void iniciarSesion() {
        RequestQueue queue = Volley.newRequestQueue(IniciarSesion.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Conexion.URL_WEB_SERVICES + "iniciar-sesion.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Usuario user = new Usuario();
                        try {
                            JSONObject objResultado = new JSONObject(response);
                            String estado = objResultado.get("estado").toString();
                            if (!estado.equalsIgnoreCase("exito")) { //!estado.equalsIgnoreCase("exito")
                                Toast.makeText(IniciarSesion.this, "Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();

                            } else {
                                user.setId(objResultado.getJSONObject("datos").optInt("id"));
                                user.setUsername(objResultado.getJSONObject("datos").optString("username"));
                                user.setNombre(objResultado.getJSONObject("datos").optString("nombre"));
                                user.setRole(objResultado.getJSONObject("datos").optString("role"));
                                Intent intent = new Intent(IniciarSesion.this, MainActivity.class);
                                intent.putExtra("DATOS_USER", user);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username.getText().toString());
                params.put("password", contrasena.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }

}
