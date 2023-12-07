package com.davinci.clicktixmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        ImageView back_home = findViewById(R.id.btn_volver_tickets);
        ImageView back_home_logo = findViewById(R.id.id_logo_home);

        back_home_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void openMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}