package com.androfast.server.appcursovolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetallesDeporte extends AppCompatActivity {

    TextView dId, dNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_deporte);

        Bundle bundle = getIntent().getExtras();

        dId = (TextView) findViewById(R.id.id_detalle_d);
        dNombre = (TextView) findViewById(R.id.nombre_detalle_d);


        dId.setText(bundle.getString("iddeporte"));
        dNombre.setText(bundle.getString("nombredeporte"));

    }
}
