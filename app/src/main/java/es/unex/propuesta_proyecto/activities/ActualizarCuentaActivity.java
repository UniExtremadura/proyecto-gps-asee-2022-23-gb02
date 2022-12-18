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

public class ActualizarCuentaActivity extends AppCompatActivity {

    TextView username;
    EditText password;
    Button actualizar,eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cuenta);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Perfil");
        }

        username = findViewById(R.id.tvUsuarioActualizar);
        password = findViewById(R.id.etContraseñaActualizar);
        actualizar = findViewById(R.id.bActualizarCuenta);
        eliminar = findViewById(R.id.bEliminarCuenta);

        Bundle parametros = getIntent().getExtras();
        String usuario = parametros.getString("usuario");
        String contrasena = parametros.getString("password");

        username.setText(usuario);
        password.setText(contrasena);

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

    }

    public void cuentaActualizada(View view){
        Intent cuentaAct = new Intent(this, ClasesActivity.class);
        startActivity(cuentaAct);
    }
}