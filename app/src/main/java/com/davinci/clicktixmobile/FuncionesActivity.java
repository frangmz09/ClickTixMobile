package com.davinci.clicktixmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FuncionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funciones);

        Button btnSiguiente = findViewById(R.id.btn_siguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para pasar de FuncionesActivity a ActivityPayment
                Intent intent = new Intent(FuncionesActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btnAtras = findViewById(R.id.btnVolverPelicula);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para pasar de FuncionesActivity a ActivityPayment
                Intent intent = new Intent(FuncionesActivity.this, PeliculaDetalleActivity.class);
                startActivity(intent);
            }
        });
    }
}