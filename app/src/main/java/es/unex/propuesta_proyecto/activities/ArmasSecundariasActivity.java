package es.unex.propuesta_proyecto.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.unex.propuesta_proyecto.R;

public class ArmasSecundariasActivity extends AppCompatActivity {

    ArrayList<Arma> alPistolas = new ArrayList<Arma>();
    ArrayList<Arma> alLanzamisiles = new ArrayList<Arma>();
    ArrayList<Arma> alCuerpoACuerpo = new ArrayList<Arma>();

    RecyclerView rvPistolas;
    RecyclerView rvLanzamisiles;
    RecyclerView rvCuerpoACuerpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armas_secundarias);

        // Carga del RecyclerView de las pistolas
        rvPistolas = findViewById(R.id.rvPistolas);
        rvPistolas.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de las pistolas de la API en alPistolas
        rvPistolas.setAdapter(new ArmasAdapter(alPistolas));

        // Carga del RecyclerView de los lanzamisiles
        rvLanzamisiles = findViewById(R.id.rvLanzamisiles);
        rvLanzamisiles.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de los lanzamisiles de la API en alLanzamisiles
        rvLanzamisiles.setAdapter(new ArmasAdapter(alLanzamisiles));

        // Carga del RecyclerView de las armas cuerpo a cuerpo
        rvCuerpoACuerpo = findViewById(R.id.rvCuerpoACuerpo);
        rvCuerpoACuerpo.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de las armas cuerpo a cuerpo de la API en alCuerpoACuerpo
        rvCuerpoACuerpo.setAdapter(new ArmasAdapter(alCuerpoACuerpo));

    }

}