package com.androfast.server.appcursovolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androfast.server.appcursovolley.datos.Conexion;
import com.androfast.server.appcursovolley.negocio.Deporte;
import com.androfast.server.appcursovolley.negocio.Usuario;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Ligas extends AppCompatActivity {

    TextView listarLigas, nomdeporte, iddeporte;
    Button btnLiga;
    private Usuario user;
    private Deporte sport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligas);

        listarLigas = (TextView) findViewById(R.id.txtListarLigas);
        btnLiga = (Button) findViewById(R.id.btnLiga);
        btnLiga.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //listarLigas();
            }
        });

        Bundle bundle = getIntent().getExtras();
        user = bundle.getParcelable("DATOS_USER");
        ((TextView) findViewById(R.id.idSesion)).setText(user.getId()+"");
        ((TextView) findViewById(R.id.roleSesion)).setText(user.getRole());

        Bundle bundleSport = getIntent().getExtras();
        /*((TextView) findViewById(R.id.id_deporte)).setText("ID DEPORTE: "+sport.getId());
        ((TextView) findViewById(R.id.nom_deporte)).setText("DEPORTE: " +sport.getNombre());*/

        //((TextView) findViewById(R.id.nom_deporte)).setText("DEPORTE: " +bundleSport.getString("iddeporte"));
        //String nombrerec = getIntent().getStringExtra("dato_nombre");

        //nomdeporte.setText(nombrerec);




    }

}
