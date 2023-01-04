package es.unex.propuesta_proyecto.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.squareup.picasso.Picasso;
import java.util.List;
import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppContainer;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.api.ArmaNetworkDataSource;
import es.unex.propuesta_proyecto.api.ArmasRepository;
import es.unex.propuesta_proyecto.api.ArmasViewModel;
import es.unex.propuesta_proyecto.dao.AppDataBase;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Clases;
import es.unex.propuesta_proyecto.model.MyApplication;
import es.unex.propuesta_proyecto.model.RepoArmas;

public class DetalleClaseActivity extends AppCompatActivity {

    private ArmasRepository mRepository;
    private MyAdapter mAdapter;

    Button bPrimaria;
    ImageView imgPrimaria, imgSecundaria,bBorrar;
    String usuarioRecuperado, contrasenaRecuperada;

    TextView tvNameArma, nombreClase;
    ProgressBar tvPrecision,tvDano,tvAlcance,tvCadencia,tvMovilidad,tvControl;

    TextView tvNameArmaSec;
    ProgressBar tvPrecisionSec,tvDanoSec,tvAlcanceSec,tvCadenciaSec,tvMovilidadSec,tvControlSec;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarPreferencias();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Información Armas");
        }

        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null) {
            String valor = parametros.getString("className");
            setContentView(R.layout.activity_detalle_clase1);
            nombreClase = findViewById(R.id.etNombreClase);
            nombreClase.setText(valor);
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @SuppressLint("WrongViewCast")
                @Override
                public void run() {
                    int claseActual = 0;
                    List<Clases> clasesUsuario = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClasesUsuario(usuarioRecuperado);
                    if (clasesUsuario != null) {
                        for (int i = 0; i < clasesUsuario.size(); i++) {
                            if(clasesUsuario.get(i).getNombre().equals(valor)){
                                claseActual = clasesUsuario.get(i).getId();
                            }
                        }
                    }
                    int idArmaPrin, idArmaSec;
                    Armas aPrin, aSec;
                    idArmaPrin = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(claseActual, 1);
                    idArmaSec = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(claseActual, 0);
                    AppDataBase.getInstance(getApplicationContext()).daoClases().actualizarIdArmas(idArmaPrin, idArmaSec, claseActual);
                    aPrin = AppDataBase.getInstance(getApplicationContext()).daoJuego().obtenerArmaPorId(idArmaPrin);
                    aSec = AppDataBase.getInstance(getApplicationContext()).daoJuego().obtenerArmaPorId(idArmaSec);
                    tvNameArma = findViewById(R.id.tvNombre1);
                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria1);
                    tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal1);tvDano = findViewById(R.id.pbDañoArmaPrincipal1);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal1);tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal1);
                    tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal1);tvControl = findViewById(R.id.pbControlArmaPrincipal1);
                    tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria1);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria1);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria1);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria1);
                    tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria1);tvControlSec = findViewById(R.id.pbControlArmaSecundaria1);
                    actualizarCamposArmasPrincipales(aPrin);
                    actualizarCamposArmasSecundarias(aSec);

                    imgPrimaria = findViewById(R.id.ivArma1);
                    imgSecundaria = findViewById(R.id.ivArmaSecundaria1);

                    AppExecutors.getInstance().mainThread().execute(() -> actualizarImgPrimaria(aPrin));
                    AppExecutors.getInstance().mainThread().execute(() -> actualizarImgSecundaria(aSec));
                }
            });

        }

        bBorrar = findViewById(R.id.ivEliminarClase);
        bBorrar.setOnClickListener(v -> {
            AppExecutors.getInstance().diskIO().execute(() -> {
                String nombreBorrar = parametros.getString("className");
                Clases clase = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase(nombreBorrar,usuarioRecuperado);
                AppDataBase.getInstance(getApplicationContext()).daoJuego().borrarArmaPorClaseyNombre(clase.getId(),usuarioRecuperado);
                if(clase.getNombre().equals("Clase 1") || clase.getNombre().equals("Clase 2") || clase.getNombre().equals("Clase 3")){
                    int idArmaPrinc, idArmaSec;
                    Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuarioRecuperado, clase.getId(), 1);
                    Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuarioRecuperado, clase.getId(), 0);
                    AppDataBase.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                    AppDataBase.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                    idArmaPrinc = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(clase.getId(), 1);
                    idArmaSec = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(clase.getId(), 0);
                    AppDataBase.getInstance(getApplicationContext()).daoClases().actualizarIdArmas(idArmaPrinc, idArmaSec, clase.getId());
                } else{
                    AppDataBase.getInstance(getApplicationContext()).daoClases().borrarClase(clase.getNombre(), usuarioRecuperado);
                }
            });
            finish();
        });

        bPrimaria = findViewById(R.id.bAccesoriosArmaPrincipal);
        bPrimaria.setOnClickListener(v -> {
            Intent accesorios = new Intent(DetalleClaseActivity.this,AccesoriosActivity.class);
            startActivity(accesorios);
        });

        mRepository = ArmasRepository.getInstance(AppDataBase.getInstance(this).daoJuego(), ArmaNetworkDataSource.getInstance());
        mRepository.getCurrentArma().observe(this, this::onReposLoaded);

        AppContainer appContainer = ((MyApplication) getApplication()).appContainer;

        ArmasViewModel mArmasViewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) appContainer.factory).get(ArmasViewModel.class);

        mArmasViewModel.getRepos().observe(this, repos -> mAdapter.swap(repos));

    }

    public void onReposLoaded(List<RepoArmas> armas){
        runOnUiThread(() -> mAdapter.swap(armas));
    }

    public void actualizarImgPrimaria(Armas arma){
        switch (arma.getName()) {
            case "Ak-47":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_ak-47.webp").into(imgPrimaria);
                break;
            case "Aug":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_aug.webp").into(imgPrimaria);
                break;
            case "Fn Scar L7":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_fn-scar-17.webp").into(imgPrimaria);
                break;
            case "M4a1":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_m4a1.webp").into(imgPrimaria);
                break;
            case "725":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_725.webp").into(imgPrimaria);
                break;
            case "Model 680":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_model-680.webp").into(imgPrimaria);
                break;
            case "R9-O":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_r9-0-shotgun.webp").into(imgPrimaria);
                break;
            case "Origin 12 Shotgun":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_origin-12-shotgun.webp").into(imgPrimaria);
                break;
            case "Dragunov":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_dragunov.webp").into(imgPrimaria);
                break;
            case "Ebr-14":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_ebr-14.webp").into(imgPrimaria);
                break;
            case "Hdr":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_hdr.webp").into(imgPrimaria);
                break;
            case "Kar98k":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_kar98k.webp").into(imgPrimaria);
                break;
            case "M634":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_mg34.webp").into(imgPrimaria);
                break;
            case "M91":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_m91.webp").into(imgPrimaria);
                break;
            case "Pkm":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_pkm.webp").into(imgPrimaria);
                break;
            case "Mp5":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_mp5.webp").into(imgPrimaria);
                break;
            case "Mp7":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_mp7.webp").into(imgPrimaria);
                break;
            case "P90":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_p90.webp").into(imgPrimaria);
                break;
            case "Uzi":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_uzi.webp").into(imgPrimaria);
                break;
            default:
                break;
        }
    }

    public void actualizarImgSecundaria(Armas arma){
        switch (arma.getName()){
            case "1911":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_1911.webp").into(imgSecundaria);
                break;
            case "X16":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_x16.webp").into(imgSecundaria);
                break;
            case ".50 GS":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_50-gs.webp").into(imgSecundaria);
                break;
            case "Combat Knife":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_combat-knife.webp").into(imgSecundaria);
                break;
            case "Riot Shield":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_riot-shield.webp").into(imgSecundaria);
                break;
            default:
                break;
        }
    }

    public void actualizarCamposArmasPrincipales(Armas actualizar){
        tvNameArma.setText(actualizar.getName());
        tvPrecision.setProgress(actualizar.getAccuracy());
        tvDano.setProgress(actualizar.getDamage());
        tvAlcance.setProgress(actualizar.getRange());
        tvCadencia.setProgress(actualizar.getFire_rate());
        tvMovilidad.setProgress(actualizar.getMobility());
        tvControl.setProgress(actualizar.getControl());
    }

    public void actualizarCamposArmasSecundarias(Armas actualizar){
        tvNameArmaSec.setText(actualizar.getName());
        tvPrecisionSec.setProgress(actualizar.getAccuracy());
        tvDanoSec.setProgress(actualizar.getDamage());
        tvAlcanceSec.setProgress(actualizar.getRange());
        tvCadenciaSec.setProgress(actualizar.getFire_rate());
        tvMovilidadSec.setProgress(actualizar.getMobility());
        tvControlSec.setProgress(actualizar.getControl());
    }
    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        usuarioRecuperado = preferences.getString("user","usuario");
        contrasenaRecuperada = preferences.getString("contrasena","password");

        Bundle parametros = this.getIntent().getExtras();//se recupera el Bundle de la Intent recibida
        String valor = parametros.getString("className");

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("clase",valor);

        editor.commit();
    }

    public void cambiarArmaPrincipal(View view){
        Intent editar = new Intent(this, ArmasPrincipalesActivity.class);
        startActivity(editar);
    }

    public void cambiarArmaSecundaria(View view){
        Intent editar = new Intent(this, ArmasSecundariasActivity.class);
        startActivity(editar);
    }

    public void perfilUsuario(View view){
        Intent perfil = new Intent(this, ActualizarCuentaActivity.class);
        perfil.putExtra("usuario", usuarioRecuperado);
        perfil.putExtra("password", contrasenaRecuperada);

        startActivity(perfil);
    }

    public void volverClases(View v) {
        Intent i = new Intent(DetalleClaseActivity.this, ClasesActivity.class);
        i.putExtra("usuario", usuarioRecuperado);
        i.putExtra("password", contrasenaRecuperada);
        startActivity(i);
    }

    @Override
    public void onPause(){
        super.onPause();
        finish();
    }
}
