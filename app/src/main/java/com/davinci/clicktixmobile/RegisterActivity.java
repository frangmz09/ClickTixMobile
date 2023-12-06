package com.davinci.clicktixmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        EditText email = findViewById(R.id.input_email_register);
        EditText password = findViewById(R.id.input_password_register);
        EditText confirmpassword = findViewById(R.id.input_confirmpassword_register);
        TextView mensajeDeError = findViewById(R.id.mensaje_error_register);

        ImageButton backButton = findViewById(R.id.btn_volver_home);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button btn = findViewById(R.id.btn_registrarse);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                String  emailText= email.getText().toString();
                String passwordText = password.getText().toString();
                String confirmPasswordText = confirmpassword.getText().toString();

                if (emailText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()) {
                    mensajeDeError.setText(R.string.msg_error_login1);
                    mensajeDeError.setVisibility(View.VISIBLE);
                } else if (emailText.isEmpty() && !passwordText.isEmpty()) {
                    mensajeDeError.setText(R.string.msg_error_login2);
                    mensajeDeError.setVisibility(View.VISIBLE);
                } else if (passwordText.isEmpty() && !emailText.isEmpty()) {
                    mensajeDeError.setText(R.string.msg_error_login3);
                    mensajeDeError.setVisibility(View.VISIBLE);
                } else if (!passwordText.equals(confirmPasswordText)) {
                    mensajeDeError.setText(R.string.msg_error_password_mismatch);
                    mensajeDeError.setVisibility(View.VISIBLE);
                } else {
                    mensajeDeError.setVisibility(View.GONE);

                    mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user != null) {
                                            user.sendEmailVerification()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                // Proceso exitoso
                                                                Toast.makeText(getApplicationContext(), "Correo de verificación enviado", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                        }

                                        Log.d("TAG", "createUserWithEmail:success");

                                        Toast.makeText(RegisterActivity.this, "Se registro" ,
                                                Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(RegisterActivity.this, VerificationActivity.class);
                                        intent.putExtra("email", emailText);
                                        startActivity(intent);


                                    } else {

                                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });
    }

}
