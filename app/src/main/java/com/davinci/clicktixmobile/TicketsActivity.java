package com.davinci.clicktixmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TicketsActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://api.themoviedb.org";
    private static final String API_KEY = "44f9ad3c77d25563ced721e526744397";
    private static final String LANGUAGE = "es-ES";
    private static final String REGION = "AR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        LinearLayout tickets_containerLY = findViewById(R.id.tickets_containerLY);


        if (user != null) {
            String userEmail = user.getEmail();
            Log.e("EMAIL", userEmail);

            firestore.collection("tickets")
                    .whereEqualTo("email", userEmail)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                tickets_containerLY.removeAllViews();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String cantidadButacasFB = document.getString("cantidad_butacas");
                                    String dimensionFB = document.getString("dimension");
                                    String fechaFB = document.getString("fecha");
                                    String horarioFB = document.getString("horario");
                                    String idiomaFB = document.getString("idioma");
                                    long idFB = document.getLong("pelicula_id");
                                    Log.e("TESTDOC", "Cantidad de butacas: " + cantidadButacasFB);
                                    Log.e("TESTDOC", "Dimension: " + dimensionFB);
                                    Log.e("TESTDOC", "Fecha: " + fechaFB);
                                    Log.e("TESTDOC", "Horario: " + horarioFB);
                                    Log.e("TESTDOC", "Idioma: " + idiomaFB);
                                    Log.e("TESTDOC", "ID de la película: " + idFB);

                                    View ticketView = getLayoutInflater().inflate(R.layout.ticket_item_layout, null);

                                    ImageView ticketImage = ticketView.findViewById(R.id.ticket_image);
                                    TextView titleTextView = ticketView.findViewById(R.id.ticket_title);
                                    TextView dimensionTextView = ticketView.findViewById(R.id.ticket_dimension);
                                    TextView dateTextView = ticketView.findViewById(R.id.ticket_date);
                                    TextView timeTextView = ticketView.findViewById(R.id.ticket_time);
                                    TextView languageTextView = ticketView.findViewById(R.id.ticket_language);
                                    TextView seatsTextView = ticketView.findViewById(R.id.ticket_seats);

                                    titleTextView.setText("Título: " + "A completar");
                                    dimensionTextView.setText("Dimension: " + dimensionFB);
                                    dateTextView.setText("Fecha: " + fechaFB);
                                    timeTextView.setText("Horario: " + horarioFB);
                                    languageTextView.setText("Idioma: " + idiomaFB);
                                    seatsTextView.setText("Cantidad de butacas: " + cantidadButacasFB);

                                    // Puedes cargar la imagen con Picasso o Glide, por ejemplo
                                    // Picasso.get().load(ticket.getImageUrl()).into(ticketImage);

                                    tickets_containerLY.addView(ticketView);
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Error al recuperar los tickets", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }


        ImageButton backButton = findViewById(R.id.btn_volver_tickets);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TicketsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView back_home = findViewById(R.id.id_logo_home);
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicketsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }

}
