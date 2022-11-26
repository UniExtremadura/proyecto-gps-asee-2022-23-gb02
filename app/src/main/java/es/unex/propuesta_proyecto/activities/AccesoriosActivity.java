package es.unex.propuesta_proyecto.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.api.ReposNetworkLoaderRunnable;
import es.unex.propuesta_proyecto.dao.AppDatabaseAccesorios;
import es.unex.propuesta_proyecto.dao.AppDatabaseArmas;
import es.unex.propuesta_proyecto.dao.AppDatabaseClases;
import es.unex.propuesta_proyecto.model.Accesorio;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Clases;

public class AccesoriosActivity extends AppCompatActivity implements MyAdapter.OnListInteractionListener {

    Spinner sBocacha;
    Spinner sCañon;
    Spinner sLaser;
    Spinner sMira;
    Spinner sCulata;


    ProgressBar pbPrecisionArma;
    ProgressBar pbDanoArma;
    ProgressBar pbAlcanceArma;
    ProgressBar pbCadenciaArma;
    ProgressBar pbMovilidadArma;
    ProgressBar pbControlArma;
    Button bAplicar;

    private MyAdapter cogerUsuario;
    private String usuarioActual, claseActual;

    private int idArma;

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
        bAplicar = findViewById(R.id.bActualizarAccesorios);

        cargarPreferencias();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase(claseActual,usuarioActual);
                List<Armas> arma = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioActual);
                Accesorio accesorio = AppDatabaseAccesorios.getInstance(getApplicationContext()).daoAccesorios().obtenerAccesoriosUsuario(idArma);
                if(arma != null && clase != null){
                    for(int i = 0; i < arma.size(); i++){
                        if(arma.get(i).getIdClase() == clase.getId()){ // Arma actual que esta empleando
                            Armas armaActual = arma.get(i);
                            idArma = armaActual.getId();
                            if(accesorio != null){
                                Log.d("Cadencia",String.valueOf(armaActual.getFire_rate()));
                                actualizarCamposArmaAcesorrios(armaActual,accesorio);
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(arma.get(i).getName(),arma.get(i).getType(),arma.get(i).getSubtype(),arma.get(i).getAccuracy()+accesorio.getModPrecision(),arma.get(i).getDamage()+accesorio.getModDaño(),arma.get(i).getRange()+accesorio
                                        .getModAlcance(), arma.get(i).getFire_rate()+accesorio.getModCadencia(),arma.get(i).getMobility()+accesorio.getModMovilidad(),arma.get(i).getControl()+accesorio.getModControl(),arma.get(i).getPrincipal(), armaActual.getId(),arma.get(i).getIdClase(), armaActual.getWeapon());
                            } else {
                                pbPrecisionArma.setProgress(armaActual.getAccuracy());pbDanoArma.setProgress(armaActual.getDamage());
                                pbAlcanceArma.setProgress(armaActual.getRange());pbCadenciaArma.setProgress(armaActual.getFire_rate());
                                pbMovilidadArma.setProgress(armaActual.getMobility());pbControlArma.setProgress(armaActual.getControl());
                            }
                        }
                    }
                } else{
                    Log.d("ARMA NULA",arma.toString());
                }
            }
        });

        // Carga el Spinner de las bocachas
        sBocacha = findViewById(R.id.sBocacha);
        sBocacha.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.bocachas, android.R.layout.simple_spinner_item));

        // Carga el Spinner de los cañones
        sCañon = findViewById(R.id.sCañon);
        sCañon.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.cañon, android.R.layout.simple_spinner_item));

        // Carga el Spinner de los laser
        sLaser = findViewById(R.id.sLaser);
        sLaser.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.laser, android.R.layout.simple_spinner_item));

        // Carga el Spinner de las miras
        sMira = findViewById(R.id.sMira);
        sMira.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.mira, android.R.layout.simple_spinner_item));

        // Carga el Spinner de las culatas
        sCulata = findViewById(R.id.sCulata);
        sCulata.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.culata, android.R.layout.simple_spinner_item));

        //sCulata.setSelection(2);

        bAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String obtenerBocacha = sBocacha.getSelectedItem().toString();
                strategyAccesorios(obtenerBocacha);
                String obtenerCanon = sCañon.getSelectedItem().toString();
                strategyAccesorios(obtenerCanon);
                String obtenerLaser = sLaser.getSelectedItem().toString();
                strategyAccesorios(obtenerLaser);
                String obtenerMira = sMira.getSelectedItem().toString();
                strategyAccesorios(obtenerMira);
                String obtenerCulata = sCulata.getSelectedItem().toString();
                strategyAccesorios(obtenerCulata);

            }
        });

    }

    private void strategyAccesorios(String nomAccesorio){
         if(nomAccesorio.equals("Bocacha +")){
             AppExecutors.getInstance().networkIO().execute(new Runnable() {
                 @Override
                 public void run() {
                     Accesorio accesorioExistente = AppDatabaseAccesorios.getInstance(getApplicationContext()).daoAccesorios().obtenerAccesoriosUsuario(idArma);
                     Armas armaActual = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmaPorId(idArma);
                     Accesorio accesorio = new Accesorio(nomAccesorio, Accesorio.TipoAccesorio.BOCACHA,10,10,10,10,10,10,idArma);
                     if(accesorioExistente == null ){
                     AppDatabaseAccesorios.getInstance(getApplicationContext()).daoAccesorios().insertarAccesorio(accesorio);
                         actualizarArmaConAccesoriosBD(armaActual,accesorio);
                         actualizarCamposArmaAcesorrios(armaActual,accesorio);
                    } else {
                         actualizarCamposArmaAcesorrios(armaActual,accesorio);
                     }
                 }
             });
         } else if(nomAccesorio.equals("Bocacha ++")){

        }
        if(nomAccesorio.equals("Cañon +")){

        } else if(nomAccesorio.equals("Cañon ++")){

        }
        if(nomAccesorio.equals("Laser +")){

        } else if(nomAccesorio.equals("Laser ++")){

        }
        if(nomAccesorio.equals("Mira +")){

        } else if(nomAccesorio.equals("Mira ++")){

        }
        if(nomAccesorio.equals("Culata +")){

        } else if(nomAccesorio.equals("Culata ++")){

        }
    }

    /* Este método actualiza los campos de las armas primarias con accesorios, modificandolas en el room y en el layout */

    private void actualizarCamposArmaAcesorrios(Armas arma,Accesorio accesorio){
        pbPrecisionArma.setProgress(arma.getAccuracy()+accesorio.getModPrecision());pbDanoArma.setProgress(arma.getDamage()+accesorio.getModDaño());
        pbAlcanceArma.setProgress(arma.getRange()+accesorio.getModAlcance());pbCadenciaArma.setProgress(arma.getFire_rate()+accesorio.getModCadencia());
        pbMovilidadArma.setProgress(arma.getMobility()+accesorio.getModMovilidad());pbControlArma.setProgress(arma.getControl()+accesorio.getModControl());
    }

    private void actualizarArmaConAccesoriosBD(Armas arma,Accesorio accesorio){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(arma.getName(),arma.getType(),arma.getSubtype(),arma.getAccuracy()+accesorio.getModPrecision(),
                        arma.getDamage()+accesorio.getModDaño(),arma.getRange()+accesorio.getModAlcance(),arma.getFire_rate()+accesorio.getModCadencia(),arma.getMobility()+accesorio.getModMovilidad(),
                        arma.getControl()+accesorio.getModControl(),idArma,arma.getIdClase(),arma.getPrincipal(), arma.getWeapon());
            }
        });
    }

    /* Este recupera con la función SharedPreferences el usuario que está loggeado en este instante y la clase a la que ha accedido en el mismo instante */

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