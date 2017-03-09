package com.example.alberto.uecarpi;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Alberto on 22/02/2017.
 */
public class TimeReloj extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    private TextView tv1;

    public void setTv1(TextView tv1) {
        this.tv1 = tv1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar cal = Calendar.getInstance();
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hora, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(android.widget.TimePicker timePicker, int hour, int minute) {

        //TextView tv2 = (TextView) getActivity().findViewById(R.id.textViewHoraVolver);

        String hora = hour > 9 ? String.valueOf(hour) : "0" + String.valueOf(hour);
        //String tag = timePicker.getTag().toString();
        //Log.d("********INFO", tag);
        String min = minute > 9 ? String.valueOf(minute) : "0" + String.valueOf(minute);
        tv1.setText(hora + ":" + min);
        //tv2.setText(hora + ":" + min);
        /*if(timePicker.getTag().equals(OfrecerViaje.TAGIDA)){
            tv1.setText(hora + ":" + min);
        }else if(timePicker.equals(OfrecerViaje.TAGVUELTA)){
            tv2.setText(hora + ":" + min);
        }*/

    }
}
