package es.unex.propuesta_proyecto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.dao.AppDatabase;

public class DetalleClaseActivity extends AppCompatActivity {

    Button bPrimaria, bSecundaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Armas").allowMainThreadQueries().build();

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

        bPrimaria = findViewById(R.id.bAccesoriosArmaPrincipal);
        bPrimaria.setOnClickListener(v -> {
            Intent i = new Intent(DetalleClaseActivity.this,AccesoriosActivity.class);
            // Valores intent primaria
            startActivity(i);
        });
        bSecundaria = findViewById(R.id.bAccesoriosArmaSecundaria);
        bSecundaria.setOnClickListener(v -> {
            Intent i = new Intent(DetalleClaseActivity.this,AccesoriosActivity.class);
            // Valores intent secundaria
            startActivity(i);
        });
    }

    public void cambiarArmaPrincipal(View view){
        Intent editar = new Intent(this, ArmasPrincipalesActivity.class);
        //editar.putExtra();
        startActivity(editar);
    }


    public void perfilUsuario(View view){
        Intent perfil = new Intent(this, ActualizarCuentaActivity.class);
        startActivity(perfil);
    }






}