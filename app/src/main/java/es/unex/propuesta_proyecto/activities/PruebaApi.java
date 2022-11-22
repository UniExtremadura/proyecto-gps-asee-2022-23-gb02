package es.unex.propuesta_proyecto.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.api.ReposNetworkLoaderRunnable;
import es.unex.propuesta_proyecto.dao.AppDatabase;
import es.unex.propuesta_proyecto.model.Armas;

public class PruebaApi extends AppCompatActivity {

    TextView tvClase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);

        tvClase = findViewById(R.id.apiTest);
        
        //List<Armas> listaClases;

      /*  AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase database = AppDatabase.getInstance(PruebaApi.this);
                Armas a = new Armas("ARMA DAVID","MATADORA","FUEGO",100,2,3,1,3,4,"NO","SI");
                database.daoJuego().insertarClase(a);
                // No se pueden actualizar hilos de la vista desde un hilo que no sea el principal
                // Para ello se hace uso de runOnUiThread(() -> mAdapter.add(item));
                runOnUiThread(() -> tvClase.setText(a.getName()));
            }
        }); */

        // Llamada a la API:
        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(1,(repos) -> tvClase.setText(repos.get(0).getName())));
    }

}
