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

import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import java.util.List;
import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDataBase;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Clases;

/* Esta clase se encarga de mostrar todos los detalles de las armas, además desde ella se acceden a los accesorios, se pueden editar las armas, intercambiandolas con la API, pulsando
    el botón de editar.
 */
public class DetalleClaseActivity extends AppCompatActivity {

    Button bPrimaria;
    ImageView imgPrimaria, imgSecundaria,bBorrar;
    String usuarioRecuperado, contrasenaRecuperada;

    TextView tvNameArma, nombreClase;
    ProgressBar tvPrecision,tvDano,tvAlcance,tvCadencia,tvMovilidad,tvControl;

    TextView tvNameArmaSec;
    ProgressBar tvPrecisionSec,tvDanoSec,tvAlcanceSec,tvCadenciaSec,tvMovilidadSec,tvControlSec;

    /* En el OnCreate se crean las clases y en el OnResume se actualizan */

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarPreferencias();

        // Dependiendo de la clase recuperada en el bundle se accederá a una clase u otra.


        Bundle parametros = this.getIntent().getExtras();//se recupera el Bundle de la Intent recibida
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
                    List<Clases> clasesUsuario = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClasesUsuario(usuarioRecuperado); // Lista de clases del usuario
                    if (clasesUsuario != null) {
                        for (int i = 0; i < clasesUsuario.size(); i++) {
                            if(clasesUsuario.get(i).getNombre().equals(valor)){
                                claseActual = clasesUsuario.get(i).getId(); // Recupera la id de la clase actual.
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
                    //Se ejecutan desde el hilo principal
                    AppExecutors.getInstance().mainThread().execute(() -> actualizarImgPrimaria(aPrin));
                    AppExecutors.getInstance().mainThread().execute(() -> actualizarImgSecundaria(aSec));
                }
            });

        }

        /* Boton para eliminar la clase en cuestión */
        bBorrar = findViewById(R.id.ivEliminarClase);
        bBorrar.setOnClickListener(v -> {
            AppExecutors.getInstance().diskIO().execute(() -> {
                //borra del Room la clase indicada
                //hay que borrar la clase, y también las armas asociadas a esa clase
                Clases clase = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase(parametros.getString("className"),usuarioRecuperado);
                AppDataBase.getInstance(getApplicationContext()).daoJuego().borrarArmaPorClaseyNombre(clase.getId(),usuarioRecuperado);
                //Este borrar clases funciona perfectamente (comprobado con AppInspector)
                AppDataBase.getInstance(getApplicationContext()).daoClases().borrarClase(clase.getNombre(), usuarioRecuperado);
            });
            finish();//se supone que vuelve a la Activity anterior
        });

        /* Boton para acceder a los accesorios de la clase en cuestión */
        bPrimaria = findViewById(R.id.bAccesoriosArmaPrincipal);
        bPrimaria.setOnClickListener(v -> {
            Intent accesorios = new Intent(DetalleClaseActivity.this,AccesoriosActivity.class);
            startActivity(accesorios);
        });
    }

    //Dependiendo el atributo weapon del arma recibida muestra una imagen u otra en función del enlace con las funciones de Picasso
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

    //Dependiendo el atributo weapon del arma recibida muestra una imagen u otra en función del enlace con las funciones de Picasso
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
