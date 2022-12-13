package es.unex.propuesta_proyecto.activities;

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

/* Esta clase representa el listado de clases que se muestran nada más iniciar sesión Clase 1, clase 2, clase 3...*/

public class ClasesActivity extends AppCompatActivity {

    ArrayList<String> alClases = new ArrayList<String>();
    RecyclerView rvClases;
    ImageView bAgregar;
    String usuario,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);

        Bundle parametros = this.getIntent().getExtras();//se recupera el Bundle que viene de la Intent recibida

        usuario = parametros.getString("usuario");//se recupera el nombre del usuario del Bundle de la Intent recibida
        pass = parametros.getString("password");

        rvClases = findViewById(R.id.rvClases);
        rvClases.setLayoutManager(new LinearLayoutManager(this));
        bAgregar = findViewById(R.id.ivAgregar);

        /* Añade tres clases por defecto, que siempre le saldrán a todos los usuarios */

        AppExecutors.getInstance().diskIO().execute(() -> {
            Clases aux;
            Armas a = new Armas("AK-47","Weapon","Base",64,23,12,5,87,65,"Fusil de asalto",usuario,0, 1, "AK-47");
            Armas a2 = new Armas("RPG-7","Weapon","Base",30,50,20,5,50,50,"Lanzamisiles",usuario,0, 0, "RPG-7");
            if(AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 1",usuario) == null){
                aux = new Clases("Clase 1", usuario,a.getId(),a2.getId());
                a.setIdClase(aux.getId());
                a2.setIdClase(aux.getId());
                AppDataBase.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
           }
            if(AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 2",usuario) == null){
                aux = new Clases("Clase 2",usuario,a.getId(),a.getId());
                a.setIdClase(aux.getId());
                a2.setIdClase(aux.getId());
                AppDataBase.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
            }
            if(AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase 3",usuario) == null){
                aux = new Clases("Clase 3",usuario,a.getId(),a2.getId());
                a.setIdClase(aux.getId());
                a2.setIdClase(aux.getId());
                AppDataBase.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
            }
        });

        alClases.add("Clase 1");
        alClases.add("Clase 2");
        alClases.add("Clase 3");
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<Clases> clasesUser = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClasesUsuario(usuario);
            for(int i = 3; i < clasesUser.size(); i++){
                if(clasesUser.get(i).getNombre().equals("Clase "+(i+1))){
                    alClases.add("Clase "+(i+1));
                }
            }
        });
        rvClases.setAdapter(new ClasesAdapter(alClases));

        /* Este método se encarga de mostrar hasta 10 clases, es decir cuando el usuario presiona el botón "+", se encarga de ir añadiendo clases, hasta un máximo de 10.
        * Para ello comprueba en la base de datos de clases que ese mismo Usuario pueda seguir creando clases */

        bAgregar.setOnClickListener(v -> {
            AppExecutors.getInstance().diskIO().execute(() -> {
                List<Clases> clasesTotales = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClasesUsuario(usuario);
                int numClase = clasesTotales.size();
                Clases aux;
                if(numClase > 8){
                    bAgregar.setVisibility(View.INVISIBLE);
                }
                if(AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase " + (numClase+1),usuario) == null){
                    alClases.add("Clase "+(numClase+1));
                    aux = new Clases("Clase "+(numClase+1),usuario,0,0);
                    AppDataBase.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                }
            });
            rvClases.setAdapter(new ClasesAdapter(alClases)); // Los añade al recyclerView.
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        //necesito registrar el ArrayList de botones hasta alClases.size()(tengan clase o no, y los que no tengan clase en Room, se hace un alClases.remove(i), y luego se invoca a rvClases.setAdapter())
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                //comprobar si la clase que está a null es Clase1, Clase2 o Clase3. Si lo es, regenerar por defecto en Room. Si no lo es, eliminar de alClases para que no se muestre en el RecyclerView.
                for (int i = 0; i < alClases.size(); i++) {
                    //si la clase es null en Room, se comprueba si es de las de por defecto
                    if (AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClase("Clase " + (i + 1), usuario) == null) {
                        Clases aux;
                        if (i == 0) {//si i == 0 es que la clase que esta a null en Room es Clase 1, por lo que se vuelve a rellenar con valores por defecto
                            aux = new Clases("Clase 1", usuario, 0, 0);
                            AppDataBase.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                        } else {
                            if (i == 1) {//si i == 1 es que la clase que esta a null en Room es Clase 2, por lo que se vuelve a rellenar con valores por defecto
                                aux = new Clases("Clase 2", usuario, 0, 0);
                                AppDataBase.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                            } else {
                                if (i == 2) {//si i == 2 es que la clase que esta a null en Room es Clase 3, por lo que se vuelve a rellenar con valores por defecto
                                    aux = new Clases("Clase 3", usuario, 0, 0);
                                    AppDataBase.getInstance(getApplicationContext()).daoClases().insertarClase(aux);
                                } else {
                                    alClases.remove(i);//Elimina el elemento de alClases para que no se muestre en el RecyclerView al invocar a setAdapter()
                                    runOnUiThread(() -> {
                                        if (alClases.size() < 10) {
                                            bAgregar.setVisibility(View.VISIBLE);
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            }
        });//Fin del runnable

        rvClases.setAdapter(new ClasesAdapter(alClases));
    }

    /* Boton de perfil de usuario para poder acceder a editar la contraseña */

    public void perfilUsuario(View view){
        Intent perfil = new Intent(this, ActualizarCuentaActivity.class);
        perfil.putExtra("usuario",usuario);
        perfil.putExtra("password",pass);
        startActivity(perfil);
    }
}