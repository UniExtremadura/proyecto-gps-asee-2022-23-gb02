package es.unex.propuesta_proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class ClasesActivity extends AppCompatActivity {

    RecyclerView rvClases;
    ArrayList<String> alClases = new ArrayList<String>();
    private Button botonClase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros!=null){
            ImageView perfil = findViewById(R.id.ivUsuario);
            boolean estado = parametros.getBoolean("estado");
            if(estado){ // True -- visible
                perfil.setVisibility(View.VISIBLE);
            }else { // False -- invisible
                perfil.setVisibility(View.INVISIBLE);
            }
        }
        rvClases = findViewById(R.id.rvClases);
        rvClases.setLayoutManager(new LinearLayoutManager(this));

        alClases.add("Clase 1");
        alClases.add("Clase 2");
        alClases.add("Clase 3");
        alClases.add("Clase 4");
        alClases.add("Clase 5");
        alClases.add("Clase 6");
        alClases.add("Clase 7");
        alClases.add("Clase 8");
        alClases.add("Clase 9");
        alClases.add("Clase 10");

        ClasesAdapter clasesAdapter = new ClasesAdapter(alClases);
        rvClases.setAdapter(clasesAdapter);
        // Acceso a clases
        botonClase = rvClases.findViewById(R.id.bClase);

    }
// Mirar onclickListener (1 para cada clase (1 diseño para cada clase))
    public void accederClases(View view){ // TODO - Acceso a las diferentes clases
        Intent actClase = new Intent(this, DetalleClaseActivity.class);
        /*String buttonText = botonClase.getTag().toString();
        switch (buttonText){
            case "Clase 2":
                actClase.putExtra("className", 2);
                break;
            case "Clase 3":
                actClase.putExtra("className", 3);
                break;
            case "Clase 4":
                actClase.putExtra("className", 4);
                break;
            case "Clase 5":
                actClase.putExtra("className", 5);
                break;
            case "Clase 6":
                actClase.putExtra("className", 6);
                break;
            case "Clase 7":
                actClase.putExtra("className", 7);
                break;
            case "Clase 8":
                actClase.putExtra("className", 8);
                break;
            case "Clase 9":
                actClase.putExtra("className", 9);
                break;
            case "Clase 10":
                actClase.putExtra("className", 10);
                break;
            default:
                actClase.putExtra("className", 1);
                break;
        }*/
        startActivity(actClase);
    }

    public void perfilUsuario(View view){
        Intent perfil = new Intent(this,ActualizarCuentaActivity.class);
        startActivity(perfil);
    }
}