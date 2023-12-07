package com.davinci.clicktixmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerificationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ImageView back_home = findViewById(R.id.id_logo_home);
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerificationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button btn = findViewById(R.id.btnEnviarVerificacion);
        TextView tv = findViewById(R.id.txt_mensaje_verificacion);
        Intent intent = getIntent();
        if (intent != null) {
            String emailVerificacion = intent.getStringExtra("email");

            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                user.sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(getApplicationContext(), "Correo de verificación enviado", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }


            if (emailVerificacion != null) {
                email = emailVerificacion;
                String mensaje = "Hemos enviado un mail de verificacion a " + emailVerificacion;

                tv.setText(mensaje);
            }
        }

        ImageButton backButton = findViewById(R.id.btn_volver_tickets);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerificationActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });








    }






}