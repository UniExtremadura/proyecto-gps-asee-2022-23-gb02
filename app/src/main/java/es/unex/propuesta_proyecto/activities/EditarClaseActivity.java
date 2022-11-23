package es.unex.propuesta_proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDatabase;

public class EditarClaseActivity extends AppCompatActivity {

    ImageView bCorrecto;//este es el icono del tick

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_clases_editar);
        //mostrarArmas();
        ImageView button;
        button = findViewById(R.id.ivEditarArmaPrincipal);
        //GetIntent
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent editar = new Intent(EditarClaseActivity.this,DetalleClaseActivity.class);
                TextView tvNameArma;
                tvNameArma = findViewById(R.id.tvNameArma);
                String name = tvNameArma.getText().toString();

                AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Armas").build();

                appDatabase.daoJuego().actualizarClase("ARMA DAVID","D1","",1,42,23,12,3,4,1);

                finish();
            }
        });

        bCorrecto = findViewById(R.id.ivEditarArmaPrincipal);
        bCorrecto.setOnClickListener(v-> {
            finish(); 
        });

    }//Fin onCreate()




    public void editarArmaPrincipal(View view) {
        TextView tvNameArma;
        tvNameArma = findViewById(R.id.tvNameArma);
        String name = tvNameArma.getText().toString();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}