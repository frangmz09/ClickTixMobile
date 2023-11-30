package com.davinci.clicktixmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText email = findViewById(R.id.input_email_register);
        EditText password = findViewById(R.id.input_password_register);
        EditText confirmpassword = findViewById(R.id.input_confirmpassword_register);
        TextView mensajeDeError = findViewById(R.id.mensaje_error_register);

        ImageButton backButton = findViewById(R.id.btn_volver_register);

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
                String emailText = email.getText().toString();
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
                }
            }
        });
    }

}
