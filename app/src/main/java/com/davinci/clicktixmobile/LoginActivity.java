package com.davinci.clicktixmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.input_email);
        EditText password = findViewById(R.id.input_password);
        TextView mensajeDeError = findViewById(R.id.mensaje_error);


        Button btn = findViewById(R.id.btn_ingresar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    mensajeDeError.setText("Los campos no deben estar vacíos");
                    mensajeDeError.setVisibility(View.VISIBLE);
                } else if (email.getText().toString().isEmpty()) {
                    mensajeDeError.setText("El campo email no debe estar vacío");
                    mensajeDeError.setVisibility(View.VISIBLE);
                } else if (password.getText().toString().isEmpty()) {
                    mensajeDeError.setText("El campo password no debe estar vacío");
                    mensajeDeError.setVisibility(View.VISIBLE);
                } else {
                    mensajeDeError.setVisibility(View.GONE);
                }
            }
        });
    }
}