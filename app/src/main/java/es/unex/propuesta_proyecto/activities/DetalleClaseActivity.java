package es.unex.propuesta_proyecto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import es.unex.propuesta_proyecto.R;

public class DetalleClaseActivity extends AppCompatActivity {
    ImageView bEditArPrin;
    ImageView bEditArSec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null) {
            String valor = parametros.getString("className");
            switch (valor) {
                case "Clase 2":
                    setContentView(R.layout.activity_detalle_clase2);
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

        bEditArPrin = findViewById(R.id.ivEditarArmaPrincipal);
        bEditArPrin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent armaP = new Intent(DetalleClaseActivity.this, ArmasPrincipalesActivity.class);
                startActivity(armaP);
            }
        });

        bEditArSec = findViewById(R.id.ivEditarArmaSecundaria);
        bEditArSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent armaS = new Intent(DetalleClaseActivity.this, ArmasSecundariasActivity.class);
                startActivity(armaS);
            }
        });


    }

    public void perfilUsuario(View view) {
        Intent perfil = new Intent(this, ActualizarCuentaActivity.class);
        startActivity(perfil);
    }

}