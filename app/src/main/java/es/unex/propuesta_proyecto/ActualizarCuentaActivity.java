package es.unex.propuesta_proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActualizarCuentaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cuenta);
    }

    public void cuentaActualizada(View view){
        Intent cuentaAct = new Intent(this,ClasesActivity.class);
        startActivity(cuentaAct);
    }
}