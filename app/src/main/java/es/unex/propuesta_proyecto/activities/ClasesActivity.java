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
import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDatabaseArmas;
import es.unex.propuesta_proyecto.dao.AppDatabaseClases;
import es.unex.propuesta_proyecto.dao.AppDatabaseUsuarios;
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

        usuario = parametros.getString("usuario");//se recupera el nombre del usuario del Bundle de la Intent recibida
        pass = parametros.getString("password");

        rvClases = findViewById(R.id.rvClases);
        rvClases.setLayoutManager(new LinearLayoutManager(this));
        bAgregar = findViewById(R.id.ivAgregar);

        /* Añade tres clases por defecto, que siempre le saldrán a todos los usuarios */

        AppExecutors.getInstance().diskIO().execute(() -> {
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
        });

        alClases.add("Clase 1");
        alClases.add("Clase 2");
        alClases.add("Clase 3");
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Clases> clasesUser = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClasesUsuario(usuario);
                for(int i = 3; i < clasesUser.size(); i++){
                    if(clasesUser.get(i).getNombre().equals("Clase "+(i+1))){
                        alClases.add("Clase "+(i+1));
                    }
                }
            }
        });
        rvClases.setAdapter(new ClasesAdapter(alClases));

        /* Este método se encarga de mostrar hasta 10 clases, es decir cuando el usuario presiona el botón "+", se encarga de ir añadiendo clases, hasta un máximo de 10.
        * Para ello comprueba en la base de datos de clases que ese mismo Usuario pueda seguir creando clases */

        bAgregar.setOnClickListener(v -> {
            AppExecutors.getInstance().diskIO().execute(() -> {
                List<Clases> clasesTotales = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClasesUsuario(usuario);
                int numClase = clasesTotales.size();
                    Clases aux;
                     if(numClase > 8){
                         bAgregar.setVisibility(View.INVISIBLE);
                     }
                    if(AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase " + (numClase+1),usuario) == null){
                        alClases.add("Clase "+(numClase+1));
                        aux = new Clases("Clase "+(numClase+1),usuario,0,0);
                        AppDatabaseClases.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                    }
                });
            rvClases.setAdapter(new ClasesAdapter(alClases)); // Los añade al recyclerView.
        });
    }

    /* Boton de perfil de usuario para poder acceder a editar la contraseña */

    public void perfilUsuario(View view){
        Intent perfil = new Intent(this, ActualizarCuentaActivity.class);
        perfil.putExtra("usuario",usuario);
        perfil.putExtra("password",pass);
        startActivity(perfil);
    }
}