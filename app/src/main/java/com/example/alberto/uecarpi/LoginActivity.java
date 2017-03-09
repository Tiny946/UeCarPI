package com.example.alberto.uecarpi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private EditText emailLogin;
    private EditText contraLogin;
    private FirebaseAuth firebaseAuth;
    private Button btnregistrase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailLogin = (EditText)findViewById(R.id.txtEmail);
        contraLogin = (EditText)findViewById(R.id.txtContraseña);
        firebaseAuth = FirebaseAuth.getInstance();
        btnregistrase = (Button) findViewById(R.id.buttonRegister);



        btnregistrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrase();
            }
        });
    }


    public void btnLogin_click(View view){
        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, " Por favor espere... ", " Cargando... ", true);
        (firebaseAuth.signInWithEmailAndPassword(emailLogin.getText().toString(), contraLogin.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Exito al Loguearse", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                    startActivity(i);
                }else{
                    Log.e("ERROR", task.getException().toString());
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void registrase(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

}
