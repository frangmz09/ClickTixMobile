package com.davinci.clicktixmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        TextView tv = findViewById(R.id.txt_mensaje_verificacion);


        Intent intent = getIntent();
        if (intent != null) {
            String emailVerificacion = intent.getStringExtra("email");
            if (emailVerificacion != null) {
                // seteamos el texto
                String mensaje = "Hemos enviado un mail de verificacion a " + emailVerificacion;

                tv.setText(mensaje);


            }
        }
    }
}