package es.unex.propuesta_proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.api.ReposNetworkLoaderRunnable;

public class ArmasPrincipalesActivity extends AppCompatActivity implements MyAdapter.OnListInteractionListener {

    ArrayList<Armas> alFusilesDeAsalto = new ArrayList<Armas>();
    ArrayList<Armas> alSubfusiles = new ArrayList<Armas>();
    ArrayList<Armas> alEscopetas = new ArrayList<Armas>();
    ArrayList<Armas> alAmetralladorasLigeras = new ArrayList<Armas>();
    ArrayList<Armas> alFusilesDeFrancotirador = new ArrayList<Armas>();

    RecyclerView rvFusilesDeAsalto;
    RecyclerView rvSubfusiles;
    RecyclerView rvEscopetas;
    RecyclerView rvAmetralladorasLigeras;
    RecyclerView rvFusilesDeFrancotirador;

    private MyAdapter fusiles,subfusiles,escopetas,ametrelladoraLigera,fusilesDeFrancotirador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armas_principales);
        /** INICIALIZACION DE LOS ADAPTERS **/
        fusiles = new MyAdapter(new ArrayList<>(), this);
        subfusiles = new MyAdapter(new ArrayList<>(), this);
        escopetas = new MyAdapter(new ArrayList<>(), this);
        ametrelladoraLigera = new MyAdapter(new ArrayList<>(), this);
        fusilesDeFrancotirador = new MyAdapter(new ArrayList<>(), this);

        // Carga del RecyclerView de los fusiles de asalto
        rvFusilesDeAsalto = findViewById(R.id.rvFusilesDeAsalto);
        rvFusilesDeAsalto.setLayoutManager(new LinearLayoutManager(this));
        // Inserción de los fusiles de asalto de la API en alFusilesDeAsalto
        for (int i = 0 ; i < 3; i++){
          //AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  fusiles.swap((repos))));
        }
        rvFusilesDeAsalto.setAdapter(fusiles);


        // Carga del RecyclerView de los subfusiles
        rvSubfusiles = findViewById(R.id.rvSubfusiles);
        rvSubfusiles.setLayoutManager(new LinearLayoutManager(this));
        // Inserción de los subfusiles de la API en alSubfusiles
        for (int i = 0 ; i < 3; i++){
          //  AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  subfusiles.swap((repos))));
        }
        rvSubfusiles.setAdapter(subfusiles);

        // Carga del RecyclerView de las escopetas
        rvEscopetas = findViewById(R.id.rvEscopetas);
        rvEscopetas.setLayoutManager(new LinearLayoutManager(this));
        // Inserción de las escopetas de la API en alEscopetas
        for (int i = 0 ; i < 3; i++){
           // AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  escopetas.swap((repos))));
        }
        rvEscopetas.setAdapter(escopetas);

        // Carga del RecyclerView de las ametralladoras ligeras
        rvAmetralladorasLigeras = findViewById(R.id.rvAmetralladorasLigeras);
        rvAmetralladorasLigeras.setLayoutManager(new LinearLayoutManager(this));
        // Inserción de las ametralladoras ligeras de la API en alAmetralladorasLigeras
        for (int i = 0 ; i < 3; i++){
           // AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  ametrelladoraLigera.swap((repos))));
        }
        rvAmetralladorasLigeras.setAdapter(ametrelladoraLigera);

        // Carga del RecyclerView de los fusiles de francotirador
        rvFusilesDeFrancotirador = findViewById(R.id.rvFusilesDeFrancotirador);
        rvFusilesDeFrancotirador.setLayoutManager(new LinearLayoutManager(this));
        //Inserción de los fusiles de francotirador de la API en alFusilesDeFrancotirador
        for (int i = 0 ; i < 3; i++){
           // AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  fusilesDeFrancotirador.swap((repos))));
        }
        rvFusilesDeFrancotirador.setAdapter(fusilesDeFrancotirador);

    }

    @Override
    public void onListInteraction(String url) {
        Uri webpage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }
}