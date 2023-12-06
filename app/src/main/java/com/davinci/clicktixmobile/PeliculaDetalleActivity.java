package com.davinci.clicktixmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class PeliculaDetalleActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://api.themoviedb.org";
    private static final String API_KEY = "44f9ad3c77d25563ced721e526744397";
    private static final String LANGUAGE = "es-ES";
    private static final String REGION = "AR";

    private Integer idPeliculaTicket;
    private String tituloPelicula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_detail);
        ImageView back_home = findViewById(R.id.id_logo_home);
        back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PeliculaDetalleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        int peliculaId = getIntent().getIntExtra("pelicula_id", -1);

        idPeliculaTicket = peliculaId;
        if (peliculaId != -1) {
            obtenerDetallesPelicula(peliculaId);
        } else {
            Intent intent = new Intent(PeliculaDetalleActivity.this, MainActivity.class);
            startActivity(intent);
        }
        ImageButton backButton = findViewById(R.id.btn_volver_home);

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
                intent.putExtra("ID_PELICULA", idPeliculaTicket);
                intent.putExtra("TITULO",tituloPelicula);
                startActivity(intent);
            }
        });






    }
    private void obtenerDetallesPelicula(int peliculaId) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(BASE_URL + "/3/movie/" + peliculaId +
                        "?api_key=" + API_KEY +
                        "&language=" + LANGUAGE +
                        "&region=" + REGION)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            procesarDetallesPelicula(responseData);
                        }
                    });
                } else {
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // Manejar el error en la solicitud
            }
        });
    }
    private void procesarDetallesPelicula(String responseData) {
        // Analizar la respuesta JSON
        JsonObject jsonObject = JsonParser.parseString(responseData).getAsJsonObject();

        // Obtener detalles específicos de la película
        String titulo = jsonObject.get("title").getAsString();
        String overview = jsonObject.get("overview").getAsString();
        String imagenUrl = jsonObject.get("poster_path").getAsString();
        String releaseDate = jsonObject.get("release_date").getAsString();
        String imagenUrlCompleta = "https://image.tmdb.org/t/p/w500" + imagenUrl;
        mostrarDetallesEnInterfaz(titulo, overview, imagenUrlCompleta, releaseDate);
    }

    private void mostrarDetallesEnInterfaz(String titulo, String overview, String imagenUrl, String releaseDate) {
        TextView tituloTextView = findViewById(R.id.txtTituloPelicula);
        TextView overviewTextView = findViewById(R.id.txtSinopsis);
        TextView subtituloTextView = findViewById(R.id.txtNombre);
        ImageView imagenImageView = findViewById(R.id.imgPortadaPelicula);
        TextView releaseDateTextView = findViewById(R.id.txtFecha);


        tituloPelicula = titulo;
        tituloTextView.setText(titulo);
        overviewTextView.setText(overview);
        releaseDateTextView.setText(String.format("%s%s", getString(R.string.fecha_de_estreno_label), releaseDate));
        subtituloTextView.setText(String.format("%s%s", getString(R.string.titulo_label), titulo.toUpperCase()));
        Picasso.get().load(imagenUrl).into(imagenImageView);
    }
}