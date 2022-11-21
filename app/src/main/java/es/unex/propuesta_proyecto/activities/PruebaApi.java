package es.unex.propuesta_proyecto.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.api.ClaseApi;
import es.unex.propuesta_proyecto.api.ReposNetworkLoaderRunnable;
import es.unex.propuesta_proyecto.dao.AppDatabase;
import es.unex.propuesta_proyecto.model.Clases;
import es.unex.propuesta_proyecto.model.Repo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PruebaApi extends AppCompatActivity {

    TextView tvClase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);

        tvClase = findViewById(R.id.apiTest);
        
        List<Clases> listaClases;

        AppDatabase appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbPruebas"
        ).allowMainThreadQueries().build();

        // appDatabase.daoJuego().insertarClase(new Clases("Arma David","Metralleta","Mortal",10,9,6,2,4,3,"Buena","Lenta"));
        // appDatabase.daoJuego().insertarClase(new Clases("Arma Carlos","Lanzallamas","Mortal",10,9,6,2,4,3,"Buena","Lenta"));
        // appDatabase.daoJuego().insertarClase(new Clases("Arma Adrián","K.O.","Mortal",10,9,6,2,4,3,"Buena","Lenta"));
        // appDatabase.daoJuego().insertarClase(new Clases("Arma Javier","Puñetazo","Mortal",10,9,6,2,4,3,"Buena","Lenta"));

        listaClases = appDatabase.daoJuego().obtenerClases();

        String txt = " ";
        for (int i = 0; i < listaClases.size(); i++){
            txt = txt + "Clase" +i+ " = " + listaClases.get(i).getName() +", "+ listaClases.get(i).getType()+"\n";
        }


        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(1,(repos) -> tvClase.setText(repos.get(0).getName())));

    }
}
