package com.davinci.clicktixmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.davinci.clicktixmobile.model.Ticket;

public class CompraFinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_final);



        Intent intent = getIntent();
        Ticket ticket = (Ticket) intent.getSerializableExtra("TICKET");
        System.out.println(ticket.toStringTicket());

        cambiarDatos(ticket);

        ImageButton backButton = findViewById(R.id.btn_volver_home);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CompraFinalActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public  void cambiarDatos(Ticket ticket){

        String pelicula = ticket.getTitulo();
        String fecha = ticket.getFecha();
        String horario = ticket.getHorario();
        String idioma = ticket.getIdioma();
        String butacas = ticket.getCantidadButacas();
        String dimension = ticket.getDimension();

        TextView txtFecha = findViewById(R.id.txt_fechaFuncion);
        txtFecha.setText(fecha);
        TextView txtHorario = findViewById(R.id.txt_hora);
        txtHorario.setText(horario);
        TextView txtIdioma = findViewById(R.id.txt_idioma);
        txtIdioma.setText(idioma);
        TextView txtButacas = findViewById(R.id.txt_cantidadButacas);
        txtButacas.setText(butacas);


        TextView txtTitulo = findViewById(R.id.txt_tituloPelicula);
        txtTitulo.setText(pelicula + "  " + dimension);




    }




}
