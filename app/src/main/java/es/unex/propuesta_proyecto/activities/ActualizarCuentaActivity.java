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
    Button actualizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cuenta);

        username = findViewById(R.id.etUsuarioActualizar);
        password = findViewById(R.id.etContraseñaActualizar);

        String usuario;
        Bundle parametros = getIntent().getExtras();
        usuario = parametros.getString("usuario");

        username.setText(usuario);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Usuarios usuarios = new Usuarios();
                AppDatabaseUsuarios.getInstance(getApplicationContext()).daoUsuarios().actualizarUsuario(usuarios);
            }
        });

    }

    public void cuentaActualizada(View view){
        Intent cuentaAct = new Intent(this, ClasesActivity.class);
        startActivity(cuentaAct);
    }
}