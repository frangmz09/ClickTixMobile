package com.davinci.clicktixmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PeliculaDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_detail);

        ImageButton backButton = findViewById(R.id.btn_volverAfunciones);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PeliculaDetalleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btn = findViewById(R.id.btnVerFunciones);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PeliculaDetalleActivity.this, FuncionesActivity.class);
                startActivity(intent);
            }
        });




    }
}