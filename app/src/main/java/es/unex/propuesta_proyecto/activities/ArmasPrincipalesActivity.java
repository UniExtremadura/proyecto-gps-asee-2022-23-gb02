package es.unex.propuesta_proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import es.unex.propuesta_proyecto.R;

public class ArmasPrincipalesActivity extends AppCompatActivity {

    ArrayList<Arma> alFusilesDeAsalto = new ArrayList<Arma>();
    ArrayList<Arma> alSubfusiles = new ArrayList<Arma>();
    ArrayList<Arma> alEscopetas = new ArrayList<Arma>();
    ArrayList<Arma> alAmetralladorasLigeras = new ArrayList<Arma>();
    ArrayList<Arma> alFusilesDePrecision = new ArrayList<Arma>();
    ArrayList<Arma> alFusilesDeFrancotirador = new ArrayList<Arma>();

    RecyclerView rvFusilesDeAsalto;
    RecyclerView rvSubfusiles;
    RecyclerView rvEscopetas;
    RecyclerView rvAmetralladorasLigeras;
    RecyclerView rvFusilesDePrecision;
    RecyclerView rvFusilesDeFrancotirador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armas_principales);

        // Carga del RecyclerView de los fusiles de asalto
        rvFusilesDeAsalto = findViewById(R.id.rvFusilesDeAsalto);
        rvFusilesDeAsalto.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de los fusiles de asalto de la API en alFusilesDeAsalto
        rvFusilesDeAsalto.setAdapter(new ArmasAdapter(alFusilesDeAsalto));

        // Carga del RecyclerView de los subfusiles
        rvSubfusiles = findViewById(R.id.rvSubfusiles);
        rvSubfusiles.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de los subfusiles de la API en alSubfusiles
        rvSubfusiles.setAdapter(new ArmasAdapter(alSubfusiles));

        // Carga del RecyclerView de las escopetas
        rvEscopetas = findViewById(R.id.rvEscopetas);
        rvEscopetas.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de las escopetas de la API en alEscopetas
        rvEscopetas.setAdapter(new ArmasAdapter(alEscopetas));

        // Carga del RecyclerView de las ametralladoras ligeras
        rvAmetralladorasLigeras = findViewById(R.id.rvAmetralladorasLigeras);
        rvAmetralladorasLigeras.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de las ametralladoras ligeras de la API en alAmetralladorasLigeras
        rvAmetralladorasLigeras.setAdapter(new ArmasAdapter(alAmetralladorasLigeras));

        // Carga del RecyclerView de los fusiles de precision
        rvFusilesDePrecision = findViewById(R.id.rvFusilesDePrecision);
        rvFusilesDePrecision.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de los fusiles de precision de la API en alFusilesDePrecision
        rvFusilesDePrecision.setAdapter(new ArmasAdapter(alFusilesDePrecision));

        // Carga del RecyclerView de los fusiles de francotirador
        rvFusilesDeFrancotirador = findViewById(R.id.rvFusilesDeFrancotirador);
        rvFusilesDeFrancotirador.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de los fusiles de francotirador de la API en alFusilesDeFrancotirador
        rvFusilesDeFrancotirador.setAdapter(new ArmasAdapter(alFusilesDeFrancotirador));

    }

}