package com.example.alberto.uecarpi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Alberto on 24/01/2017.
 */
public class BuscarViajeAdapter extends BaseAdapter{

    private Context mContext;
    ArrayList <InfoOfrecerCoche> viajes;

    public BuscarViajeAdapter(Context mContext, ArrayList<InfoOfrecerCoche> viajes) {
        this.mContext = mContext;
        this.viajes = viajes;
    }

    @Override
    public int getCount() {
        return viajes.size();
    }

    @Override
    public Object getItem(int position) {
        return viajes.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item, null);

            holder = new ViewHolder();
            holder.lugarQuedada = (TextView) convertView.findViewById(R.id.textViewNombre);
            holder.precio = (TextView) convertView.findViewById(R.id.textViewPrecio);
            holder.numPlazas=(TextView)convertView.findViewById(R.id.textViewNumPlazas);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lugarQuedada.setText(viajes.get(position).getLugarQuedada());

        holder.precio.setText(viajes.get(position).getPrecioMes()+"€");

        holder.numPlazas.setText("Plazas disponibles: "+viajes.get(position).getPlazasDisponibles());

        if(viajes.get(position).getPlazasDisponibles()==0) {
            holder.numPlazas.setTextColor(Color.parseColor("#FF0000"));
        }else {
            holder.numPlazas.setTextColor(Color.parseColor("#000000"));
        }


        return convertView;
    }


    private class ViewHolder{
        TextView numPlazas,precio, lugarQuedada;

    }
}
