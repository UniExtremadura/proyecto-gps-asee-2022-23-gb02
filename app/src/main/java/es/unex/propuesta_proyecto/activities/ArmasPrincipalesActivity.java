package es.unex.propuesta_proyecto.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

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
    private MyAdapter cogerUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armas_principales);

        cargarPreferencias(); // Carga preferencias.

        /* INICIALIZACION DE LOS ADAPTERS */

        fusiles = new MyAdapter(new ArrayList<>(), this);
        subfusiles = new MyAdapter(new ArrayList<>(), this);
        escopetas = new MyAdapter(new ArrayList<>(), this);
        ametrelladoraLigera = new MyAdapter(new ArrayList<>(), this);
        fusilesDeFrancotirador = new MyAdapter(new ArrayList<>(), this);

        /** IMPORTANTE ! LOS ACCESOS A LA API SE ENCUENTRAN COMENTADOS, PUES SOLO SE TIENEN 100 USOS.. (LO CUAL SIGNIFICA QUE SOLO SE VISUALIZARAN UN PORCENTAJE DE ARMAS..
         * Y DARÁ ERROR CUANDO SE UTILICEN TODOS LOS ACCESOS. ADEMÁS LAS IMAGENES TARDAN EN CARGAR LO SUYO..) **/


        // Carga del RecyclerView de los fusiles de asalto
        rvFusilesDeAsalto = findViewById(R.id.rvFusilesDeAsalto);
        rvFusilesDeAsalto.setLayoutManager(new LinearLayoutManager(this));
        // Inserción de los fusiles de asalto de la API en alFusilesDeAsalto
        // i < 17 API COMPLETA
        for (int i = 1; i < 3; i++){
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  fusiles.swap((repos))));
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarIdArma((repos.get(0).getPrincipal()))));
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarWeapon((repos.get(0).getWeapon()))));
        }
        rvFusilesDeAsalto.setAdapter(fusiles); //se carga el ArrayList de fusiles recuperado de la API en el RecyclerView


        // Carga del RecyclerView de los subfusiles
        rvSubfusiles = findViewById(R.id.rvSubfusiles);
        rvSubfusiles.setLayoutManager(new LinearLayoutManager(this));
        // Inserción de los subfusiles de la API en alSubfusiles
        // i < 32 API COMPLETA
        for (int i = 17 ; i < 19; i++){

            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  subfusiles.swap((repos))));
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarIdArma((repos.get(0).getPrincipal()))));
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarWeapon((repos.get(0).getWeapon()))));

        }
        rvSubfusiles.setAdapter(subfusiles);//se carga el ArrayList de subfusiles recuperado de la API en el RecyclerView

        // Carga del RecyclerView de las escopetas
        rvEscopetas = findViewById(R.id.rvEscopetas);
        rvEscopetas.setLayoutManager(new LinearLayoutManager(this));
        // Inserción de las escopetas de la API en alEscopetas
        // i < 48 API COMPLETA
        for (int i = 32 ; i < 34; i++){
             AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  escopetas.swap((repos))));
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarIdArma((repos.get(0).getPrincipal()))));
             AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarWeapon((repos.get(0).getWeapon()))));



        }
        rvEscopetas.setAdapter(escopetas);//se carga el ArrayList de escopetas recuperado de la API en el RecyclerView

        // Carga del RecyclerView de las ametralladoras ligeras
        rvAmetralladorasLigeras = findViewById(R.id.rvAmetralladorasLigeras);
        rvAmetralladorasLigeras.setLayoutManager(new LinearLayoutManager(this));
        // Inserción de las ametralladoras ligeras de la API en alAmetralladorasLigeras
        // i < 60 API COMPLETA
        for (int i = 48 ; i < 50; i++){

              AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  ametrelladoraLigera.swap((repos))));
                AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarIdArma((repos.get(0).getPrincipal()))));
                AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarWeapon((repos.get(0).getWeapon()))));

        }
        rvAmetralladorasLigeras.setAdapter(ametrelladoraLigera);//se carga el ArrayList de ametralladoras recuperado de la API en el RecyclerView

        // Carga del RecyclerView de los fusiles de francotirador
        rvFusilesDeFrancotirador = findViewById(R.id.rvFusilesDeFrancotirador);
        rvFusilesDeFrancotirador.setLayoutManager(new LinearLayoutManager(this));
        //Inserción de los fusiles de francotirador de la API en alFusilesDeFrancotirador
        // i < 75 API COMPLETA
        for (int i = 60 ; i < 62; i++){

           AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  fusilesDeFrancotirador.swap((repos))));
             AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarIdArma((repos.get(0).getPrincipal()))));
             AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarWeapon((repos.get(0).getWeapon()))));


        }
        rvFusilesDeFrancotirador.setAdapter(fusilesDeFrancotirador);//se carga el ArrayList de fusiles de francotirador recuperado de la API en el RecyclerView
    }

    /* Cuando esta en pausa termina */

    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

    /* Este método recupera el usuario y la clase actual (guardados en sharedPreferences) */

    private void cargarPreferencias() {

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

       String usuario = preferences.getString("user","usuario vacio");
       String clase = preferences.getString("clase","clases vacia");

        cogerUsuario = new MyAdapter(new ArrayList<>(),this);

        cogerUsuario.usuarioActivo(usuario);
        cogerUsuario.claseActiva(clase);

    }

    /* Método auto-generado por Listener */

    @Override
    public void onListInteraction(String url) {
        Uri webpage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }
}