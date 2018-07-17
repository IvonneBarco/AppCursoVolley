package com.androfast.server.appcursovolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androfast.server.appcursovolley.negocio.Usuario;

public class Ligas extends AppCompatActivity {

    TextView listarLigas, nomdeporte, iddeporte;
    Button btnLiga;
    private Usuario user;

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

        // Recuperando variables usuario y deporte
        Bundle bundle = getIntent().getExtras();
        user = bundle.getParcelable("DATOS_USER");
        ((TextView) findViewById(R.id.idSesion)).setText("ID USUARIO: "+ user.getId());
        ((TextView) findViewById(R.id.roleSesion)).setText("ROL: "+ user.getRole());

        String recuperamos_iddeporte = getIntent().getStringExtra("iddeporte");
        ((TextView) findViewById(R.id.id_deporte)).setText("ID DEPORTE: "+recuperamos_iddeporte);
        // Fin * Recuperando variables usuario y deporte




    }

}
