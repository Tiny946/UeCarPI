package com.example.alberto.uecarpi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Reservar extends AppCompatActivity {
    FirebaseDatabase firebasedb = FirebaseDatabase.getInstance();
    TextView precio, modelo, destino, plazas, horaIr, horaVolver;
    DatabaseReference databaseReference;
    InfoOfrecerCoche infocoche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);
        Intent i = getIntent();
        infocoche = i.getParcelableExtra(BuscarViaje.DATOS_VIAJE);
        destino = (TextView) findViewById(R.id.lbl_destino);
        modelo = (TextView) findViewById(R.id.lbl_modelo);
        precio = (TextView) findViewById(R.id.lbl_precio);
        plazas = (TextView) findViewById(R.id.lbl_plazas);
        horaIr = (TextView) findViewById(R.id.textViewHoraIr);
        horaVolver = (TextView) findViewById(R.id.textViewHoraVolver);

        destino.setText("Destino:" + infocoche.getUni());
        modelo.setText("Modelo: " + infocoche.getModelo());
        precio.setText("Precio: " + infocoche.getPrecioMes());
        plazas.setText("Plazas disponibles: " + infocoche.getPlazasDisponibles());
        horaIr.setText("Hora Ir: " + infocoche.getHoraIr());
        horaVolver.setText("Hora Volver: " + infocoche.getHoraVolver());



    }

    public void hacerReserva(View v) {
        if (infocoche.getPlazasDisponibles() == 0) {

            Toast.makeText(this, "NO HAY PLAZAS SUFCIENTES", Toast.LENGTH_LONG).show();

        } else {


        databaseReference = FirebaseDatabase.getInstance().getReference();
        String Key = databaseReference.child("post").push().getKey();


        int reservas = 1;

        int prestantes = infocoche.getPlazasDisponibles() - reservas;


        infocoche.setPlazasDisponibles(prestantes);
        databaseReference.child("Viaje").child(infocoche.getKeyviaje()).setValue(infocoche);

            infocoche.setPlazasDisponibles(reservas);
            infocoche.setUserid(FirebaseAuth.getInstance().getCurrentUser().getUid());
            databaseReference.child("Reserva").child(Key).setValue(infocoche);
        Toast.makeText(this, "RESERVA REALIAZADA CORRECTAMENTE", Toast.LENGTH_LONG).show();

        finish();


    }
    }

}