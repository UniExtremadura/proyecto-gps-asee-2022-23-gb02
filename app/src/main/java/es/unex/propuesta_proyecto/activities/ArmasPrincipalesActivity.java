package es.unex.propuesta_proyecto.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.api.ReposNetworkLoaderRunnable;
import es.unex.propuesta_proyecto.model.Armas;

/* Muestra el listado de armas principales, obtenidas de la API y haciendolas intercambiables con las "por defecto" */

public class ArmasPrincipalesActivity extends AppCompatActivity implements MyAdapter.OnListInteractionListener {


    RecyclerView rvFusilesDeAsalto;
    RecyclerView rvSubfusiles;
    RecyclerView rvEscopetas;
    RecyclerView rvAmetralladorasLigeras;
    RecyclerView rvFusilesDeFrancotirador;

    private MyAdapter fusiles,subfusiles,escopetas,ametrelladoraLigera,fusilesDeFrancotirador;
    private MyAdapter cogerUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armas_principales);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Armas Principales");
        }

        cargarPreferencias();

        fusiles = new MyAdapter(new ArrayList<>(), this);
        subfusiles = new MyAdapter(new ArrayList<>(), this);
        escopetas = new MyAdapter(new ArrayList<>(), this);
        ametrelladoraLigera = new MyAdapter(new ArrayList<>(), this);
        fusilesDeFrancotirador = new MyAdapter(new ArrayList<>(), this);

        rvFusilesDeAsalto = findViewById(R.id.rvFusilesDeAsalto);
        rvFusilesDeAsalto.setLayoutManager(new LinearLayoutManager(this));
        // i < 4 API COMPLETA
        for (int i = 0; i < 2; i++){
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  fusiles.swap((repos))));
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarIdArma((repos.get(0).getPrincipal()))));
        }
        rvFusilesDeAsalto.setAdapter(fusiles); //se carga el ArrayList de fusiles recuperado de la API en el RecyclerView

        rvEscopetas = findViewById(R.id.rvEscopetas);
        rvEscopetas.setLayoutManager(new LinearLayoutManager(this));
        // i < 8 API COMPLETA
        for (int i = 4; i < 6; i++){
/*             AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  escopetas.swap((repos))));
             AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarIdArma((repos.get(0).getPrincipal()))));
        */}
        rvEscopetas.setAdapter(escopetas); //se carga el ArrayList de escopetas recuperado de la API en el RecyclerView

        rvFusilesDeFrancotirador = findViewById(R.id.rvFusilesDeFrancotirador);
        rvFusilesDeFrancotirador.setLayoutManager(new LinearLayoutManager(this));
        // i < 12 API COMPLETA
        for (int i = 8 ; i < 10; i++){
           /*AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  fusilesDeFrancotirador.swap((repos))));
           AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarIdArma((repos.get(0).getPrincipal()))));
        */}
        rvFusilesDeFrancotirador.setAdapter(fusilesDeFrancotirador);//se carga el ArrayList de fusiles de francotirador recuperado de la API en el RecyclerView

        rvAmetralladorasLigeras = findViewById(R.id.rvAmetralladorasLigeras);
        rvAmetralladorasLigeras.setLayoutManager(new LinearLayoutManager(this));
        // i < 15 API COMPLETA
        for (int i = 12 ; i < 14; i++){
/*              AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  ametrelladoraLigera.swap((repos))));
              AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarIdArma((repos.get(0).getPrincipal()))));
        */}
        rvAmetralladorasLigeras.setAdapter(ametrelladoraLigera);//se carga el ArrayList de ametralladoras recuperado de la API en el RecyclerView



        rvSubfusiles = findViewById(R.id.rvSubfusiles);
        rvSubfusiles.setLayoutManager(new LinearLayoutManager(this));
        // i < 19 API COMPLETA
        for (int i = 15; i < 17; i++){
/*            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  subfusiles.swap((repos))));
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarIdArma((repos.get(0).getPrincipal()))));
        */}
        rvSubfusiles.setAdapter(subfusiles);

    }

    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

    private void cargarPreferencias() {

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

       String usuario = preferences.getString("user","usuario vacio");
       String clase = preferences.getString("clase","clases vacia");

        cogerUsuario = new MyAdapter(new ArrayList<>(),this);

        cogerUsuario.usuarioActivo(usuario);
        cogerUsuario.claseActiva(clase);

    }

    @Override
    public void onListInteraction(String url) {
        Uri webpage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }
}