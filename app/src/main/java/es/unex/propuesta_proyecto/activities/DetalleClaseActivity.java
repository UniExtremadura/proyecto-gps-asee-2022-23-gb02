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

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDatabaseArmas;
import es.unex.propuesta_proyecto.dao.AppDatabaseClases;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Clases;

public class DetalleClaseActivity extends AppCompatActivity {

    Button bPrimaria;
    ImageView bEditarClase;//este es el icono del lapiz para editar la clase
    String usuarioRecuperado, contrasenaRecuperada;

    TextView tvNameArma;
    ProgressBar tvPrecision,tvDano,tvAlcance,tvCadencia,tvMovilidad,tvControl;
    TextView tvNameArmaSec;
    ProgressBar tvPrecisionSec,tvDanoSec,tvAlcanceSec,tvCadenciaSec,tvMovilidadSec,tvControlSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarPreferencias();
        Bundle parametros = this.getIntent().getExtras();//se recupera el Bundle de la Intent recibida
        if(parametros != null) {
            String valor = parametros.getString("className");
            switch (valor) {
                case "Clase 2":
                    setContentView(R.layout.activity_detalle_clase2);
                    tvNameArma = findViewById(R.id.tvNameArma2);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal2);tvDano = findViewById(R.id.pbDañoArmaPrincipal2);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal2);tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal2);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal2);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal2);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria2);
                    tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria2);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria2);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria2);
                    tvCadencia = findViewById(R.id.pbCadenciaArmaSecundaria2);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria2);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria2);

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 2",usuarioRecuperado);
                            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,11,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1);
                            actualizarCamposArmasPrincipales(a);
                            Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,1,50,50,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0);
                            Log.d("Accuracy", String.valueOf(a2.getAccuracy()));
                            actualizarCamposArmasSecundarias(a2);
                            List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioRecuperado);
                            if(armas != null){
                                for(int i = 0; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        if(armaActual.getPrincipal()== 1){
                                            actualizarCamposArmasPrincipales(armaActual);
                                            String codigo = "cod: " + armaActual.getPrincipal();
                                            Log.d("Codigo arma", codigo);
                                        }else{
                                            String codigo = "cod: " + armaActual.getPrincipal();
                                            Log.d("Codigo arma", codigo);
                                            if(armaActual.getPrincipal() == 0){
                                                actualizarCamposArmasSecundarias(armaActual);
                                            }
                                        }
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            }

                        }
                    });
                    break;
                case "Clase 3":
                    setContentView(R.layout.activity_detalle_clase3);
                    tvNameArma = findViewById(R.id.tvNameArma3);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal3);tvDano = findViewById(R.id.pbDañoArmaPrincipal3);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal3);tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal3);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal3);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal3);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria3);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria3);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria3);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria3);tvCadencia = findViewById(R.id.pbCadenciaArmaSecundaria3);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria3);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria3);

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 3",usuarioRecuperado);
                            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,11,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1);
                            actualizarCamposArmasPrincipales(a);
                            List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioRecuperado);
                            if(armas != null){
                                for(int i = 0; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasPrincipales(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            }

                            Armas a2 = new Armas("RPG-7","Weapon","Base",64,23,12,11,87,65,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0);
                            actualizarCamposArmasSecundarias(a2);
                            if(armas != null){
                                for(int i = 76; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasSecundarias(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            }
                        }
                    });
                    break;
                case "Clase 4":
                    setContentView(R.layout.activity_detalle_clase4);
                    tvNameArma = findViewById(R.id.tvNameArma4);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal4);tvDano = findViewById(R.id.pbDañoArmaPrincipal4);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal4);tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal4);tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal4);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal4);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria4);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria4);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria4);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria4);tvCadencia = findViewById(R.id.pbCadenciaArmaSecundaria4);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria4);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria4);

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 4",usuarioRecuperado);
                            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,11,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1);
                            actualizarCamposArmasPrincipales(a);
                            List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioRecuperado);
                            if(armas != null){
                                for(int i = 0; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasPrincipales(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            }

                            Armas a2 = new Armas("RPG-7","Weapon","Base",64,23,12,11,87,65,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0);
                            actualizarCamposArmasSecundarias(a2);
                            if(armas != null){
                                for(int i = 76; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasSecundarias(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            }
                        }
                    });
                    break;
                case "Clase 5":
                    setContentView(R.layout.activity_detalle_clase5);
                    tvNameArma = findViewById(R.id.tvNameArma5);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal5);
                    tvDano = findViewById(R.id.pbDañoArmaPrincipal5);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal5);
                    tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal5);
                    tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal5);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal5);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria5);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria5);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria5);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria5);tvCadencia = findViewById(R.id.pbCadenciaArmaSecundaria5);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria5);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria5);

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 5",usuarioRecuperado);
                            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,11,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1);
                            actualizarCamposArmasPrincipales(a);
                            List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioRecuperado);
                            if(armas != null){
                                for(int i = 0; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasPrincipales(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            }

                            Armas a2 = new Armas("RPG-7","Weapon","Base",64,23,12,11,87,65,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0);
                            actualizarCamposArmasSecundarias(a2);
                            if(armas != null){
                                for(int i = 76; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasSecundarias(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            }
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

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria6);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria6);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria6);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria6);tvCadencia = findViewById(R.id.pbCadenciaArmaSecundaria6);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria6);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria6);

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 6",usuarioRecuperado);
                            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,11,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1);
                            actualizarCamposArmasPrincipales(a);
                            List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioRecuperado);
                            if(armas != null){
                                for(int i = 0; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasPrincipales(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            }

                            Armas a2 = new Armas("RPG-7","Weapon","Base",64,23,12,11,87,65,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0);
                            actualizarCamposArmasSecundarias(a2);
                            if(armas != null){
                                for(int i = 76; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasSecundarias(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            }
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

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria7);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria7);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria7);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria7);tvCadencia = findViewById(R.id.pbCadenciaArmaSecundaria7);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria7);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria7);

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 7",usuarioRecuperado);
                            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,11,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1);
                            actualizarCamposArmasPrincipales(a);
                            List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioRecuperado);
                            if(armas != null){
                                for(int i = 0; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasPrincipales(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            }

                            Armas a2 = new Armas("RPG-7","Weapon","Base",64,23,12,11,87,65,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0);
                            actualizarCamposArmasSecundarias(a2);
                            if(armas != null){
                                for(int i = 76; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasSecundarias(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            }
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

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria8);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria8);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria8);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria8);tvCadencia = findViewById(R.id.pbCadenciaArmaSecundaria8);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria8);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria8);

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 8",usuarioRecuperado);
                            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,11,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1);
                            actualizarCamposArmasPrincipales(a);
                            List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioRecuperado);
                            if(armas != null){
                                for(int i = 0; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasPrincipales(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            }

                            Armas a2 = new Armas("RPG-7","Weapon","Base",64,23,12,11,87,65,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0);
                            actualizarCamposArmasSecundarias(a2);
                            if(armas != null){
                                for(int i = 76; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasSecundarias(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            }
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

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria9);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria9);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria9);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria9);tvCadencia = findViewById(R.id.pbCadenciaArmaSecundaria9);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria9);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria9);

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 9",usuarioRecuperado);
                            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,11,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1);
                            actualizarCamposArmasPrincipales(a);
                            List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioRecuperado);
                            if(armas != null){
                                for(int i = 0; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasPrincipales(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            }

                            Armas a2 = new Armas("RPG-7","Weapon","Base",64,23,12,11,87,65,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0);
                            actualizarCamposArmasSecundarias(a2);
                            if(armas != null){
                                for(int i = 76; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasSecundarias(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            }
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

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria10);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria10);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria10);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria10);tvCadencia = findViewById(R.id.pbCadenciaArmaSecundaria10);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria10);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria10);

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 10",usuarioRecuperado);
                            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,11,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(),1);
                            actualizarCamposArmasPrincipales(a);
                            List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioRecuperado);
                            if(armas != null){
                                for(int i = 0; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasPrincipales(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            }

                            Armas a2 = new Armas("RPG-7","Weapon","Base",64,23,12,11,87,65,"Lanzamisiles",usuarioRecuperado,clase.getId(), 0);
                            actualizarCamposArmasSecundarias(a2);
                            if(armas != null){
                                for(int i = 76; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasSecundarias(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            }

                        }
                    });
                    break;
                default:
                    setContentView(R.layout.activity_detalle_clase1);
                    tvNameArma = findViewById(R.id.tvNameArmaPrincipal);tvPrecision = findViewById(R.id.pbPrecisionArmaPrincipal1);
                    tvDano = findViewById(R.id.pbDañoArmaPrincipal1);
                    tvAlcance = findViewById(R.id.pbAlcanceArmaPrincipal1);
                    tvCadencia = findViewById(R.id.pbCadenciaArmaPrincipal1);
                    tvMovilidad = findViewById(R.id.pbMovilidadArmaPrincipal1);
                    tvControl = findViewById(R.id.pbControlArmaPrincipal1);

                    tvNameArmaSec = findViewById(R.id.tvNombreArmaSecundaria1);tvPrecisionSec = findViewById(R.id.pbPrecisionArmaSecundaria1);tvDanoSec = findViewById(R.id.pbDañoArmaSecundaria1);
                    tvAlcanceSec = findViewById(R.id.pbAlcanceArmaSecundaria1);tvCadencia = findViewById(R.id.pbCadenciaArmaSecundaria1);tvMovilidadSec = findViewById(R.id.pbMovilidadArmaSecundaria1);
                    tvControlSec = findViewById(R.id.pbControlArmaSecundaria1);

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 1",usuarioRecuperado);
                            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,11,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId(), 1);
                            actualizarCamposArmasPrincipales(a);
                            List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioRecuperado);
                            if(armas != null){
                                for(int i = 0; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId(), armaActual.getPrincipal());
                                        actualizarCamposArmasPrincipales(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            }

                           /*Armas a2 = new Armas("RPG-7","Weapon","Base",64,23,12,11,87,65,"Lanzamisiles",usuarioRecuperado,clase.getId());
                            actualizarCamposArmasSecundarias(a2);
                            if(armas != null){
                                for(int i = 76; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId());
                                        actualizarCamposArmasSecundarias(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            }*/
                        }
                    });
                    break;
            }
        }

        bPrimaria = findViewById(R.id.bAccesoriosArmaPrincipal);
        bPrimaria.setOnClickListener(v -> {
            Intent i = new Intent(DetalleClaseActivity.this,AccesoriosActivity.class);
            startActivity(i);
        });

        bEditarClase = findViewById(R.id.ivEditarClase);
        bEditarClase.setOnClickListener(v -> {
            Intent editar = new Intent(DetalleClaseActivity.this, EditarClaseActivity.class);
            startActivity(editar);
        });

    }//Fin onCreate()

    public void actualizarCamposArmasPrincipales(Armas actualizar){
        tvNameArma.setText(actualizar.getName());
        tvPrecision.setProgress(actualizar.getAccuracy()); tvDano.setProgress(actualizar.getDamage());
        tvAlcance.setProgress(actualizar.getRange());tvCadencia.setProgress(actualizar.getFire_rate());
        tvMovilidad.setProgress(actualizar.getMobility()); tvControl.setProgress(actualizar.getControl());
    }

    public void actualizarCamposArmasSecundarias(Armas actualizar){
        tvNameArmaSec.setText(actualizar.getName());
        /*tvPrecisionSec.setProgress(actualizar.getAccuracy()); tvDanoSec.setProgress(actualizar.getDamage());
        tvAlcanceSec.setProgress(actualizar.getRange());tvCadenciaSec.setProgress(actualizar.getFire_rate());
        tvMovilidadSec.setProgress(actualizar.getMobility()); tvControlSec.setProgress(actualizar.getControl());*/
    }
    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String usuario = preferences.getString("user","usuario");
        String contrasena = preferences.getString("contrasena", "contrasenaVacia");
        usuarioRecuperado = usuario;
        contrasenaRecuperada = contrasena;

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
}