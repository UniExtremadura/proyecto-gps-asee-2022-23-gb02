package es.unex.propuesta_proyecto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDataBase;
import es.unex.propuesta_proyecto.model.Usuarios;

/* Esta clase actualiza la cuenta del usuario */

public class ActualizarCuentaActivity extends AppCompatActivity {

    TextView username;
    EditText password;
    Button actualizar,eliminar;//HACER onCLICK()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cuenta);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Perfil");
        }

        username = findViewById(R.id.tvUsuarioActualizar);//username es un Edit Text
        password = findViewById(R.id.etContraseñaActualizar);//password es un Edit Text
        actualizar = findViewById(R.id.bActualizarCuenta);
        eliminar = findViewById(R.id.bEliminarCuenta);

        Bundle parametros = getIntent().getExtras();//extrae el Bundle de la Intent recibida y la guarda en "parametros".
        String usuario = parametros.getString("usuario");//extrae del Bundle el String con la clave "usuario".
        String contrasena = parametros.getString("password");//extrae del Bundle el String con la clave "pass"

        username.setText(usuario);//hace que se muestre en el EditText "username" el String pasado por parametros. En este caso es el nombre del usuario.
        password.setText(contrasena);//hace que se muestre en el EditText "password" el String pasado por parametros. En este caso es la contraseña del usuario.

        /* Cuando se hace click en el boton actualiza */

        actualizar.setOnClickListener(v -> AppExecutors.getInstance().diskIO().execute(() -> {
            Usuarios checkUser;
            checkUser = AppDataBase.getInstance(getApplicationContext()).daoUsuarios().comprobarUsuario(usuario);
            if(checkUser != null){
                checkUser.setName(username.getText().toString());
                checkUser.setPassword(password.getText().toString());
                AppDataBase.getInstance(getApplicationContext()).daoUsuarios().actualizarContrasena(checkUser.getName(),checkUser.getPassword());
                Intent reloggin = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(reloggin);
            }
        }));

        /* Cuando se hace click en el botón elimina */

        eliminar.setOnClickListener(v -> AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Usuarios deleteUser;
                deleteUser = AppDataBase.getInstance(getApplicationContext()).daoUsuarios().comprobarUsuario(usuario);
                AppDataBase.getInstance(getApplicationContext()).daoUsuarios().borrarUsuario(deleteUser);
                    Intent reloggin = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(reloggin);
            }
        }));

    }//Fin onCreate

    /* Cuando se hace click en el botón se desplaza con el intent*/

    public void cuentaActualizada(View view){
        Intent cuentaAct = new Intent(this, ClasesActivity.class);
        startActivity(cuentaAct);
    }
}