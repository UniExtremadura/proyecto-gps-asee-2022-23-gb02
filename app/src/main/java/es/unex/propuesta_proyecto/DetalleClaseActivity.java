package es.unex.propuesta_proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DetalleClaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null) {
            int valor = parametros.getInt("className");
            switch (valor) {
                case 2:
                    setContentView(R.layout.activity_detalle_clase2);
                    break;
                case 3:
                    setContentView(R.layout.activity_detalle_clase3);
                    break;
                case 4:
                    setContentView(R.layout.activity_detalle_clase4);
                    break;
                case 5:
                    setContentView(R.layout.activity_detalle_clase5);
                    break;
                case 6:
                    setContentView(R.layout.activity_detalle_clase6);
                    break;
                case 7:
                    setContentView(R.layout.activity_detalle_clase7);
                    break;
                case 8:
                    setContentView(R.layout.activity_detalle_clase8);
                    break;
                case 9:
                    setContentView(R.layout.activity_detalle_clase9);
                    break;
                case 10:
                    setContentView(R.layout.activity_detalle_clase10);
                    break;
                default:
                    setContentView(R.layout.activity_detalle_clases);
                    break;
            }
        }

    }

    public void perfilUsuario(View view){
        Intent perfil = new Intent(this,ActualizarCuentaActivity.class);
        startActivity(perfil);
    }




}