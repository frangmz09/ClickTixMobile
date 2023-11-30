package com.davinci.clicktixmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView backImageView = findViewById(R.id.img_peli);

        backImageView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                // Crear un Intent para volver a ActivityA
                Intent intent = new Intent(MainActivity.this, PeliculaDetalleActivity.class);
                startActivity(intent);
            }
        });

        ImageView loginImage = findViewById(R.id.id_perfil);

        loginImage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                // Crear un Intent para volver a ActivityA
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
