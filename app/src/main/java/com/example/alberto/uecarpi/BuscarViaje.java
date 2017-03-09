package com.example.alberto.uecarpi;

/**
 * Created by Alberto on 11/01/2017.
 */

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class BuscarViaje extends Fragment implements AdapterView.OnItemClickListener {
    String[] unis = new String[3];
    public static final String DATOS_VIAJE = "datos_viaje";
    Spinner spinner;
    ListView listView;
    SearchView buscar;
    private final String TAG = BuscarViaje.class.getSimpleName();
    ArrayList<InfoOfrecerCoche> datosViaje;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference miBd;
    ChildEventListener childEventListener;
    Query query;
    private InfoOfrecerCoche infoOfrecerCoche;


    InfoOfrecerCoche[] viajes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*
        View rootView = inflater.inflate(R.layout.tab1contacts, container, false);




        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);


        listView = (ListView)rootView.findViewById(R.id.listaViajes);

        BuscarViajeAdapter adapter2 = new BuscarViajeAdapter(this, viajes);
        listView.setAdapter(adapter2);
*/
        return inflater.inflate(R.layout.tab1contacts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = (Spinner) view.findViewById(R.id.spinner2);
        listView = (ListView) view.findViewById(R.id.listaViajes);
        String[] unis = {"Seleciona tu Universidad:", "alcobendas", "vilaviciosa"};
        ArrayAdapter<String> sadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, unis);
        spinner.setAdapter(sadapter);
        datosViaje = new ArrayList<>();

        buscar = (SearchView) view.findViewById(R.id.searchView);
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        String nombre = usuario.getDisplayName();
        String uid = usuario.getUid();
        String email = usuario.getEmail();

        query = database.getReference("Viaje");
        childEventListener = new ChildEventListener() {
            BuscarViajeAdapter adapter;

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                infoOfrecerCoche = dataSnapshot.getValue(InfoOfrecerCoche.class);
                datosViaje.add(infoOfrecerCoche);
                adapter = new BuscarViajeAdapter(getContext(), datosViaje);
                listView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                infoOfrecerCoche = dataSnapshot.getValue(InfoOfrecerCoche.class);
                boolean a = true;
                int pos = 0;
                for (int i = 0; i < datosViaje.size(); i++) {
                    Log.d(BuscarViaje.class.getSimpleName(), infoOfrecerCoche.getKeyviaje() + "  " + datosViaje.get(i).getKeyviaje());
                    if (datosViaje.get(i).getKeyviaje() != null) {
                        if (infoOfrecerCoche.getKeyviaje().equals(datosViaje.get(i).getKeyviaje())) {
                            pos = i;
                        }
                    }

                }
                Log.d(BuscarViaje.class.getSimpleName(), "" + pos);
                datosViaje.remove(pos);
                datosViaje.add(pos, infoOfrecerCoche);
                adapter = new BuscarViajeAdapter(getContext(), datosViaje);
                listView.setAdapter(adapter);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                //Toast.makeText(getActivity(), "Error al cargar el viaje", Toast.LENGTH_SHORT).show();
            }
        };
        query.addChildEventListener(childEventListener);

        BuscarViajeAdapter adapter = new BuscarViajeAdapter(getContext(), datosViaje);
        listView.setOnItemClickListener(this);

        listView.setAdapter(adapter);
        //listView.setOnClickListener(this);
    }

    //metodo para pasar a la otra pantalla y ver ek viaje con toda la info
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), Reservar.class);
        intent.putExtra(DATOS_VIAJE, datosViaje.get(i));
        startActivity(intent);
    }

}
