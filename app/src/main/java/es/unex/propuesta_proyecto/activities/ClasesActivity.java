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

    RecyclerView rvClases;
    ArrayList<String> alClases = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros!=null){
            ImageView perfil = findViewById(R.id.ivUsuario);
            boolean estado = parametros.getBoolean("estado");
            if(estado){ // True -- visible
                perfil.setVisibility(View.VISIBLE);
            }else { // False -- invisible
                perfil.setVisibility(View.INVISIBLE);
            }
        }
        rvClases = findViewById(R.id.rvClases);
        rvClases.setLayoutManager(new LinearLayoutManager(this));

        alClases.add("Clase 1");
        alClases.add("Clase 2");
        alClases.add("Clase 3");
        alClases.add("Clase 4");
        alClases.add("Clase 5");
        alClases.add("Clase 6");
        alClases.add("Clase 7");
        alClases.add("Clase 8");
        alClases.add("Clase 9");
        alClases.add("Clase 10");

        ClasesAdapter clasesAdapter = new ClasesAdapter(alClases);
        rvClases.setAdapter(clasesAdapter);
        // Acceso a clases

    }


    public void perfilUsuario(View view){
        Intent perfil = new Intent(this, ActualizarCuentaActivity.class);
        startActivity(perfil);
    }
}