package es.unex.propuesta_proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.unex.propuesta_proyecto.R;
import es.unex.propuesta_proyecto.api.DBHelper;

public class RegistroActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button signup,signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        username = findViewById(R.id.etUsuario);
        password = findViewById(R.id.etContraseña);
        repassword = findViewById(R.id.etContraseña1);
        signup = findViewById(R.id.bCrearCuenta);
        signin = findViewById(R.id.bTengoCuenta);
        DB = new DBHelper(this);

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
                        Boolean checkuser = DB.checkUsername(user);
                        if(!checkuser){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert){
                                Toast.makeText(RegistroActivity.this,"Registro completado!", Toast.LENGTH_SHORT).show();
                                    Intent actClasses = new Intent(getApplicationContext(), ClasesActivity.class);
                                    actClasses.putExtra("estado",true);
                                    startActivity(actClasses);
                            }
                            else {
                                Toast.makeText(RegistroActivity.this,"Registro fallido!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegistroActivity.this,"El usuario existe, por favor, inicia sesión.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegistroActivity.this,"Las contraseñas no coinciden !!!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
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