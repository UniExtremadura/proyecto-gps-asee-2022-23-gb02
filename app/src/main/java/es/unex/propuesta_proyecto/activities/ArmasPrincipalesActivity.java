package es.unex.propuesta_proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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
    ArrayList<Armas> alFusilesDePrecision = new ArrayList<Armas>();
    ArrayList<Armas> alFusilesDeFrancotirador = new ArrayList<Armas>();

    RecyclerView rvFusilesDeAsalto;
    RecyclerView rvSubfusiles;
    RecyclerView rvEscopetas;
    RecyclerView rvAmetralladorasLigeras;
    RecyclerView rvFusilesDePrecision;
    RecyclerView rvFusilesDeFrancotirador;

    private MyAdapter fusiles,subfusiles,escopetas,ametrelladoraLigera,fusilesDePrecision,fusilesDeFrancotirador;
    /*public Armas getArma(){
        Armas a = new Armas();
        String precision
        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(0,(repos) ->   precision [0] = repos.get(0).getAccuracy()));
        //a.setAccuracy(Integer.parseInt(precision.toString()));
        Log.d("NOMBRE",precision.toString());
        return a;
    } */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armas_principales);
        /** INICIALIZACION DE LOS ADAPTERS **/
        fusiles = new MyAdapter(new ArrayList<>(), this);
        subfusiles = new MyAdapter(new ArrayList<>(), this);
        escopetas = new MyAdapter(new ArrayList<>(), this);
        ametrelladoraLigera = new MyAdapter(new ArrayList<>(), this);
        fusilesDePrecision = new MyAdapter(new ArrayList<>(), this);
        fusilesDeFrancotirador = new MyAdapter(new ArrayList<>(), this);

        // Carga del RecyclerView de los fusiles de asalto
        rvFusilesDeAsalto = findViewById(R.id.rvFusilesDeAsalto);
        rvFusilesDeAsalto.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de los fusiles de asalto de la API en alFusilesDeAsalto
        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(0,(repos) ->  fusiles.swap((repos))));
        rvFusilesDeAsalto.setAdapter(fusiles);



        // Carga del RecyclerView de los subfusiles
        rvSubfusiles = findViewById(R.id.rvSubfusiles);
        rvSubfusiles.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de los subfusiles de la API en alSubfusiles
        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(1,(repos) ->  subfusiles.swap((repos))));
        rvSubfusiles.setAdapter(subfusiles);

        // Carga del RecyclerView de las escopetas
        rvEscopetas = findViewById(R.id.rvEscopetas);
        rvEscopetas.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de las escopetas de la API en alEscopetas
        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(2,(repos) ->  escopetas.swap((repos))));
        rvEscopetas.setAdapter(escopetas);

        // Carga del RecyclerView de las ametralladoras ligeras
        rvAmetralladorasLigeras = findViewById(R.id.rvAmetralladorasLigeras);
        rvAmetralladorasLigeras.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de las ametralladoras ligeras de la API en alAmetralladorasLigeras
        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(3,(repos) ->  ametrelladoraLigera.swap((repos))));
        rvAmetralladorasLigeras.setAdapter(ametrelladoraLigera);

        // Carga del RecyclerView de los fusiles de precision
        rvFusilesDePrecision = findViewById(R.id.rvFusilesDePrecision);
        rvFusilesDePrecision.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de los fusiles de precision de la API en alFusilesDePrecision
        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(4,(repos) ->  fusilesDePrecision.swap((repos))));
        rvFusilesDePrecision.setAdapter(fusilesDePrecision);

        // Carga del RecyclerView de los fusiles de francotirador
        rvFusilesDeFrancotirador = findViewById(R.id.rvFusilesDeFrancotirador);
        rvFusilesDeFrancotirador.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Inserción de los fusiles de francotirador de la API en alFusilesDeFrancotirador
        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(5,(repos) ->  fusilesDeFrancotirador.swap((repos))));
        rvFusilesDeFrancotirador.setAdapter(fusilesDeFrancotirador);

    }

    @Override
    public void onListInteraction(String url) {
        Uri webpage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }
}