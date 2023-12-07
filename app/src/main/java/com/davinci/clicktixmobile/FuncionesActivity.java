package com.davinci.clicktixmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.davinci.clicktixmobile.model.Ticket;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;


public class FuncionesActivity extends AppCompatActivity {

    private Ticket ticket;
    private FirebaseAuth mAuth;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funciones);

        Button btnSiguiente = findViewById(R.id.btn_siguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {

            private boolean validarRadioButtons() {
                RadioGroup[] radioGroups = {
                        findViewById(R.id.radioGroupDimension),
                        findViewById(R.id.radioGroupIdioma),
                        findViewById(R.id.radioGroupFechas),
                        findViewById(R.id.radioGroupHorarios),
                        findViewById(R.id.radioGroupButacas)
                };
                for (RadioGroup radioGroup : radioGroups) {
                    if (radioGroup.getCheckedRadioButtonId() == -1) {
                        return false;
                    }
                }
                return true;
            }
            @Override
            public void onClick(View v) {
                if (validarRadioButtons()) {
                    System.out.println(ticket.toStringTicket());
                    Intent intent = new Intent(FuncionesActivity.this, PaymentActivity.class);
                    intent.putExtra("TICKET", ticket);
                    startActivity(intent);
                } else {
                    Toast.makeText(FuncionesActivity.this, "Debes seleccionar todas las opciones", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.ticket = new Ticket();


        RadioGroup radioGroupDimension = findViewById(R.id.radioGroupDimension);

        radioGroupDimension.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton2D) {

                RadioButton radioButton2D = findViewById(R.id.radioButton2D);

                System.out.println("Seleccionado: " + radioButton2D.getText());

                ticket.setDimension((String) radioButton2D.getText());


            } else if (checkedId == R.id.radioButton3D) {

                RadioButton radioButton3D = findViewById(R.id.radioButton3D);

                System.out.println("Seleccionado: " + radioButton3D.getText());
                ticket.setDimension((String) radioButton3D.getText());
            }
        });


        RadioGroup radioGroupIdioma = findViewById(R.id.radioGroupIdioma);

        radioGroupIdioma.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButtonEspañol) {

                RadioButton radioButtonEspañol = findViewById(R.id.radioButtonEspañol);

                System.out.println("Seleccionado: " + radioButtonEspañol.getText());


                ticket.setIdioma((String) radioButtonEspañol.getText());

            } else if (checkedId == R.id.radioButtonIngles) {

                RadioButton radioButtonIngles = findViewById(R.id.radioButtonIngles);

                System.out.println("Seleccionado: " + radioButtonIngles.getText());

                ticket.setIdioma((String) radioButtonIngles.getText());
            }
        });





        RadioGroup radioGroupFecha = findViewById(R.id.radioGroupFechas);


        for (int i = 0; i < 4; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, i);


            SimpleDateFormat sdf = new SimpleDateFormat("d/M", Locale.getDefault());
            String formattedDate = sdf.format(calendar.getTime());


            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(formattedDate);


            radioGroupFecha.addView(radioButton);
        }

        radioGroupFecha.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton selectedRadioButton = findViewById(checkedId);
            if (selectedRadioButton != null) {
                String selectedDate = selectedRadioButton.getText().toString();
                System.out.println("Fecha seleccionada: " + selectedDate);

                ticket.setFecha(selectedDate);
            }
        });


        RadioGroup radioGroupHorario = findViewById(R.id.radioGroupHorarios);

        radioGroupHorario.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton12) {

                RadioButton radioButton12 = findViewById(R.id.radioButton12);

                System.out.println("Seleccionado: " + radioButton12.getText());

                ticket.setHorario((String) radioButton12.getText());
            } else if (checkedId == R.id.radioButton14) {

                RadioButton radioButton14 = findViewById(R.id.radioButton14);

                System.out.println("Seleccionado: " + radioButton14.getText());
                ticket.setHorario((String) radioButton14.getText());
            }
            else if (checkedId == R.id.radioButton16) {

                RadioButton radioButton16 = findViewById(R.id.radioButton16);

                System.out.println("Seleccionado: " + radioButton16.getText());
                ticket.setHorario((String) radioButton16.getText());
            }
            else if (checkedId == R.id.radioButton18) {

                RadioButton radioButton18 = findViewById(R.id.radioButton18);

                System.out.println("Seleccionado: " + radioButton18.getText());
                ticket.setHorario((String) radioButton18.getText());
            }
            else if (checkedId == R.id.radioButton20) {

                RadioButton radioButton20 = findViewById(R.id.radioButton20);

                System.out.println("Seleccionado: " + radioButton20.getText());

                ticket.setHorario((String) radioButton20.getText());
            }
            else if (checkedId == R.id.radioButton22) {

                RadioButton radioButton22 = findViewById(R.id.radioButton22);

                System.out.println("Seleccionado: " + radioButton22.getText());

                ticket.setHorario((String) radioButton22.getText());
            }
        });


        RadioGroup radioGroupButacas = findViewById(R.id.radioGroupButacas);

        radioGroupButacas.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.butaca1) {

                RadioButton butaca1 = findViewById(R.id.butaca1);
                System.out.println("Seleccionado: " + butaca1.getText());
                ticket.setCantidadButacas((String) butaca1.getText());
            } else if (checkedId == R.id.butaca2) {

                RadioButton butaca2 = findViewById(R.id.butaca2);

                System.out.println("Seleccionado: " + butaca2.getText());
                ticket.setCantidadButacas((String) butaca2.getText());
            }else if (checkedId == R.id.butaca3) {

                RadioButton butaca3 = findViewById(R.id.butaca3);

                System.out.println("Seleccionado: " + butaca3.getText());
                ticket.setCantidadButacas((String) butaca3.getText());
            }else if (checkedId == R.id.butaca4) {

                RadioButton butaca4 = findViewById(R.id.butaca4);
                System.out.println("Seleccionado: " + butaca4.getText());
                ticket.setCantidadButacas((String) butaca4.getText());
            }
        });

        mAuth = FirebaseAuth.getInstance();
        String emailCliente = mAuth.getCurrentUser().getEmail().toString();

        ticket.setIdEmail(emailCliente);
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        ticket.setId(uuidString);

        Intent intent = getIntent();

        int idPelicula = intent.getIntExtra("ID_PELICULA", -1);

        ticket.setPeliculaId(idPelicula);

        String tituloPelicula = intent.getStringExtra("TITULO");


        ticket.setTitulo(tituloPelicula);



        



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