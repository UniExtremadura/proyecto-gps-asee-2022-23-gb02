package es.unex.propuesta_proyecto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDatabaseUsuarios;
import es.unex.propuesta_proyecto.model.Usuarios;

public class ActualizarCuentaActivity extends AppCompatActivity {

    TextView username;
    EditText password;
    Button actualizar,eliminar;//HACER onCLICK()


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cuenta);

        username = findViewById(R.id.tvUsuarioActualizar);//username es un Edit Text
        password = findViewById(R.id.etContraseñaActualizar);//password es un Edit Text
        actualizar = findViewById(R.id.bActualizarCuenta);
        eliminar = findViewById(R.id.bEliminarCuenta);

        Bundle parametros = getIntent().getExtras();//extrae el Bundle de la Intent recibida y la guarda en "parametros".
        String usuario = parametros.getString("usuario");//extrae del Bundle el String con la clave "usuario".
        String contrasena = parametros.getString("password");//extrae del Bundle el String con la clave "pass"

        username.setText(usuario);//hace que se muestre en el EditText "username" el String pasado por parametros. En este caso es el nombre del usuario.
        password.setText(contrasena);//hace que se muestre en el EditText "password" el String pasado por parametros. En este caso es la contraseña del usuario.

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Usuarios checkUser;
                        checkUser = AppDatabaseUsuarios.getInstance(getApplicationContext()).daoUsuarios().comprobarUsuario(usuario);
                        Log.d("USUARIO",password.getText().toString());
                        if(checkUser != null){
                            checkUser.setName(username.getText().toString());
                            checkUser.setPassword(password.getText().toString());
                            AppDatabaseUsuarios.getInstance(getApplicationContext()).daoUsuarios().actualizarContrasena(checkUser.getName(),checkUser.getPassword());
                            Intent reloggin = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(reloggin);
                        }
                    }
                });
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Usuarios deleteUser;
                        deleteUser = AppDatabaseUsuarios.getInstance(getApplicationContext()).daoUsuarios().comprobarUsuario(usuario);
                            AppDatabaseUsuarios.getInstance(getApplicationContext()).daoUsuarios().borrarUsuario(deleteUser);
                            Intent reloggin = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(reloggin);
                    }
                });
            }
        });

    }//Fin onCreate

    public void cuentaActualizada(View view){
        Intent cuentaAct = new Intent(this, ClasesActivity.class);
        startActivity(cuentaAct);
    }
}