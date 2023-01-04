package es.unex.propuesta_proyecto.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;

import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.api.ReposNetworkLoaderRunnable;

public class ArmasSecundariasActivity extends AppCompatActivity  implements MyAdapter.OnListInteractionListener {

    RecyclerView rvPistolas;
    RecyclerView rvCuerpoACuerpo;

    private MyAdapter pistolas, cuerpo;
    private MyAdapter cogerUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armas_secundarias);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Armas Secundarias");
        }

        pistolas = new MyAdapter(new ArrayList<>(), this);
        cuerpo = new MyAdapter(new ArrayList<>(), this);

        cargarPreferencias();

        rvPistolas = findViewById(R.id.rvPistolas);
        rvPistolas.setLayoutManager(new LinearLayoutManager(this));
        //i < 22
        for (int i = 19 ; i < 21; i++){
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  pistolas.swap((repos))));
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarIdArma((repos.get(0).getPrincipal()))));
        }
        rvPistolas.setAdapter(pistolas);

        rvCuerpoACuerpo = findViewById(R.id.rvCuerpoACuerpo);
        rvCuerpoACuerpo.setLayoutManager(new LinearLayoutManager(this));
        //i < 24
        for (int i = 22 ; i < 24; i++){
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cuerpo.swap((repos))));
            AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(i,(repos) ->  cogerUsuario.pasarIdArma((repos.get(0).getPrincipal()))));
        }
        rvCuerpoACuerpo.setAdapter(cuerpo);

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