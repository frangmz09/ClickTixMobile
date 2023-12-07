package com.davinci.clicktixmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

        // Inicializa FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        ImageView back_home = findViewById(R.id.id_logo_home);
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerificationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btn = findViewById(R.id.btnEnviarVerificacion);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = mAuth.getCurrentUser();

                if (user != null) {
                    if (!user.isEmailVerified()) {
                        user.sendEmailVerification()
                                .addOnCompleteListener(VerificationActivity.this, task -> {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Correo de verificación enviado " + user.getEmail(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error al enviar el correo de verificación", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(getApplicationContext(), "El correo electrónico ya está verificado", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "El usuario no está autenticado, realiza acciones de acuerdo a tus necesidades", Toast.LENGTH_SHORT).show();

                }
            }
        });

        ImageButton backButton = findViewById(R.id.btn_volver_tickets);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerificationActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
