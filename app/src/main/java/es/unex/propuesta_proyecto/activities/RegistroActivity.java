package es.unex.propuesta_proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.AppExecutors;
import es.unex.propuesta_proyecto.dao.AppDatabaseUsuarios;
import es.unex.propuesta_proyecto.model.Usuarios;

public class RegistroActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup,signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        username = findViewById(R.id.etUsuario);//EditText
        password = findViewById(R.id.etContraseña);//EditText
        repassword = findViewById(R.id.etContraseña1);//EditText
        signup = findViewById(R.id.bCrearCuenta);//Button
        signin = findViewById(R.id.bTengoCuenta);//Button

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(RegistroActivity.this,"Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                Usuarios usuario;
                                usuario = AppDatabaseUsuarios.getInstance(RegistroActivity.this).daoUsuarios().comprobarUsuario(user);
                                if(usuario == null){
                                    usuario = new Usuarios(user,pass);
                                    AppDatabaseUsuarios.getInstance(getApplicationContext()).daoUsuarios().insertarUsuario(usuario);
                                    Intent actClasses = new Intent(getApplicationContext(), LoginActivity.class);
                                    actClasses.putExtra("estado",true);

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "Se ha registrado!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    startActivity(actClasses);
                                }
                                else{
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegistroActivity.this,"El usuario existe, por favor, inicia sesión.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(RegistroActivity.this,"Las contraseñas no coinciden !!!", Toast.LENGTH_SHORT).show();
                    }
                }

            }//Fin onClick()
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}