package es.unex.propuesta_proyecto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.dao.AppDatabase;

public class DetalleClaseActivity extends AppCompatActivity {

    Button bPrimaria;
    ImageView bEditarClase;//este es el icono del lapiz para editar la clase
    TextView bArma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String nombreArma;
        super.onCreate(savedInstanceState);
        Bundle parametros = this.getIntent().getExtras();//se recupera el Bundle de la Intent recibida
        Boolean encontrado = parametros.getBoolean("NOMBREBOOL");
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
        bArma = findViewById(R.id.tvNombre1);
        if(!encontrado){ nombreArma = "AKKK-47";}
        else {nombreArma = parametros.getString("NOMBREARMA");}
        Log.d("nombreDet",nombreArma);
        bArma.setText(nombreArma);

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

    public void cambiarArmaPrincipal(View view){
        Intent editar = new Intent(this, ArmasPrincipalesActivity.class);
        startActivity(editar);
    }
}