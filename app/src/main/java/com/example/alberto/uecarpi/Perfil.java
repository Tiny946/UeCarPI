package com.example.alberto.uecarpi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

/**
 * Created by Alberto on 11/01/2017.
 */
public class Perfil extends Fragment{

    public static final String DATOS_VIAJE = "datos_viaje";

    ListView listView;

    private final String TAG = BuscarViaje.class.getSimpleName();
    ArrayList<InfoOfrecerCoche> datosViaje;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference miBd;
    ChildEventListener childEventListener;
    Query query,query2;
    private InfoOfrecerCoche infoOfrecerCoche;


    InfoOfrecerCoche[] viajes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String viaje =(String);
                AlertDialog.Builder builder = new AlertDialog.Builder(Perfil.this)
                        .setMessage("Desea borrar la tarea?")
                        .setTitle("Por favor, confirme.")
                        .setNegativeButton(getString(android.R.string.no), null)
                        .setPositiveButton(getString(android.R.string.yes),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        FirebaseDatabase firebaseDatabase = new FirebaseDatabase(Perfil.this);
                                        firebaseDatabase.delete(viaje);
                                    }
                                });
                return true;

            }
        });*/

        return inflater.inflate(R.layout.tab1contacts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listaViajes);
        datosViaje  = new ArrayList<>();
        FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
        String nombre = usuario.getDisplayName();
        String uid = usuario.getUid();
        String email = usuario.getEmail();

        query = database.getReference("Viaje").orderByChild("userid").equalTo(uid);
        query2 = database.getReference("Reserva").orderByChild("userid").equalTo(uid);
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
        query2.addChildEventListener(childEventListener);

    }



}

