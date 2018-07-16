package com.androfast.server.appcursovolley;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
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

public class Registrar extends AppCompatActivity {
    TextView nombre,movil,correo,clave;
    Button registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        nombre=(TextInputEditText)findViewById(R.id.nombre_registro);
        movil=(TextInputEditText)findViewById(R.id.movil_registro);
        correo=(TextInputEditText)findViewById(R.id.correo_registro);
        clave=(TextInputEditText)findViewById(R.id.password_registro);
        registrar=(Button)findViewById(R.id.btn_registro_usuario);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    public void registrar(){
        RequestQueue queue = Volley.newRequestQueue(Registrar.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Conexion.URL_WEB_SERVICES + "registrar-usuario.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Usuario user=new Usuario();
                        try {
                            JSONObject objResultado = new JSONObject(response);
                            String extadox=objResultado.get("estado").toString();
                            if(!extadox.equalsIgnoreCase("exito")){
                                Toast.makeText(Registrar.this,"error",Toast.LENGTH_LONG).show();

                            }else{

                                Intent intent= new Intent(Registrar.this,IniciarSesion.class);
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
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("nombre", nombre.getText().toString());
                params.put("email",correo.getText().toString());
                params.put("movil", movil.getText().toString());
                params.put("clave",clave.getText().toString());
                return params;

            }
        };
        queue.add(stringRequest);
    }
}
