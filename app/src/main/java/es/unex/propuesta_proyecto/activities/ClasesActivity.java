package es.unex.propuesta_proyecto.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDataBase;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.Clases;

public class ClasesActivity extends AppCompatActivity {

    ArrayList<String> alClases = new ArrayList<String>();
    RecyclerView rvClases;
    ImageView bAgregar;
    String usuario,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Clases");
        }

        Bundle parametros = this.getIntent().getExtras();

        usuario = parametros.getString("usuario");
        pass = parametros.getString("password");

        rvClases = findViewById(R.id.rvClases);
        rvClases.setLayoutManager(new LinearLayoutManager(this));
        bAgregar = findViewById(R.id.ivAgregar);


        AppExecutors.getInstance().diskIO().execute(() -> {
            Clases aux;
            int idClass, idArmaPrinc, idArmaSec;;
            if(AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 1",usuario) == null){
                aux = new Clases("Clase 1", usuario, 0, 0);
                AppDataBase.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                idClass = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase(aux.getNombre(), usuario).getId();

                Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuario, idClass, 1);
                Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuario, idClass, 0);
                AppDataBase.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                AppDataBase.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                idArmaPrinc = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(idClass, 1);
                idArmaSec = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(idClass, 0);

                AppDataBase.getInstance(getApplicationContext()).daoClases().actualizarIdArmas(idArmaPrinc, idArmaSec, idClass);
           }
            if(AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 2",usuario) == null){
                aux = new Clases("Clase 2",usuario, 0,0);
                AppDataBase.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                idClass = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase(aux.getNombre(), usuario).getId();

                Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuario, idClass, 1);
                Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuario, idClass, 0);
                AppDataBase.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                AppDataBase.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);

                idArmaPrinc = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(idClass, 1);
                idArmaSec = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(idClass, 0);
                AppDataBase.getInstance(getApplicationContext()).daoClases().actualizarIdArmas(idArmaPrinc, idArmaSec, idClass);
            }
            if(AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 3",usuario) == null){
                aux = new Clases("Clase 3",usuario,0 ,0);
                AppDataBase.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                idClass = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase(aux.getNombre(), usuario).getId();

                Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuario, idClass, 1);
                Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuario, idClass, 0);
                AppDataBase.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                AppDataBase.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);

                idArmaPrinc = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(idClass, 1);
                idArmaSec = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(idClass, 0);
                AppDataBase.getInstance(getApplicationContext()).daoClases().actualizarIdArmas(idArmaPrinc, idArmaSec, idClass);
            }
        });

        alClases.add("Clase 1");
        alClases.add("Clase 2");
        alClases.add("Clase 3");
        rvClases.setAdapter(new ClasesAdapter(alClases));

        bAgregar.setOnClickListener(v -> {
            AppExecutors.getInstance().diskIO().execute(() -> {
                List<Clases> clasesTotales = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClasesUsuario(usuario);
                int numClase = clasesTotales.size();
                Clases aux;
                int idClass, idArmaPrinc, idArmaSec;
                if(AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase " + (numClase+1),usuario) == null){
                    alClases.add("Clase "+(numClase+1));
                    aux = new Clases("Clase "+(numClase+1),usuario,0,0);
                    AppDataBase.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                    idClass = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase(aux.getNombre(), usuario).getId();
                    Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuario, idClass, 1);
                    Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuario, idClass, 0);
                    AppDataBase.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                    AppDataBase.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);

                    idArmaPrinc = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(idClass, 1);
                    idArmaSec = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(idClass, 0);
                    AppDataBase.getInstance(getApplicationContext()).daoClases().actualizarIdArmas(idArmaPrinc, idArmaSec, idClass);
                }else{
                    List<Clases> clases = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClasesUsuario(usuario);
                    boolean ins = false;
                    for(int i=1; i < numClase; i++){
                        if(!clases.get(i).getNombre().equals("Clase "+i) && !ins){
                            String nombreUlt = clases.get(clases.size()-1).getNombre();
                            char [] cadena_div = nombreUlt.toCharArray();
                            String num = "";
                            for(int j=0; j < cadena_div.length; j++){
                                if(Character.isDigit(cadena_div[j])){
                                    num+=cadena_div[j];
                                }
                            }
                            int nextClass = Integer.parseInt(num);
                            alClases.add("Clase "+(nextClass+1));
                            aux = new Clases("Clase "+(nextClass+1),usuario,0,0);
                            AppDataBase.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                            ins = true;
                            idClass = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase(aux.getNombre(), usuario).getId();
                            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuario, idClass, 1);
                            Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuario, idClass, 0);
                            AppDataBase.getInstance(getApplicationContext()).daoJuego().insertarArmas(a);
                            AppDataBase.getInstance(getApplicationContext()).daoJuego().insertarArmas(a2);
                            idArmaPrinc = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(idClass, 1);
                            idArmaSec = AppDataBase.getInstance(getApplicationContext()).daoJuego().getIdArmaTipo(idClass, 0);
                            AppDataBase.getInstance(getApplicationContext()).daoClases().actualizarIdArmas(idArmaPrinc, idArmaSec, idClass);
                        }
                    }
                }
            });
            rvClases.setAdapter(new ClasesAdapter(alClases)); // Los añade al recyclerView.
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        AppExecutors.getInstance().diskIO().execute(() -> {
            alClases.clear();
            List <Clases> clasesUsuario = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClasesUsuario(usuario);
            for (int i=0; i < clasesUsuario.size(); i++){
                alClases.add(clasesUsuario.get(i).getNombre());
            }

        });
        rvClases.setAdapter(new ClasesAdapter(alClases));
    }

    public void perfilUsuario(View view){
        Intent perfil = new Intent(this, ActualizarCuentaActivity.class);
        perfil.putExtra("usuario",usuario);
        perfil.putExtra("password",pass);
        startActivity(perfil);
    }
}