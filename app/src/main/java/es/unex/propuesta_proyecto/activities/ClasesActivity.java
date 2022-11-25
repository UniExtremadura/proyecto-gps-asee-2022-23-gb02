package es.unex.propuesta_proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDatabaseArmas;
import es.unex.propuesta_proyecto.dao.AppDatabaseClases;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Clases;

public class ClasesActivity extends AppCompatActivity {

    ArrayList<String> alClases = new ArrayList<String>();
    RecyclerView rvClases;
    ImageView bAgregar;
    String usuario,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);

        Bundle parametros = this.getIntent().getExtras();//se recupera el Bundle que viene de la Intent recibida
        if (parametros != null) {
            ImageView perfil = findViewById(R.id.ivUsuario);
            boolean estado = parametros.getBoolean("estado");//se recupera del Bundle el boolean que indica el estado del ImageView
            if (estado) { // True -- visible
                perfil.setVisibility(View.VISIBLE);
            } else { // False -- invisible
                perfil.setVisibility(View.INVISIBLE);
            }
        }
        usuario = parametros.getString("usuario");//se recupera el nombre del usuario del Bundle de la Intent recibida
        pass = parametros.getString("password");


        rvClases = findViewById(R.id.rvClases);
        rvClases.setLayoutManager(new LinearLayoutManager(this));
        bAgregar = findViewById(R.id.ivAgregar);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Clases aux;
               if(AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 1",usuario) == null){
                    aux = new Clases("Clase 1",usuario,0,0);
                    AppDatabaseClases.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
               }
                if(AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 2",usuario) == null){
                    aux = new Clases("Clase 2",usuario,0,0);
                    AppDatabaseClases.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                }
                if(AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 3",usuario) == null){
                    aux = new Clases("Clase 3",usuario,0,0);
                    AppDatabaseClases.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                }
            }
        });
        alClases.add("Clase 1");
        alClases.add("Clase 2");
        alClases.add("Clase 3");
        rvClases.setAdapter(new ClasesAdapter(alClases));
        bAgregar.setOnClickListener(new View.OnClickListener() {
            int i=4;
            @Override
            public void onClick(View v) {
                alClases.add("Clase "+i);
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Clases aux;
                        if(AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase " + i,usuario) == null){
                            aux = new Clases("Clase "+i,usuario,0,0);
                            AppDatabaseClases.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                        }
                    }
                });
                if (i == 10) {
                    bAgregar.setVisibility(View.INVISIBLE);
                }
                i++;
                rvClases.setAdapter(new ClasesAdapter(alClases));
            }
        });
        // Acceso a clases
    }//Fin onCreate()

    public void perfilUsuario(View view){
        Intent perfil = new Intent(this, ActualizarCuentaActivity.class);
        perfil.putExtra("usuario",usuario);
        perfil.putExtra("password",pass);
        startActivity(perfil);
    }
}