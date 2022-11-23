package es.unex.propuesta_proyecto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDatabaseUsuarios;
import es.unex.propuesta_proyecto.model.Usuarios;

public class ActualizarCuentaActivity extends AppCompatActivity {

    EditText username,password;
    Button actualizar;//HACER onCLICK()


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cuenta);

        username = findViewById(R.id.etUsuarioActualizar);//username es un Edit Text
        password = findViewById(R.id.etContraseñaActualizar);//password es un Edit Text

        Bundle parametros = getIntent().getExtras();//extrae el Bundle de la Intent recibida y la guarda en "parametros".
        String usuario = parametros.getString("usuario");//extrae del Bundle el String con la clave "usuario".
        String contrasena = parametros.getString("contraseña");//extrae del Bundle el String con la clave "contraseña"

        username.setText(usuario);//hace que se muestre en el EditText "username" el String pasado por parametros. En este caso es el nombre del usuario.
        password.setText(contrasena);//hace que se muestre en el EditText "password" el String pasado por parametros. En este caso es la contraseña del usuario.

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Usuarios usuarios = new Usuarios();
                AppDatabaseUsuarios.getInstance(getApplicationContext()).daoUsuarios().actualizarUsuario(usuarios);
            }
        });
    }//Fin onCreate

    public void cuentaActualizada(View view){
        Intent cuentaAct = new Intent(this, ClasesActivity.class);
        startActivity(cuentaAct);
    }
}