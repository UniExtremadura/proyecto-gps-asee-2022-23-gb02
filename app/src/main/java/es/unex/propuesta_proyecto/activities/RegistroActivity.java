package es.unex.propuesta_proyecto.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDataBase;
import es.unex.propuesta_proyecto.model.Usuarios;

public class RegistroActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup,signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Registro");
        }

        username = findViewById(R.id.etUsuario);
        password = findViewById(R.id.etContraseña);
        repassword = findViewById(R.id.etContraseña1);
        signup = findViewById(R.id.bCrearCuenta);
        signin = findViewById(R.id.bTengoCuenta);


        signup.setOnClickListener(v -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();
            if(user.equals("")||pass.equals("")||repass.equals(""))
                Toast.makeText(RegistroActivity.this,"Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
            else{
                if(pass.equals(repass)){
                    AppExecutors.getInstance().diskIO().execute(() -> {
                        Usuarios usuario;
                        usuario = AppDataBase.getInstance(RegistroActivity.this).daoUsuarios().comprobarUsuario(user);
                        if(usuario == null){
                            usuario = new Usuarios(user,pass);
                            AppDataBase.getInstance(getApplicationContext()).daoUsuarios().insertarUsuario(usuario);
                            Intent actClasses = new Intent(getApplicationContext(), LoginActivity.class);
                            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Se ha registrado!", Toast.LENGTH_SHORT).show());
                            startActivity(actClasses);
                        }
                        else{
                            runOnUiThread(() -> Toast.makeText(RegistroActivity.this,"El usuario existe, por favor, inicia sesión.", Toast.LENGTH_SHORT).show());
                        }
                    });
                }
                else{
                    Toast.makeText(RegistroActivity.this,"Las contraseñas no coinciden !!!", Toast.LENGTH_SHORT).show();
                }
            }

        });

        signin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        });
    }
}