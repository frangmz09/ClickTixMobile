package com.davinci.clicktixmobile;

import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        EditText email = findViewById(R.id.input_email_register);
        EditText password = findViewById(R.id.input_password);
        TextView mensajeDeError = findViewById(R.id.mensaje_error_register);
        TextView registrarse = findViewById(R.id.register_text);

        ImageButton backButton = findViewById(R.id.btn_volver);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        Button btn = findViewById(R.id.btn_ingresar);
        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View button) {


                mAuth.signInWithEmailAndPassword(String.valueOf(email.getText()), String.valueOf(password.getText()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);


//                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();


//                                    updateUI(null);
                                }
                            }
                        });







//
//
//
//
//
//
//
//
//                if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
//                    mensajeDeError.setText(R.string.msg_error_login1);
//                    mensajeDeError.setVisibility(View.VISIBLE);
//                } else if (email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
//                    mensajeDeError.setText(R.string.msg_error_login2);
//                    mensajeDeError.setVisibility(View.VISIBLE);
//                } else if (password.getText().toString().isEmpty() && !email.getText().toString().isEmpty()) {
//                    mensajeDeError.setText(R.string.msg_error_login3);
//                    mensajeDeError.setVisibility(View.VISIBLE);
//                } else {
//                    mensajeDeError.setVisibility(View.GONE);
//                }
            }
        });
    }
}