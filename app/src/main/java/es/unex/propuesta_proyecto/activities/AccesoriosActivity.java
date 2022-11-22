package es.unex.propuesta_proyecto.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import es.unex.propuesta_proyecto.R;

public class AccesoriosActivity extends AppCompatActivity {

    Spinner sBocacha;
    Spinner sCañon;
    Spinner sLaser;
    Spinner sMira;
    Spinner sCulata;
    Spinner sAcople;
    Spinner sMunicion;
    Spinner sEmpuñaduraTrasera;
    Spinner sVentaja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesorios);

        // Carga del Spinner de las bocachas
        sBocacha = findViewById(R.id.sBocacha);
        sBocacha.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.bocachas, android.R.layout.simple_spinner_item));

        // Carga del Spinner de los cañones
        sCañon = findViewById(R.id.sCañon);
        sCañon.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.cañon, android.R.layout.simple_spinner_item));

        // Carga del Spinner de los laser
        sLaser = findViewById(R.id.sLaser);
        sLaser.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.laser, android.R.layout.simple_spinner_item));

        // Carga del Spinner de las miras
        sMira = findViewById(R.id.sMira);
        sMira.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.mira, android.R.layout.simple_spinner_item));

        // Carga del Spinner de las culatas
        sCulata = findViewById(R.id.sCulata);
        sCulata.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.culata, android.R.layout.simple_spinner_item));

        // Carga del Spinner de los acoples
        sAcople = findViewById(R.id.sAcople);
        sAcople.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.acople, android.R.layout.simple_spinner_item));

        // Carga del Spinner de las municiones
        sMunicion = findViewById(R.id.sMunicion);
        sMunicion.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.municion, android.R.layout.simple_spinner_item));

        // Carga del Spinner de las empuñaduras traseras
        sEmpuñaduraTrasera = findViewById(R.id.sEmpuñaduraTrasera);
        sEmpuñaduraTrasera.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.empuñaduraTrasera, android.R.layout.simple_spinner_item));

        // Carga del Spinner de las ventajas
        sVentaja = findViewById(R.id.sVentaja);
        sVentaja.setAdapter(ArrayAdapter
                .createFromResource(this, R.array.ventaja, android.R.layout.simple_spinner_item));

    }

}