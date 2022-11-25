package es.unex.propuesta_proyecto.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDatabaseArmas;
import es.unex.propuesta_proyecto.dao.AppDatabaseClases;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Clases;

public class AccesoriosActivity extends AppCompatActivity implements MyAdapter.OnListInteractionListener {

    Spinner sBocacha;
    Spinner sCañon;
    Spinner sLaser;
    Spinner sMira;
    Spinner sCulata;
    Spinner sAcople;
    Spinner sMunicion;
    Spinner sEmpuñaduraTrasera;
    Spinner sVentaja;

    ProgressBar pbPrecisionArma;
    ProgressBar pbDanoArma;
    ProgressBar pbAlcanceArma;
    ProgressBar pbCadenciaArma;
    ProgressBar pbMovilidadArma;
    ProgressBar pbControlArma;

    private MyAdapter cogerUsuario;
    private String usuarioActual, claseActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesorios);

        pbPrecisionArma = findViewById(R.id.pbPrecisionArmaAccesorios);
        pbDanoArma = findViewById(R.id.pbDañoArmaAccesorios);
        pbAlcanceArma = findViewById(R.id.pbAlcanceArmaAccesorios);
        pbCadenciaArma = findViewById(R.id.pbCadenciaArmaAccesorios);
        pbMovilidadArma = findViewById(R.id.pbMovilidadArmaAccesorios);
        pbControlArma = findViewById(R.id.pbControlArmaAccesorios);

        cargarPreferencias();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase(claseActual,usuarioActual);
                List<Armas> arma = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioActual);
                if(arma != null && clase != null){
                    for(int i = 0; i < arma.size(); i++){
                        if(arma.get(i).getIdClase() == clase.getId()){ // Arma actual que esta empleando
                            Armas armaActual = arma.get(i);
                            pbPrecisionArma.setProgress(armaActual.getAccuracy());pbDanoArma.setProgress(armaActual.getDamage());
                            pbAlcanceArma.setProgress(armaActual.getRange());pbCadenciaArma.setProgress(armaActual.getFire_rate());
                            pbMovilidadArma.setProgress(armaActual.getMobility());pbControlArma.setProgress(armaActual.getControl());
                        }
                    }
                } else{
                    Log.d("ARMA NULA",arma.toString());
                }
            }
        });

        // Carga del Spinner de las bocachas
        sBocacha = findViewById(R.id.sBocacha);
        sBocacha.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.bocachas, android.R.layout.simple_spinner_item));

        // Carga del Spinner de los cañones
        sCañon = findViewById(R.id.sCañon);
        sCañon.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.cañon, android.R.layout.simple_spinner_item));

        // Carga del Spinner de los laser
        sLaser = findViewById(R.id.sLaser);
        sLaser.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.laser, android.R.layout.simple_spinner_item));

        // Carga del Spinner de las miras
        sMira = findViewById(R.id.sMira);
        sMira.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.mira, android.R.layout.simple_spinner_item));

        // Carga del Spinner de las culatas
        sCulata = findViewById(R.id.sCulata);
        sCulata.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.culata, android.R.layout.simple_spinner_item));

        // Carga del Spinner de los acoples
        sAcople = findViewById(R.id.sAcople);
        sAcople.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.acople, android.R.layout.simple_spinner_item));

        // Carga del Spinner de las municiones
        sMunicion = findViewById(R.id.sMunicion);
        sMunicion.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.municion, android.R.layout.simple_spinner_item));

        // Carga del Spinner de las empuñaduras traseras
        sEmpuñaduraTrasera = findViewById(R.id.sEmpuñaduraTrasera);
        sEmpuñaduraTrasera.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.empuñaduraTrasera, android.R.layout.simple_spinner_item));

        // Carga del Spinner de las ventajas
        sVentaja = findViewById(R.id.sVentaja);
        sVentaja.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.ventaja, android.R.layout.simple_spinner_item));


    }

    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String usuario = preferences.getString("user","usuario vacio");
        String clase = preferences.getString("clase","clases vacia");

        this.usuarioActual = usuario;
        this.claseActual = clase;

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