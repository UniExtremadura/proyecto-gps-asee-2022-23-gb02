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
    String usuarioRecuperado;

    TextView tvNameArma;
    ProgressBar tvPrecision,tvDano,tvAlcance,tvCadencia,tvMovilidad,tvControl;

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

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            Clases clase = AppDatabaseClases.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 2",usuarioRecuperado);
                            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,11,87,65,"Fusil de asalto",usuarioRecuperado,clase.getId());
                            actualizarCamposArmasPrincipales(a);
                            List<Armas> armas = AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().obtenerArmasPorNombreUsuario(usuarioRecuperado);
                            if(armas != null){
                                for(int i = 0; i < armas.size() ; i++){
                                    if(armas.get(i).getIdClase() == clase.getId()){ // Seria el arma del usuario en esta clase
                                        Armas armaActual = armas.get(i);
                                        AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().actualizarArma(armaActual.getName(),armaActual.getType(),armaActual.getSubtype(),armaActual.getAccuracy(),armaActual.getDamage(),armaActual.getRange(),armaActual.getFire_rate(),armaActual.getMobility(),armaActual.getControl(), armaActual.getId(),clase.getId());
                                        actualizarCamposArmasPrincipales(armaActual);
                                    }
                                }
                            } else { // No tiene ningún arma
                                AppDatabaseArmas.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            }
                        }
                    });
                    break;
                case "Clase 3":
                    setContentView(R.layout.activity_detalle_clase3);
                    break;
                case "Clase 4":
                    setContentView(R.layout.activity_detalle_clase4);
                    break;
                case "Clase 5":
                    setContentView(R.layout.activity_detalle_clase5);
                    break;
                case "Clase 6":
                    setContentView(R.layout.activity_detalle_clase6);
                    break;
                case "Clase 7":
                    setContentView(R.layout.activity_detalle_clase7);
                    break;
                case "Clase 8":
                    setContentView(R.layout.activity_detalle_clase8);
                    break;
                case "Clase 9":
                    setContentView(R.layout.activity_detalle_clase9);
                    break;
                case "Clase 10":
                    setContentView(R.layout.activity_detalle_clase10);
                    break;
                default:
                    setContentView(R.layout.activity_detalle_clase1);

                    break;
            }
        }

        bPrimaria = findViewById(R.id.bAccesoriosArmaPrincipal);
        bPrimaria.setOnClickListener(v -> {
            Intent i = new Intent(DetalleClaseActivity.this,AccesoriosActivity.class);
            // Valores intent primaria
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
        tvPrecision.setProgress(actualizar.getAccuracy()); tvDano.setProgress(actualizar.getDamage()); tvAlcance.setProgress(actualizar.getRange());tvCadencia.setProgress(actualizar.getFire_rate());
        tvMovilidad.setProgress(actualizar.getMobility()); tvControl.setProgress(actualizar.getControl());
    }
    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String usuario = preferences.getString("user","usuario");
        usuarioRecuperado = usuario;

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
}