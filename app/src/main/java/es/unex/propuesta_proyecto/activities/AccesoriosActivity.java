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
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDataBase;
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
    private ArrayList<String> almacenSpinners = new ArrayList<>();
    private int idArma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesorios);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Accesorios");
        }

        pbPrecisionArma = findViewById(R.id.pbPrecisionArmaAccesorios);
        pbDanoArma = findViewById(R.id.pbDañoArmaAccesorios);
        pbAlcanceArma = findViewById(R.id.pbAlcanceArmaAccesorios);
        pbCadenciaArma = findViewById(R.id.pbCadenciaArmaAccesorios);
        pbMovilidadArma = findViewById(R.id.pbMovilidadArmaAccesorios);
        pbControlArma = findViewById(R.id.pbControlArmaAccesorios);
        bAplicar = findViewById(R.id.bActualizarAccesorios);

        cargarPreferencias();

        AppExecutors.getInstance().diskIO().execute(() -> {
            Clases clase = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase(claseActual,usuarioActual);
            List<Armas> arma = AppDataBase.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioActual);
            if(arma != null && clase != null){
                for(int i = 0; i < arma.size(); i++){
                    if(arma.get(i).getIdClase() == clase.getId()){
                        if(arma.get(i).getPrincipal() == 1){
                            Armas armaActual = arma.get(i);
                            idArma = armaActual.getId();
                            List<Accesorio> accesorio = AppDataBase.getInstance(getApplicationContext()).daoAccesorios().obtenerAccesoriosTodosUsuario(idArma);
                            if(accesorio.size() != 0){
                                for(int j = 0; accesorio.size() > j; j++){
                                    switch (accesorio.get(j).getNombre()){
                                        case "Bocacha +":  sBocacha.setSelection(1); break;
                                        case "Bocacha ++": sBocacha.setSelection(2); break;
                                        case "Cañon +": sCañon.setSelection(1); break;
                                        case "Cañon ++": sCañon.setSelection(2);  break;
                                        case "Mira +": sMira.setSelection(1); break;
                                        case "Mira ++": sMira.setSelection(2); break;
                                        case "Laser +": sLaser.setSelection(1); break;
                                        case "Laser ++": sLaser.setSelection(2); break;
                                        case "Culata +": sCulata.setSelection(1); break;
                                        case "Culata ++": sCulata.setSelection(2); break;
                                        default: sBocacha.setSelection(0);sCañon.setSelection(0);
                                            sMira.setSelection(0); sLaser.setSelection(0); sCulata.setSelection(0);
                                            break;
                                    }
                                    actualizarCamposArma(armaActual);
                                }
                            } else {
                                actualizarCamposArma(armaActual);
                            }
                        }
                    }
                }
            } else{
                Log.d("ARMA NULA",arma.toString());
            }
        });

        sBocacha = findViewById(R.id.sBocacha);
        sBocacha.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.bocachas, android.R.layout.simple_spinner_item));

        sCañon = findViewById(R.id.sCañon);
        sCañon.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.cañon, android.R.layout.simple_spinner_item));

        sLaser = findViewById(R.id.sLaser);
        sLaser.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.laser, android.R.layout.simple_spinner_item));

        sMira = findViewById(R.id.sMira);
        sMira.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.mira, android.R.layout.simple_spinner_item));

        sCulata = findViewById(R.id.sCulata);
        sCulata.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.culata, android.R.layout.simple_spinner_item));

        bAplicar.setOnClickListener(v -> {

            String obtenerBocacha = sBocacha.getSelectedItem().toString();
            String obtenerCanon = sCañon.getSelectedItem().toString();
            String obtenerLaser = sLaser.getSelectedItem().toString();
            String obtenerMira = sMira.getSelectedItem().toString();
            String obtenerCulata = sCulata.getSelectedItem().toString();

            almacenSpinners.add(obtenerBocacha);
            almacenSpinners.add(obtenerCanon);
            almacenSpinners.add(obtenerLaser);
            almacenSpinners.add(obtenerMira);
            almacenSpinners.add(obtenerCulata);

            strategyAccesorios(almacenSpinners);
            finish();
        });
    }

    private void comprobarAccesorio(String nombreAccesorio){
        Accesorio comprobarAccesorio = AppDataBase.getInstance(getApplicationContext()).daoAccesorios().obtenerAccesorioUsuario(idArma, nombreAccesorio);
        Armas armaActual = AppDataBase.getInstance(getApplicationContext()).daoJuego().obtenerArmaPorId(idArma);
        if (comprobarAccesorio != null) {
            AppDataBase.getInstance(getApplicationContext()).daoJuego().actualizarArmaPorId(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),
                    armaActual.getAccuracy()-comprobarAccesorio.getModPrecision(),
                    armaActual.getDamage()-comprobarAccesorio.getModDaño(),armaActual.getRange()-comprobarAccesorio.getModAlcance(),
                    armaActual.getFire_rate()-comprobarAccesorio.getModCadencia(),armaActual.getMobility()-comprobarAccesorio.getModMovilidad()
                    ,armaActual.getControl()-comprobarAccesorio.getModControl(),armaActual.getId(),armaActual.getPrincipal());
            AppDataBase.getInstance(getApplicationContext()).daoAccesorios().borrarAccesorio(idArma, comprobarAccesorio.getNombre());
        }
        actualizarCamposArma(armaActual);
    }

    private void strategyAccesorios(ArrayList<String> almacenSpinners){
        String nomAccesorio;
        for(int i = 0; i < almacenSpinners.size(); i++){
            nomAccesorio = almacenSpinners.get(i);

            if(nomAccesorio.equals("- B -") || nomAccesorio.equals("- C -") || nomAccesorio.equals("- L -")|| nomAccesorio.equals("- M -")|| nomAccesorio.equals("- T -")){
                String finalNomAccesorio = nomAccesorio;
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        switch (finalNomAccesorio) {
                            case "- B -": {
                                comprobarAccesorio("Bocacha +");
                                break;
                            }
                            case "- C -": {
                                comprobarAccesorio("Cañon +");
                                break;
                            }
                            case "- L -": {
                                comprobarAccesorio("Laser +");
                                break;
                            }
                            case "- M -" : {
                                comprobarAccesorio("Mira +");
                                break;
                            }
                            case "- T -": {
                                comprobarAccesorio("Culata +");
                                break;
                            }
                        }
                    }
                });
            }

        if(nomAccesorio.equals("Bocacha +")){
            String finalNomAccesorio1 = nomAccesorio;
            AppExecutors.getInstance().diskIO().execute(() -> {
                Accesorio accesorioExistente = AppDataBase.getInstance(getApplicationContext()).daoAccesorios().obtenerAccesorioUsuario(idArma, finalNomAccesorio1);
                Armas armaActual = AppDataBase.getInstance(getApplicationContext()).daoJuego().obtenerArmaPorId(idArma);
                Accesorio accesorio = new Accesorio(finalNomAccesorio1, Accesorio.TipoAccesorio.BOCACHA,12,14,-4,12,-4,-4,idArma);
                if(accesorioExistente == null ){
                AppDataBase.getInstance(getApplicationContext()).daoAccesorios().insertarAccesorio(accesorio);
                    AppDataBase.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy()+accesorio.getModPrecision(),
                            armaActual.getDamage()+accesorio.getModDaño(),armaActual.getRange()+accesorio.getModAlcance(),armaActual.getFire_rate()+accesorio.getModCadencia(),armaActual.getMobility()+accesorio.getModMovilidad(),
                            armaActual.getControl()+accesorio.getModControl(),idArma,armaActual.getIdClase(),armaActual.getPrincipal());
               }
            });
         }
        else if(nomAccesorio.equals("Cañon +")){
            String finalNomAccesorio2 = nomAccesorio;
            AppExecutors.getInstance().diskIO().execute(() -> {
                Accesorio accesorioExistente = AppDataBase.getInstance(getApplicationContext()).daoAccesorios().obtenerAccesorioUsuario(idArma, finalNomAccesorio2);
                Armas armaActual = AppDataBase.getInstance(getApplicationContext()).daoJuego().obtenerArmaPorId(idArma);
                Accesorio accesorio = new Accesorio(finalNomAccesorio2, Accesorio.TipoAccesorio.CAÑON,-7,-7,8,12,-12,1,idArma);
                if(accesorioExistente == null ){
                    AppDataBase.getInstance(getApplicationContext()).daoAccesorios().insertarAccesorio(accesorio);
                    AppDataBase.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy()+accesorio.getModPrecision(),
                            armaActual.getDamage()+accesorio.getModDaño(),armaActual.getRange()+accesorio.getModAlcance(),armaActual.getFire_rate()+accesorio.getModCadencia(),armaActual.getMobility()+accesorio.getModMovilidad(),
                            armaActual.getControl()+accesorio.getModControl(),idArma,armaActual.getIdClase(),armaActual.getPrincipal());
                }
            });
        }
        else if(nomAccesorio.equals("Laser +")){
            String finalNomAccesorio3 = nomAccesorio;
            AppExecutors.getInstance().diskIO().execute(() -> {
                Accesorio accesorioExistente = AppDataBase.getInstance(getApplicationContext()).daoAccesorios().obtenerAccesorioUsuario(idArma, finalNomAccesorio3);
                Armas armaActual = AppDataBase.getInstance(getApplicationContext()).daoJuego().obtenerArmaPorId(idArma);
                Accesorio accesorio = new Accesorio(finalNomAccesorio3, Accesorio.TipoAccesorio.CAÑON,1,4,-9,5,2,11,idArma);
                if(accesorioExistente == null ){
                    AppDataBase.getInstance(getApplicationContext()).daoAccesorios().insertarAccesorio(accesorio);
                    AppDataBase.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy()+accesorio.getModPrecision(),
                            armaActual.getDamage()+accesorio.getModDaño(),armaActual.getRange()+accesorio.getModAlcance(),armaActual.getFire_rate()+accesorio.getModCadencia(),armaActual.getMobility()+accesorio.getModMovilidad(),
                            armaActual.getControl()+accesorio.getModControl(),idArma,armaActual.getIdClase(),armaActual.getPrincipal());
                }
            });
        }
        else if(nomAccesorio.equals("Mira +")){
            String finalNomAccesorio4 = nomAccesorio;
            AppExecutors.getInstance().diskIO().execute(() -> {
                Accesorio accesorioExistente = AppDataBase.getInstance(getApplicationContext()).daoAccesorios().obtenerAccesorioUsuario(idArma, finalNomAccesorio4);
                Armas armaActual = AppDataBase.getInstance(getApplicationContext()).daoJuego().obtenerArmaPorId(idArma);
                Accesorio accesorio = new Accesorio(finalNomAccesorio4, Accesorio.TipoAccesorio.MIRA,-9,7,4,11,-11,4,idArma);
                if(accesorioExistente == null ){
                    AppDataBase.getInstance(getApplicationContext()).daoAccesorios().insertarAccesorio(accesorio);
                    AppDataBase.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy()+accesorio.getModPrecision(),
                            armaActual.getDamage()+accesorio.getModDaño(),armaActual.getRange()+accesorio.getModAlcance(),armaActual.getFire_rate()+accesorio.getModCadencia(),armaActual.getMobility()+accesorio.getModMovilidad(),
                            armaActual.getControl()+accesorio.getModControl(),idArma,armaActual.getIdClase(),armaActual.getPrincipal());
                }
            });
        }
        else if(nomAccesorio.equals("Culata +")){
            String finalNomAccesorio5 = nomAccesorio;
            AppExecutors.getInstance().diskIO().execute(() -> {
                Accesorio accesorioExistente = AppDataBase.getInstance(getApplicationContext()).daoAccesorios().obtenerAccesorioUsuario(idArma, finalNomAccesorio5);
                Armas armaActual = AppDataBase.getInstance(getApplicationContext()).daoJuego().obtenerArmaPorId(idArma);
                Accesorio accesorio = new Accesorio(finalNomAccesorio5, Accesorio.TipoAccesorio.CULATA,-2,4,4,11,1,1,idArma);
                if(accesorioExistente == null ){
                    AppDataBase.getInstance(getApplicationContext()).daoAccesorios().insertarAccesorio(accesorio);
                    AppDataBase.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy()+accesorio.getModPrecision(),
                            armaActual.getDamage()+accesorio.getModDaño(),armaActual.getRange()+accesorio.getModAlcance(),armaActual.getFire_rate()+accesorio.getModCadencia(),armaActual.getMobility()+accesorio.getModMovilidad(),
                            armaActual.getControl()+accesorio.getModControl(),idArma,armaActual.getIdClase(),armaActual.getPrincipal());
                }
            });
            }
        }
    }

    private void actualizarCamposArma(Armas arma) {
        pbPrecisionArma.setProgress(arma.getAccuracy());pbDanoArma.setProgress(arma.getDamage());
        pbAlcanceArma.setProgress(arma.getRange());pbCadenciaArma.setProgress(arma.getFire_rate());
        pbMovilidadArma.setProgress(arma.getMobility());pbControlArma.setProgress(arma.getControl());
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