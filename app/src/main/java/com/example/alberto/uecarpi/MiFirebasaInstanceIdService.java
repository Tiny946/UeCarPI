package com.example.alberto.uecarpi;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Alberto on 23/02/2017.
 */
public class MiFirebasaInstanceIdService extends FirebaseInstanceIdService {
    public static final String TAG = "NOTICIAS";
    @Override
    public void onTokenRefresh(){
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Token: " + token);
    }
}
