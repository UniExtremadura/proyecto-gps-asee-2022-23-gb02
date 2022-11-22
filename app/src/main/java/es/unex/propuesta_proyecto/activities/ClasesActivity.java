package es.unex.propuesta_proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.activities.ActualizarCuentaActivity;
import es.unex.propuesta_proyecto.activities.ClasesAdapter;

public class ClasesActivity extends AppCompatActivity {

    ArrayList<String> alClases = new ArrayList<String>();
    RecyclerView rvClases;
    ImageView bAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);

        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            ImageView perfil = findViewById(R.id.ivUsuario);
            boolean estado = parametros.getBoolean("estado");
            if (estado) { // True -- visible
                perfil.setVisibility(View.VISIBLE);
            } else { // False -- invisible
                perfil.setVisibility(View.INVISIBLE);
            }
        }
        rvClases = findViewById(R.id.rvClases);
        rvClases.setLayoutManager(new LinearLayoutManager(this));
        bAgregar = findViewById(R.id.ivAgregar);

        alClases.add("Clase 1");
        alClases.add("Clase 2");
        alClases.add("Clase 3");
        rvClases.setAdapter(new ClasesAdapter(alClases));
        bAgregar.setOnClickListener(new View.OnClickListener() {
            int i=4;
            @Override
            public void onClick(View v) {
                alClases.add("Clase "+i);
                if (i == 10) {
                    bAgregar.setVisibility(View.INVISIBLE);
                }
                i++;
                rvClases.setAdapter(new ClasesAdapter(alClases));
            }
        });
        // Acceso a clases
    }

    public void perfilUsuario(View view){
        Intent perfil = new Intent(this, ActualizarCuentaActivity.class);
        startActivity(perfil);
    }
}