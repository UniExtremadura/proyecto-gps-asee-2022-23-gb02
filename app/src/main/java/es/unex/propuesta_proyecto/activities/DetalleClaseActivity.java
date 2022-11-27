package es.unex.propuesta_proyecto.activities;

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
import es.unex.propuesta_proyecto.dao.AppDatabaseArmas;
import es.unex.propuesta_proyecto.dao.AppDatabaseClases;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Clases;

/* Esta clase se encarga de mostrar todos los detalles de las armas, además desde ella se acceden a los accesorios, se pueden editar las armas, intercambiandolas con la API, pulsando
    el botón de editar.
 */
public class DetalleClaseActivity extends AppCompatActivity {

    Button bPrimaria;
    ImageView imgPrimaria, imgSecundaria,bBorrar;
    String usuarioRecuperado, contrasenaRecuperada;

    TextView tvNameArma;
    ProgressBar tvPrecision,tvDano,tvAlcance,tvCadencia,tvMovilidad,tvControl;

    TextView tvNameArmaSec;
    ProgressBar tvPrecisionSec,tvDanoSec,tvAlcanceSec,tvCadenciaSec,tvMovilidadSec,tvControlSec;

    /* En el OnCreate se crean las clases y en el OnResume se actualizan */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarPreferencias();

        // Dependiendo de la clase recuperada en el bundle se accederá a una clase u otra.

        Bundle parametros = this.getIntent().getExtras();//se recupera el Bundle de la Intent recibida
        if(parametros != null) {
            String valor = parametros.getString("className");
            Log.d("valor",valor);
            switch (valor) {
                case "Clase 2":
                    setContentView(R.layout.activity_detalle_clase2);

                    tvNameArma = findViewById(R.id.tvNameArma2);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal2);tvDano = findViewById(R.id.pbDañoArmaPrincipal2);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal2);tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal2);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal2);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal2);

                    imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal2);
                    imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria2);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria2);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria2);
                    tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria2);tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria2);
                    tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria2);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria2);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria2);

                    AppExecutors.getInstance().diskIO().execute(() -> {
                        /* Cogemos la clase actual en la que se encuentra el usuario  */
                        Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 2",usuarioRecuperado);
                        /* Creación de armas por defecto */
                        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1, "AK-47");
                        Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0, "RPG-7");
                        List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorClaseyNombre(clase.getId(),usuarioRecuperado); // Si es nulo, significa que no tiene ningún arma en esta clase
                        if(armas.size() != 0){
                            for(int i = 0; i < armas.size() ; i++){
                                if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                    Armas armaActual = armas.get(i);
                                    if(armaActual.getPrincipal() == 1){
                                        actualizarCamposArmasPrincipales(armaActual);
                                        //Ejecutamos en el hilo principal porque ejecuta cambios en la vista
                                        runOnUiThread(() -> actualizarImgPrimaria(armaActual));
                                    }else{
                                        if(armaActual.getPrincipal() == 0){
                                            actualizarCamposArmasSecundarias(armaActual);
                                            runOnUiThread(() -> actualizarImgSecundaria(armaActual));
                                        }
                                    }
                                }
                            }
                        } else { // No tiene ningún arma
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            actualizarCamposArmasPrincipales(a);
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            actualizarCamposArmasSecundarias(a2);
                        }
                    });
                    break;
                case "Clase 3":
                    setContentView(R.layout.activity_detalle_clase3);

                    tvNameArma = findViewById(R.id.tvNameArma3);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal3);tvDano = findViewById(R.id.pbDañoArmaPrincipal3);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal3);tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal3);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal3);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal3);

                    imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal3);
                    imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria3);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria3);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria3);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria3);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria3);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria3);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria3);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria3);

                    AppExecutors.getInstance().diskIO().execute(() -> {
                        /* Cogemos la clase actual en la que se encuentra el usuario  */
                        Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 3",usuarioRecuperado);
                        /* Creación de armas por defecto */
                        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1, "AK-47");
                        Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0, "RPG-7");
                        List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorClaseyNombre(clase.getId(),usuarioRecuperado); // Si es nulo, significa que no tiene ningún arma en esta clase
                        if(armas.size() != 0){
                            for(int i = 0; i < armas.size() ; i++){
                                if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                    Armas armaActual = armas.get(i);
                                    if(armaActual.getPrincipal() == 1){
                                        actualizarCamposArmasPrincipales(armaActual);
                                        runOnUiThread(() -> actualizarImgPrimaria(armaActual));
                                    }else{
                                        if(armaActual.getPrincipal() == 0){
                                            actualizarCamposArmasSecundarias(armaActual);
                                            runOnUiThread(() -> actualizarImgSecundaria(armaActual));
                                        }
                                    }
                                }
                            }
                        } else { // No tiene ningún arma
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            actualizarCamposArmasPrincipales(a);
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            actualizarCamposArmasSecundarias(a2);
                        }
                    });
                    break;
                case "Clase 4":
                    setContentView(R.layout.activity_detalle_clase4);
                    tvNameArma = findViewById(R.id.tvNameArma4);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal4);tvDano = findViewById(R.id.pbDañoArmaPrincipal4);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal4);tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal4);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal4);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal4);

                    imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal4);
                    imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria4);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria4);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria4);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria4);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria4);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria4);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria4);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria4);

                    AppExecutors.getInstance().diskIO().execute(() -> {
                        /* Cogemos la clase actual en la que se encuentra el usuario  */
                        Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 4",usuarioRecuperado);
                        /* Creación de armas por defecto */
                        Log.d("Claseid",String.valueOf(clase.getId()));
                        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1, "AK-47");
                        Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0, "RPG-7");
                        List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorClaseyNombre(clase.getId(),usuarioRecuperado); // Si es nulo, significa que no tiene ningún arma en esta clase
                        if(armas.size() != 0){
                            for(int i = 0; i < armas.size() ; i++){
                                if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                    Armas armaActual = armas.get(i);
                                    if(armaActual.getPrincipal() == 1){
                                        actualizarCamposArmasPrincipales(armaActual);
                                        runOnUiThread(() -> actualizarImgPrimaria(armaActual));
                                    }else{
                                        if(armaActual.getPrincipal() == 0){
                                            actualizarCamposArmasSecundarias(armaActual);
                                            runOnUiThread(() -> actualizarImgSecundaria(armaActual));
                                        }
                                    }
                                }
                            }
                        } else { // No tiene ningún arma
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            actualizarCamposArmasPrincipales(a);
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            actualizarCamposArmasSecundarias(a2);
                        }
                    });

                    break;
                case "Clase 5":
                    setContentView(R.layout.activity_detalle_clase5);
                    tvNameArma = findViewById(R.id.tvNameArma5);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal5);
                    tvDano = findViewById(R.id.pbDañoArmaPrincipal5);tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal5);
                    tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal5);
                    tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal5);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal5);

                    imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal5);
                    imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria5);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria5);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria5);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria5);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria5);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria5);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria5);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria5);

                    AppExecutors.getInstance().diskIO().execute(() -> {
                        /* Cogemos la clase actual en la que se encuentra el usuario  */
                        Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 5",usuarioRecuperado);
                        /* Creación de armas por defecto */
                        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1, "AK-47");
                        Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0, "RPG-7");
                        List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorClaseyNombre(clase.getId(),usuarioRecuperado); // Si es nulo, significa que no tiene ningún arma en esta clase
                        if(armas.size() != 0){
                            for(int i = 0; i < armas.size() ; i++){
                                if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                    Armas armaActual = armas.get(i);
                                    if(armaActual.getPrincipal() == 1){
                                        actualizarCamposArmasPrincipales(armaActual);
                                        runOnUiThread(() -> actualizarImgPrimaria(armaActual));
                                    }else{
                                        if(armaActual.getPrincipal() == 0){
                                            actualizarCamposArmasSecundarias(armaActual);
                                            runOnUiThread(() -> actualizarImgSecundaria(armaActual));
                                        }
                                    }
                                }
                            }
                        } else { // No tiene ningún arma
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            actualizarCamposArmasPrincipales(a);
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            actualizarCamposArmasSecundarias(a2);
                        }
                    });
                    break;
                case "Clase 6":
                    setContentView(R.layout.activity_detalle_clase6);
                    tvNameArma = findViewById(R.id.tvNameArma6);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal6);
                    tvDano = findViewById(R.id.pbDañoArmaPrincipal6);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal6);
                    tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal6);
                    tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal6);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal6);

                    imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal6);
                    imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria6);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria6);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria6);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria6);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria6);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria6);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria6);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria6);

                    AppExecutors.getInstance().diskIO().execute(() -> {
                        /* Cogemos la clase actual en la que se encuentra el usuario  */
                        Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 6",usuarioRecuperado);
                        /* Creación de armas por defecto */
                        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1, "AK-47");
                        Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0, "RPG-7");
                        List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorClaseyNombre(clase.getId(),usuarioRecuperado); // Si es nulo, significa que no tiene ningún arma en esta clase
                        if(armas.size() != 0){
                            for(int i = 0; i < armas.size() ; i++){
                                if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                    Armas armaActual = armas.get(i);
                                    if(armaActual.getPrincipal() == 1){
                                        actualizarCamposArmasPrincipales(armaActual);
                                        runOnUiThread(() -> actualizarImgPrimaria(armaActual));
                                    }else{
                                        if(armaActual.getPrincipal() == 0){
                                            actualizarCamposArmasSecundarias(armaActual);
                                            runOnUiThread(() -> actualizarImgSecundaria(armaActual));
                                        }
                                    }
                                }
                            }
                        } else { // No tiene ningún arma
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            actualizarCamposArmasPrincipales(a);
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            actualizarCamposArmasSecundarias(a2);
                        }
                    });
                    break;
                case "Clase 7":
                    setContentView(R.layout.activity_detalle_clase7);

                    tvNameArma = findViewById(R.id.tvNameArma7);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal7);
                    tvDano = findViewById(R.id.pbDañoArmaPrincipal7);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal7);
                    tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal7);
                    tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal7);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal7);

                    imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal7);
                    imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria7);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria7);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria7);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria7);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria7);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria7);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria7);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria7);

                    AppExecutors.getInstance().diskIO().execute(() -> {
                        /* Cogemos la clase actual en la que se encuentra el usuario  */
                        Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 7",usuarioRecuperado);
                        /* Creación de armas por defecto */
                        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1, "AK-47");
                        Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0, "RPG-7");
                        List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorClaseyNombre(clase.getId(),usuarioRecuperado); // Si es nulo, significa que no tiene ningún arma en esta clase
                        if(armas.size() != 0){
                            for(int i = 0; i < armas.size() ; i++){
                                if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                    Armas armaActual = armas.get(i);
                                    if(armaActual.getPrincipal() == 1){
                                        actualizarCamposArmasPrincipales(armaActual);
                                        runOnUiThread(() -> actualizarImgPrimaria(armaActual));
                                    }else{
                                        if(armaActual.getPrincipal() == 0){
                                            actualizarCamposArmasSecundarias(armaActual);
                                            runOnUiThread(() -> actualizarImgSecundaria(armaActual));
                                        }
                                    }
                                }
                            }
                        } else { // No tiene ningún arma
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            actualizarCamposArmasPrincipales(a);
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            actualizarCamposArmasSecundarias(a2);
                        }
                    });
                    break;
                case "Clase 8":
                    setContentView(R.layout.activity_detalle_clase8);

                    tvNameArma = findViewById(R.id.tvNameArma8);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal8);
                    tvDano = findViewById(R.id.pbDañoArmaPrincipal8);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal8);
                    tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal8);
                    tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal8);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal8);

                    imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal8);
                    imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria8);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria8);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria8);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria8);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria8);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria8);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria8);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria8);

                    AppExecutors.getInstance().diskIO().execute(() -> {
                        /* Cogemos la clase actual en la que se encuentra el usuario  */
                        Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 8",usuarioRecuperado);
                        /* Creación de armas por defecto */
                        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1,"AK-47");
                        Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0, "RPG-7");
                        List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorClaseyNombre(clase.getId(),usuarioRecuperado); // Si es nulo, significa que no tiene ningún arma en esta clase
                        if(armas.size() != 0){
                            for(int i = 0; i < armas.size() ; i++){
                                if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                    Armas armaActual = armas.get(i);
                                    if(armaActual.getPrincipal() == 1){
                                        actualizarCamposArmasPrincipales(armaActual);
                                        runOnUiThread(() -> actualizarImgPrimaria(armaActual));
                                    }else{
                                        if(armaActual.getPrincipal() == 0){
                                            actualizarCamposArmasSecundarias(armaActual);
                                            runOnUiThread(() -> actualizarImgSecundaria(armaActual));
                                        }
                                    }
                                }
                            }
                        } else { // No tiene ningún arma
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            actualizarCamposArmasPrincipales(a);
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            actualizarCamposArmasSecundarias(a2);
                        }
                    });
                    break;
                case "Clase 9":
                    setContentView(R.layout.activity_detalle_clase9);
                    tvNameArma = findViewById(R.id.tvNameArma9);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal9);
                    tvDano = findViewById(R.id.pbDañoArmaPrincipal9);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal9);
                    tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal9);
                    tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal9);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal9);

                    imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal9);
                    imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria9);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria9);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria9);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria9);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria9);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria9);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria9);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria9);

                    AppExecutors.getInstance().diskIO().execute(() -> {
                        /* Cogemos la clase actual en la que se encuentra el usuario  */
                        Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 9",usuarioRecuperado);
                        /* Creación de armas por defecto */
                        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1, "AK-47");
                        Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0, "RPG-7");
                        List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorClaseyNombre(clase.getId(),usuarioRecuperado); // Si es nulo, significa que no tiene ningún arma en esta clase
                        if(armas.size() != 0){
                            for(int i = 0; i < armas.size() ; i++){
                                if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                    Armas armaActual = armas.get(i);
                                    if(armaActual.getPrincipal() == 1){
                                        actualizarCamposArmasPrincipales(armaActual);
                                        runOnUiThread(() -> actualizarImgPrimaria(armaActual));
                                    }else{
                                        if(armaActual.getPrincipal() == 0){
                                            actualizarCamposArmasSecundarias(armaActual);
                                            runOnUiThread(() -> actualizarImgSecundaria(armaActual));
                                        }
                                    }
                                }
                            }
                        } else { // No tiene ningún arma
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            actualizarCamposArmasPrincipales(a);
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            actualizarCamposArmasSecundarias(a2);
                        }
                    });
                    break;
                case "Clase 10":
                    setContentView(R.layout.activity_detalle_clase10);
                    tvNameArma = findViewById(R.id.tvNameArma10);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal10);
                    tvDano = findViewById(R.id.pbDañoArmaPrincipal10);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal10);
                    tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal10);
                    tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal10);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal10);

                    imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal10);
                    imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria10);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria10);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria10);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria10);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria10);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria10);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria10);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria10);

                    AppExecutors.getInstance().diskIO().execute(() -> {
                        /* Cogemos la clase actual en la que se encuentra el usuario  */
                        Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 10",usuarioRecuperado);
                        /* Creación de armas por defecto */
                        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1, "AK-47");
                        Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0, "RPG-7");
                        List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorClaseyNombre(clase.getId(),usuarioRecuperado); // Si es nulo, significa que no tiene ningún arma en esta clase
                        if(armas.size() != 0){
                            for(int i = 0; i < armas.size() ; i++){
                                if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                    Armas armaActual = armas.get(i);
                                    if(armaActual.getPrincipal() == 1){
                                        actualizarCamposArmasPrincipales(armaActual);
                                        runOnUiThread(() -> actualizarImgPrimaria(armaActual));
                                    }else{
                                        if(armaActual.getPrincipal() == 0){
                                            actualizarCamposArmasSecundarias(armaActual);
                                            runOnUiThread(() -> actualizarImgSecundaria(armaActual));
                                        }
                                    }
                                }
                            }
                        } else { // No tiene ningún arma
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            actualizarCamposArmasPrincipales(a);
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            actualizarCamposArmasSecundarias(a2);
                        }
                    });
                    break;
                default:
                    setContentView(R.layout.activity_detalle_clase1);

                    tvNameArma = findViewById(R.id.tvNombre1);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal1);
                    tvDano = findViewById(R.id.pbDañoArmaPrincipal1);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal1);
                    tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal1);
                    tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal1);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal1);

                    imgPrimaria = findViewById(R.id.ivArma1);
                    imgSecundaria = findViewById(R.id.ivArmaSecundaria1);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria1);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria1);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria1);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria1);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria1);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria1);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria1);

                    AppExecutors.getInstance().diskIO().execute(() -> {
                        /* Cogemos la clase actual en la que se encuentra el usuario  */
                        Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 1",usuarioRecuperado);
                        /* Creación de armas por defecto */
                        Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1, "AK-47");
                        Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0, "RPG-7");
                        List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorClaseyNombre(clase.getId(),usuarioRecuperado); // Si es nulo, significa que no tiene ningún arma en esta clase
                        if(armas.size() != 0){
                            for(int i = 0; i < armas.size() ; i++){
                                if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                    Armas armaActual = armas.get(i);
                                    if(armaActual.getPrincipal() == 1){
                                        actualizarCamposArmasPrincipales(armaActual);
                                        runOnUiThread(() -> actualizarImgPrimaria(armaActual));
                                    }else{
                                        if(armaActual.getPrincipal() == 0){
                                            actualizarCamposArmasSecundarias(armaActual);
                                            runOnUiThread(() -> actualizarImgSecundaria(armaActual));
                                        }
                                    }
                                }
                            }
                        } else { // No tiene ningún arma
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            actualizarCamposArmasPrincipales(a);
                            AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            actualizarCamposArmasSecundarias(a2);
                        }
                    });
                    break;
            }
        }

        /* Boton para eliminar la clase en cuestión */
        bBorrar=findViewById(R.id.ivEliminarClase);
        bBorrar.setOnClickListener(v -> {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {//borra del Room la clase indicada
                    //hay que borrar la clase, y también las armas asociadas a esa clase
                    Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase(parametros.getString("className"),usuarioRecuperado);
                    AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().borrarArmaPorClaseyNombre(clase.getId(),usuarioRecuperado);
                    //Este borrar clases funciona perfectamente (comprobado con AppInspector)
                    AppDatabaseClases.getInstance(getApplicationContext()).daoClases().borrarClase(parametros.getString("className"));
                }
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
        switch (arma.getWeapon()) {
            case "ak-47":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_ak-47.webp").into(imgPrimaria);
                break;
            case "aug":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_aug.webp").into(imgPrimaria);
                break;
            case "fn-scar-17":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_fn-scar-17.webp").into(imgPrimaria);
                break;
            case "m4a1":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_m4a1.webp").into(imgPrimaria);
                break;
            case "725":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_725.webp").into(imgPrimaria);
                break;
            case "model-680":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_model-680.webp").into(imgPrimaria);
                break;
            case "r9-0-shotgun":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_r9-0-shotgun.webp").into(imgPrimaria);
                break;
            case "origin-12-shotgun":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_origin-12-shotgun.webp").into(imgPrimaria);
                break;
            case "dragunov":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_dragunov.webp").into(imgPrimaria);
                break;
            case "ebr-14":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_ebr-14.webp").into(imgPrimaria);
                break;
            case "hdr":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_hdr.webp").into(imgPrimaria);
                break;
            case "kar98k":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_kar98k.webp").into(imgPrimaria);
                break;
            case "mg34":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_mg34.webp").into(imgPrimaria);
                break;
            case "m91":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_m91.webp").into(imgPrimaria);
                break;
            case "pkm":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_pkm.webp").into(imgPrimaria);
                break;
            case "mp5":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_mp5.webp").into(imgPrimaria);
                break;
            case "mp7":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_mp7.webp").into(imgPrimaria);
                break;
            case "p90":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_p90.webp").into(imgPrimaria);
                break;
            case "uzi":
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
            case "x16":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_x16.webp").into(imgSecundaria);
                break;
            case "50-gs":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_50-gs.webp").into(imgSecundaria);
                break;
            case "combat-knife":
                Picasso.get().load("https://www.gamesatlas.com/images/jch-optimize/ng/images_cod-modern-warfare_weapons_combat-knife.webp").into(imgSecundaria);
                break;
            case "riot-shield":
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

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String clase = preferences.getString("clase","CLASE VACIA");

        AppExecutors.getInstance().diskIO().execute(() -> {
                    Clases claseRecuperada = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase(clase,usuarioRecuperado);
                    List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorClaseyNombre(claseRecuperada.getId(), usuarioRecuperado); // Si es nulo, significa que no tiene ningún arma en esta clase
            switch (claseRecuperada.getNombre()){
                        case "Clase 2":
                            tvNameArma = findViewById(R.id.tvNameArma2);
                            tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal2);
                            tvDano = findViewById(R.id.pbDañoArmaPrincipal2);
                            tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal2);
                            tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal2);
                            tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal2);
                            tvControl = findViewById(R.id.pbControlArmaPrincipal2);

                            imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal2);
                            imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria2);

                            tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria2);
                            tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria2);
                            tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria2);
                            tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria2);
                            tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria2);
                            tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria2);
                            tvControlSec = findViewById(R.id.pbControlArmaSecundaria2);

                            if (armas.size() != 0) {
                                for (int i = 0; i < armas.size(); i++) {
                                    if (armas.get(i).getIdClase() == claseRecuperada.getId()) {
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(), armaActual.getType(), armaActual.getSubtype(), armaActual.getAccuracy(), armaActual.getDamage(), armaActual.getRange(), armaActual.getFire_rate(), armaActual.getMobility(), armaActual.getControl(), armaActual.getId(), claseRecuperada.getId(), armaActual.getPrincipal(), armaActual.getWeapon());
                                        if (armaActual.getPrincipal() == 1) {
                                            runOnUiThread(() -> {
                                                actualizarCamposArmasPrincipales(armaActual);
                                                actualizarImgPrimaria(armaActual);
                                            });
                                        } else {
                                            if (armaActual.getPrincipal() == 0) {
                                            runOnUiThread(() -> {
                                                    actualizarImgSecundaria(armaActual);
                                                    actualizarCamposArmasSecundarias(armaActual);
                                                 });
                                            }
                                        }
                                    }
                                }
                            }

                            break;
                        case "Clase 3":
                            tvNameArma = findViewById(R.id.tvNameArma3);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal3);tvDano = findViewById(R.id.pbDañoArmaPrincipal3);
                            tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal3);tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal3);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal3);
                            tvControl = findViewById(R.id.pbControlArmaPrincipal3);

                            imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal3);
                            imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria3);

                            tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria3);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria3);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria3);
                            tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria3);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria3);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria3);
                            tvControlSec = findViewById(R.id.pbControlArmaSecundaria3);

                            if (armas.size() != 0) {
                                for (int i = 0; i < armas.size(); i++) {
                                    if (armas.get(i).getIdClase() == claseRecuperada.getId()) {
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(), armaActual.getType(), armaActual.getSubtype(), armaActual.getAccuracy(), armaActual.getDamage(), armaActual.getRange(), armaActual.getFire_rate(), armaActual.getMobility(), armaActual.getControl(), armaActual.getId(), claseRecuperada.getId(), armaActual.getPrincipal(), armaActual.getWeapon());
                                        if (armaActual.getPrincipal() == 1) {
                                            runOnUiThread(() -> {
                                                actualizarCamposArmasPrincipales(armaActual);
                                                actualizarImgPrimaria(armaActual);
                                            });
                                        } else {
                                            if (armaActual.getPrincipal() == 0) {
                                                runOnUiThread(() -> {
                                                    actualizarImgSecundaria(armaActual);
                                                    actualizarCamposArmasSecundarias(armaActual);
                                                });
                                            }
                                        }
                                    }
                                }
                            }

                            break;
                        case "Clase 4":
                            tvNameArma = findViewById(R.id.tvNameArma4);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal4);tvDano = findViewById(R.id.pbDañoArmaPrincipal4);
                            tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal4);tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal4);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal4);
                            tvControl = findViewById(R.id.pbControlArmaPrincipal4);

                            imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal4);
                            imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria4);

                            tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria4);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria4);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria4);
                            tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria4);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria4);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria4);
                            tvControlSec = findViewById(R.id.pbControlArmaSecundaria4);

                            if (armas.size() != 0) {
                                for (int i = 0; i < armas.size(); i++) {
                                    if (armas.get(i).getIdClase() == claseRecuperada.getId()) {
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(), armaActual.getType(), armaActual.getSubtype(), armaActual.getAccuracy(), armaActual.getDamage(), armaActual.getRange(), armaActual.getFire_rate(), armaActual.getMobility(), armaActual.getControl(), armaActual.getId(), claseRecuperada.getId(), armaActual.getPrincipal(), armaActual.getWeapon());
                                        if (armaActual.getPrincipal() == 1) {
                                            runOnUiThread(() -> {
                                                actualizarCamposArmasPrincipales(armaActual);
                                                actualizarImgPrimaria(armaActual);
                                            });
                                        } else {
                                            if (armaActual.getPrincipal() == 0) {
                                                runOnUiThread(() -> {
                                                    actualizarImgSecundaria(armaActual);
                                                    actualizarCamposArmasSecundarias(armaActual);
                                                });
                                            }
                                        }
                                    }
                                }
                            }

                            break;
                        case "Clase 5":
                            tvNameArma = findViewById(R.id.tvNameArma5);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal5);tvDano = findViewById(R.id.pbDañoArmaPrincipal5);tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal5);
                            tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal5);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal5);tvControl = findViewById(R.id.pbControlArmaPrincipal5);

                            imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal5);
                            imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria5);

                            tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria5);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria5);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria5);
                            tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria5);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria5);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria5);
                            tvControlSec = findViewById(R.id.pbControlArmaSecundaria5);


                            if (armas.size() != 0) {
                                for (int i = 0; i < armas.size(); i++) {
                                    if (armas.get(i).getIdClase() == claseRecuperada.getId()) {
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(), armaActual.getType(), armaActual.getSubtype(), armaActual.getAccuracy(), armaActual.getDamage(), armaActual.getRange(), armaActual.getFire_rate(), armaActual.getMobility(), armaActual.getControl(), armaActual.getId(), claseRecuperada.getId(), armaActual.getPrincipal(), armaActual.getWeapon());
                                        if (armaActual.getPrincipal() == 1) {
                                            runOnUiThread(() -> {
                                                actualizarCamposArmasPrincipales(armaActual);
                                                actualizarImgPrimaria(armaActual);
                                            });
                                        } else {
                                            if (armaActual.getPrincipal() == 0) {
                                                runOnUiThread(() -> {
                                                    actualizarImgSecundaria(armaActual);
                                                    actualizarCamposArmasSecundarias(armaActual);
                                                });
                                            }
                                        }
                                    }
                                }
                            }

                            break;
                        case "Clase 6":
                            tvNameArma = findViewById(R.id.tvNameArma6);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal6);tvDano = findViewById(R.id.pbDañoArmaPrincipal6);tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal6);
                            tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal6);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal6);tvControl = findViewById(R.id.pbControlArmaPrincipal6);

                            imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal6);
                            imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria6);

                            tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria6);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria6);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria6);
                            tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria6);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria6);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria6);
                            tvControlSec = findViewById(R.id.pbControlArmaSecundaria6);

                            if (armas.size() != 0) {
                                for (int i = 0; i < armas.size(); i++) {
                                    if (armas.get(i).getIdClase() == claseRecuperada.getId()) {
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(), armaActual.getType(), armaActual.getSubtype(), armaActual.getAccuracy(), armaActual.getDamage(), armaActual.getRange(), armaActual.getFire_rate(), armaActual.getMobility(), armaActual.getControl(), armaActual.getId(), claseRecuperada.getId(), armaActual.getPrincipal(), armaActual.getWeapon());
                                        if (armaActual.getPrincipal() == 1) {
                                            runOnUiThread(() -> {
                                                actualizarCamposArmasPrincipales(armaActual);
                                                actualizarImgPrimaria(armaActual);
                                            });
                                        } else {
                                            if (armaActual.getPrincipal() == 0) {
                                                runOnUiThread(() -> {
                                                    actualizarImgSecundaria(armaActual);
                                                    actualizarCamposArmasSecundarias(armaActual);
                                                });
                                            }
                                        }
                                    }
                                }
                            }

                            break;
                        case "Clase 7":
                            tvNameArma = findViewById(R.id.tvNameArma7);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal7);tvDano = findViewById(R.id.pbDañoArmaPrincipal7);
                            tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal7);tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal7);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal7);
                            tvControl = findViewById(R.id.pbControlArmaPrincipal7);

                            imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal7);
                            imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria7);

                            tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria7);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria7);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria7);
                            tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria7);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria7);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria7);
                            tvControlSec = findViewById(R.id.pbControlArmaSecundaria7);

                            if (armas.size() != 0) {
                                for (int i = 0; i < armas.size(); i++) {
                                    if (armas.get(i).getIdClase() == claseRecuperada.getId()) {
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(), armaActual.getType(), armaActual.getSubtype(), armaActual.getAccuracy(), armaActual.getDamage(), armaActual.getRange(), armaActual.getFire_rate(), armaActual.getMobility(), armaActual.getControl(), armaActual.getId(), claseRecuperada.getId(), armaActual.getPrincipal(), armaActual.getWeapon());
                                        if (armaActual.getPrincipal() == 1) {
                                            runOnUiThread(() -> {
                                                actualizarCamposArmasPrincipales(armaActual);
                                                actualizarImgPrimaria(armaActual);
                                            });
                                        } else {
                                            if (armaActual.getPrincipal() == 0) {
                                                runOnUiThread(() -> {
                                                    actualizarImgSecundaria(armaActual);
                                                    actualizarCamposArmasSecundarias(armaActual);
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        case "Clase 8":
                            tvNameArma = findViewById(R.id.tvNameArma8);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal8);tvDano = findViewById(R.id.pbDañoArmaPrincipal8);tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal8);
                            tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal8);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal8);tvControl = findViewById(R.id.pbControlArmaPrincipal8);

                            imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal8);
                            imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria8);

                            tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria8);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria8);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria8);
                            tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria8);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria8);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria8);
                            tvControlSec = findViewById(R.id.pbControlArmaSecundaria8);

                            if (armas.size() != 0) {
                                for (int i = 0; i < armas.size(); i++) {
                                    if (armas.get(i).getIdClase() == claseRecuperada.getId()) {
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(), armaActual.getType(), armaActual.getSubtype(), armaActual.getAccuracy(), armaActual.getDamage(), armaActual.getRange(), armaActual.getFire_rate(), armaActual.getMobility(), armaActual.getControl(), armaActual.getId(), claseRecuperada.getId(), armaActual.getPrincipal(), armaActual.getWeapon());
                                        if (armaActual.getPrincipal() == 1) {
                                            runOnUiThread(() -> {
                                                actualizarCamposArmasPrincipales(armaActual);
                                                actualizarImgPrimaria(armaActual);
                                            });
                                        } else {
                                            if (armaActual.getPrincipal() == 0) {
                                                runOnUiThread(() -> {
                                                    actualizarImgSecundaria(armaActual);
                                                    actualizarCamposArmasSecundarias(armaActual);
                                                });
                                            }
                                        }
                                    }
                                }
                            }

                            break;
                        case "Clase 9":
                            tvNameArma = findViewById(R.id.tvNameArma9);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal9);tvDano = findViewById(R.id.pbDañoArmaPrincipal9);tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal9);
                            tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal9);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal9);tvControl = findViewById(R.id.pbControlArmaPrincipal9);

                            imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal9);
                            imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria9);

                            tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria9);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria9);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria9);
                            tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria9);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria9);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria9);
                            tvControlSec = findViewById(R.id.pbControlArmaSecundaria9);

                            if (armas.size() != 0) {
                                for (int i = 0; i < armas.size(); i++) {
                                    if (armas.get(i).getIdClase() == claseRecuperada.getId()) {
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(), armaActual.getType(), armaActual.getSubtype(), armaActual.getAccuracy(), armaActual.getDamage(), armaActual.getRange(), armaActual.getFire_rate(), armaActual.getMobility(), armaActual.getControl(), armaActual.getId(), claseRecuperada.getId(), armaActual.getPrincipal(), armaActual.getWeapon());
                                        if (armaActual.getPrincipal() == 1) {
                                            runOnUiThread(() -> {
                                                actualizarCamposArmasPrincipales(armaActual);
                                                actualizarImgPrimaria(armaActual);
                                            });
                                        } else {
                                            if (armaActual.getPrincipal() == 0) {
                                                runOnUiThread(() -> {
                                                    actualizarImgSecundaria(armaActual);
                                                    actualizarCamposArmasSecundarias(armaActual);
                                                });
                                            }
                                        }
                                    }
                                }
                            }

                            break;
                        case "Clase 10":
                            tvNameArma = findViewById(R.id.tvNameArma10);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal10);tvDano = findViewById(R.id.pbDañoArmaPrincipal10);tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal10);
                            tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal10);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal10);tvControl = findViewById(R.id.pbControlArmaPrincipal10);

                            imgPrimaria = findViewById(R.id.ivArmaArmaPrincipal10);
                            imgSecundaria = findViewById(R.id.ivArmaArmaSecundaria10);

                            tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria10);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria10);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria10);
                            tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria10);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria10);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria10);
                            tvControlSec = findViewById(R.id.pbControlArmaSecundaria10);

                            if (armas.size() != 0) {
                                for (int i = 0; i < armas.size(); i++) {
                                    if (armas.get(i).getIdClase() == claseRecuperada.getId()) {
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(), armaActual.getType(), armaActual.getSubtype(), armaActual.getAccuracy(), armaActual.getDamage(), armaActual.getRange(), armaActual.getFire_rate(), armaActual.getMobility(), armaActual.getControl(), armaActual.getId(), claseRecuperada.getId(), armaActual.getPrincipal(), armaActual.getWeapon());
                                        if (armaActual.getPrincipal() == 1) {
                                            runOnUiThread(() -> {
                                                actualizarCamposArmasPrincipales(armaActual);
                                                actualizarImgPrimaria(armaActual);
                                            });
                                        } else {
                                            if (armaActual.getPrincipal() == 0) {
                                                runOnUiThread(() -> {
                                                    actualizarImgSecundaria(armaActual);
                                                    actualizarCamposArmasSecundarias(armaActual);
                                                });
                                            }
                                        }
                                    }
                                }
                            }

                            break;
                        default:
                            tvNameArma = findViewById(R.id.tvNombre1);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal1);tvDano = findViewById(R.id.pbDañoArmaPrincipal1);
                            tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal1);tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal1);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal1);
                            tvControl = findViewById(R.id.pbControlArmaPrincipal1);

                            imgPrimaria = findViewById(R.id.ivArma1);
                            imgSecundaria = findViewById(R.id.ivArmaSecundaria1);

                            tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria1);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria1);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria1);
                            tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria1);tvCadenciaSec = findViewById(R.id.pbCadenciaArmaSecundaria1);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria1);
                            tvControlSec = findViewById(R.id.pbControlArmaSecundaria1);

                            if (armas.size() != 0) {
                                for (int i = 0; i < armas.size(); i++) {
                                    if (armas.get(i).getIdClase() == claseRecuperada.getId()) {
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(), armaActual.getType(), armaActual.getSubtype(), armaActual.getAccuracy(), armaActual.getDamage(), armaActual.getRange(), armaActual.getFire_rate(), armaActual.getMobility(), armaActual.getControl(), armaActual.getId(), claseRecuperada.getId(), armaActual.getPrincipal(), armaActual.getWeapon());
                                        if (armaActual.getPrincipal() == 1) {
                                            runOnUiThread(() -> {
                                                actualizarCamposArmasPrincipales(armaActual);
                                                actualizarImgPrimaria(armaActual);
                                            });
                                        } else {
                                            if (armaActual.getPrincipal() == 0) {
                                                runOnUiThread(() -> {
                                                    actualizarImgSecundaria(armaActual);
                                                    actualizarCamposArmasSecundarias(armaActual);
                                                });
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                    }
                });
    }
}
