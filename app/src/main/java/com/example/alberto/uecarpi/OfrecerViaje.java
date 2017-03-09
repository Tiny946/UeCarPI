package com.example.alberto.uecarpi;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Alberto on 11/01/2017.
 */
public class OfrecerViaje extends Fragment {
    FirebaseDatabase firebasedb = FirebaseDatabase.getInstance();
    String[] unis = new String[3];
    Button btnHoraDeIr, btnHoraDeVolver, btnOk;
    EditText txtPrecioMes, txtCoche, txtPlazasDisp, txtLugarQuedada;
    TextView horaIr, horaVolver;
    Spinner spinner;
    InfoOfrecerCoche ofrecerCoche;
    int posicion= 0;
    DatabaseReference databaseReference;
    public final static String TAGIDA = "timePickerIda";
    public final static String TAGVUELTA = "timePickVuelta";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3online, container, false);

        btnHoraDeIr = (Button)rootView.findViewById(R.id.buttonHoradeIr);
        btnHoraDeVolver = (Button)rootView.findViewById(R.id.buttonHoraVolver);
        btnOk = (Button)rootView.findViewById(R.id.buttonOK);
        txtPrecioMes = (EditText) rootView.findViewById(R.id.editTextPrecioMes);
        txtCoche = (EditText) rootView.findViewById(R.id.editTextMOdelo);
        txtPlazasDisp = (EditText)rootView.findViewById(R.id.editTextPlazasDisp);
        txtLugarQuedada = (EditText) rootView.findViewById(R.id.editTextLugarQuedada);
        spinner = (Spinner)rootView.findViewById(R.id.spinner);
        horaIr = (TextView)rootView.findViewById(R.id.textViewHoraIr);
        horaVolver = (TextView)rootView.findViewById(R.id.textViewHoraVolver);


        btnHoraDeIr = (Button)rootView.findViewById(R.id.buttonHoradeIr);
        btnHoraDeIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeReloj horaIda = new TimeReloj();
                horaIda.setTv1(horaIr);
                horaIda.show(getFragmentManager(), TAGIDA);

            }
        });

        btnHoraDeVolver = (Button)rootView.findViewById(R.id.buttonHoraVolver);
        btnHoraDeVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeReloj horaVuelta = new TimeReloj();
                horaVuelta.setTv1(horaVolver);
                horaVuelta.show(getFragmentManager(), TAGVUELTA);
            }
        });

    btnOk.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(txtPrecioMes.getText().toString().equals("") || txtCoche.getText().toString().equals("") || txtLugarQuedada.getText().toString().equals("") || txtPlazasDisp.getText().toString().equals("")) {
                Toast.makeText(getContext(),"Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                InfoOfrecerCoche viaje = new InfoOfrecerCoche();
                databaseReference = FirebaseDatabase.getInstance().getReference();
                String Key = databaseReference.child("post").push().getKey();

                viaje.setModelo(txtCoche.getText().toString());
                viaje.setLugarQuedada(txtLugarQuedada.getText().toString());
                viaje.setPlazasDisponibles(Integer.parseInt(txtPlazasDisp.getText().toString()));
                //horas
                viaje.setHoraIr(horaIr.getText().toString());
                viaje.setHoraVolver(horaVolver.getText().toString());
                //
                viaje.setPrecioMes(Double.parseDouble(txtPrecioMes.getText().toString()));
                viaje.setUserid(FirebaseAuth.getInstance().getCurrentUser().getUid());
                viaje.setUni(unis[posicion].toString());
                viaje.setKeyviaje(Key);

                DatabaseReference miRef = firebasedb.getReference(String.format("nuevo_viaje: %s->%s", viaje.getUni(), viaje.getLugarQuedada() ));
                posicion = spinner.getSelectedItemPosition();


                databaseReference.child("Viaje").child(Key).setValue(viaje);
                //miRef.setValue(viaje);

                Toast.makeText(getContext(),"Viaje añadido", Toast.LENGTH_SHORT).show();
            }

        }
    });

        unis[0]="Seleciona tu Universidad:";
        unis[1]="alcobendas";
        unis[2]="vilaviciosa";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, unis);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        return rootView;
    }

}
