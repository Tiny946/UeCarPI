package com.example.alberto.uecarpi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alberto on 20/01/2017.
 */
public class InfoOfrecerCoche  implements Parcelable {
    private String uni;
    private String horaIr;
    private String horaVolver;
    private String lugarQuedada;
    private double precioMes;
    private String modelo;
    private int plazasDisponibles;
    private String userid;
    private String keyviaje;
    public InfoOfrecerCoche() {

    }

    public InfoOfrecerCoche(int plazasDisponibles, String modelo, double precioMes, String lugarQuedada, String horaVolver, String horaIr, String uni,String userid, String keyviaje) {
        this.plazasDisponibles = plazasDisponibles;
        this.modelo = modelo;
        this.precioMes = precioMes;
        this.lugarQuedada = lugarQuedada;
        this.horaVolver = horaVolver;
        this.horaIr = horaIr;
        this.uni = uni;
        this.userid = userid;
        this.keyviaje = keyviaje;
    }

    protected InfoOfrecerCoche(Parcel in) {
        uni = in.readString();
        horaIr = in.readString();
        horaVolver = in.readString();
        lugarQuedada = in.readString();
        precioMes = in.readDouble();
        modelo = in.readString();
        plazasDisponibles = in.readInt();
        userid = in.readString();
        keyviaje = in.readString();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public static final Creator<InfoOfrecerCoche> CREATOR = new Creator<InfoOfrecerCoche>() {
        @Override
        public InfoOfrecerCoche createFromParcel(Parcel in) {
            return new InfoOfrecerCoche(in);
        }

        @Override
        public InfoOfrecerCoche[] newArray(int size) {
            return new InfoOfrecerCoche[size];
        }
    };

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecioMes() {
        return precioMes;
    }

    public void setPrecioMes(double precioMes) {
        this.precioMes = precioMes;
    }

    public String getLugarQuedada() {
        return lugarQuedada;
    }

    public void setLugarQuedada(String lugarQuedada) {
        this.lugarQuedada = lugarQuedada;
    }

    public String getHoraVolver() {
        return horaVolver;
    }

    public void setHoraVolver(String horaVolver) {
        this.horaVolver = horaVolver;
    }

    public String getHoraIr() {
        return horaIr;
    }

    public void setHoraIr(String horaIr) {
        this.horaIr = horaIr;
    }

    public String getKeyviaje() {
        return keyviaje;
    }

    public void setKeyviaje(String keyviaje) {
        this.keyviaje = keyviaje;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(uni);
        parcel.writeString(horaIr);
        parcel.writeString(horaVolver);
        parcel.writeString(lugarQuedada);
        parcel.writeDouble(precioMes);
        parcel.writeString(modelo);
        parcel.writeInt(plazasDisponibles);
        parcel.writeString(userid);
        parcel.writeString(keyviaje);
    }
}