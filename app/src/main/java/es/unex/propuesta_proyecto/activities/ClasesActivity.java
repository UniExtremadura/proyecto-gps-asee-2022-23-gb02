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

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Clases");
        }

        Bundle parametros = this.getIntent().getExtras();//se recupera el Bundle que viene de la Intent recibida

        usuario = parametros.getString("usuario");//se recupera el nombre del usuario del Bundle de la Intent recibida
        pass = parametros.getString("password");

        rvClases = findViewById(R.id.rvClases);
        rvClases.setLayoutManager(new LinearLayoutManager(this));
        bAgregar = findViewById(R.id.ivAgregar);

        /* Añade tres clases por defecto, que siempre le saldrán a todos los usuarios */

        AppExecutors.getInstance().diskIO().execute(() -> {
            Clases aux;
            Armas principal, secundaria;
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

        /* Este método se encarga de mostrar hasta 10 clases, es decir cuando el usuario presiona el botón "+", se encarga de ir añadiendo clases, hasta un máximo de 10.
        * Para ello comprueba en la base de datos de clases que ese mismo Usuario pueda seguir creando clases */

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
                    for(int i=1; i < numClase; i++){
                        if(clasesTotales.get(i) == null){
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
        //necesito registrar el ArrayList de botones hasta alClases.size()
        // (tengan clase o no, y los que no tengan clase en Room, se hace un alClases.remove(i), y luego se invoca a rvClases.setAdapter())
        AppExecutors.getInstance().diskIO().execute(() -> {
            //comprobar si la clase que está a null es Clase1, Clase2 o Clase3. Si lo es, regenerar por defecto en Room. Si no lo es,
            // eliminar de alClases para que no se muestre en el RecyclerView.
            alClases.clear();
            List <Clases> clasesUsuario = AppDataBase.getInstance(getApplicationContext()).daoClases().obtenerClasesUsuario(usuario);
            for (int i=0; i < clasesUsuario.size(); i++){
                alClases.add(clasesUsuario.get(i).getNombre());
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