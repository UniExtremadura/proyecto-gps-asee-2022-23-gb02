package es.unex.propuesta_proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DetalleClaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_clases);


    }

    public void perfilUsuario(View view){
        Intent perfil = new Intent(this,ActualizarCuentaActivity.class);
        startActivity(perfil);
    }




}