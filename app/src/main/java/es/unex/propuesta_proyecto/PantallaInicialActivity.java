package es.unex.propuesta_proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class PantallaInicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial);
    }

    public void iniciarSesion(View view){
        Intent actClasses = new Intent(this,ClasesActivity.class);
        actClasses.putExtra("estado",true);
        startActivity(actClasses);
    }

    public void crearCuenta(View view){
        Intent actCrear = new Intent(this,CrearCuentaActivity.class);
        startActivity(actCrear);
    }

    public void entrarSinCuenta(View view){
        Intent actClassSinCuenta = new Intent(this,ClasesActivity.class);
        actClassSinCuenta.putExtra("estado",false);
        startActivity(actClassSinCuenta);
    }

}