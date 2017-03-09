package com.example.alberto.uecarpi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText email;
    private EditText contra;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        email =(EditText)findViewById(R.id.txtEmail);
        contra = (EditText)findViewById(R.id.txtContraseña);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void btnSignin_click(View view){


        final ProgressDialog progressDialog = ProgressDialog.show(SignInActivity.this, " Por favor espere... ", " Cargando... ", true);
        (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), contra.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){
                    Toast.makeText(SignInActivity.this, "Se ha completado el Registro", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(SignInActivity.this, LoginActivity.class);
                    startActivity(i);
                }else{
                    Log.e("Error al Registrase", task.getException().toString());
                    Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
