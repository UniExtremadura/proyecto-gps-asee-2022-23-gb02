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

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button btnLogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial);

        username = findViewById(R.id.etUsuarioLogin);
        password = findViewById(R.id.etContraseñaLogin);
        btnLogin = findViewById(R.id.bIniciarSesion);
        DB = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this,"Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkUsernamePassword(user,pass);
                    if(checkuserpass){
                        Toast.makeText(LoginActivity.this,"Ha iniciado sesión!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),ClasesActivity.class);
                        startActivity(intent);
                    } else{
                        Toast.makeText(LoginActivity.this,"Credenciales incorrectas!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void crearCuenta(View view){
        Intent actCrear = new Intent(getApplicationContext(), RegistroActivity.class);
        startActivity(actCrear);
    }

    public void entrarSinCuenta(View view){
        Intent actClassSinCuenta = new Intent(getApplicationContext(), ClasesActivity.class);
        actClassSinCuenta.putExtra("estado",false);
        startActivity(actClassSinCuenta);
    }
}