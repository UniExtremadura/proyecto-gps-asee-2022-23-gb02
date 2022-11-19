package es.unex.propuesta_proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ClasesActivity extends AppCompatActivity {

    RecyclerView rvClases;
    ArrayList<String> alClases = new ArrayList<String>();;

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

        ClasesAdapter clasesAdapter = new ClasesAdapter(alClases);
        rvClases.setAdapter(clasesAdapter);

        // Acceso a clases

    }
// Mirar onclickListener (1 para cada clase (1 diseño para cada clase))
    public void accederClases(View view){ // TODO - Acceso a las diferentes clases
        Intent actClase = new Intent(this,DetalleClaseActivity.class);
        startActivity(actClase);
    }

    public void perfilUsuario(View view){
        Intent perfil = new Intent(this,ActualizarCuentaActivity.class);
        startActivity(perfil);
    }
}