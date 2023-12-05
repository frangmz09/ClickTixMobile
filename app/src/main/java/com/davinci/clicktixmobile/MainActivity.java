package com.davinci.clicktixmobile;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.themoviedb.org";
    private static final String API_KEY = "44f9ad3c77d25563ced721e526744397";
    private static final String LANGUAGE = "es-ES";
    private static final String REGION = "AR";


    private List<Pelicula> tuListaDePeliculasEnCartelera = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView searchView = findViewById(R.id.id_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });


        loadMovies();
    }


    private void loadMovies() {
        OkHttpClient client = new OkHttpClient();
        double minPopularity = 700.0;

        Request request = new Request.Builder()
                .url(BASE_URL + "/3/movie/now_playing" +
                        "?api_key=" + API_KEY +
                        "&language=" + LANGUAGE +
                        "&region=" + REGION +
                        "&vote_average.gte=" + minPopularity)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseData = response.body().string();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            JsonObject jsonObject = JsonParser.parseString(responseData).getAsJsonObject();
                            JsonArray resultsArray = jsonObject.getAsJsonArray("results");
                            Type listType = new TypeToken<List<Pelicula>>() {}.getType();
                            tuListaDePeliculasEnCartelera = new Gson().fromJson(resultsArray, listType);

                            // Mostrar las películas en la interfaz de usuario
                            mostrarPeliculas(tuListaDePeliculasEnCartelera);
                        }
                    });
                } else {
                    Log.e("MainActivity", "Error en la respuesta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("MainActivity", "Error en la solicitud: " + e.getMessage());
            }
        });
    }


    private void performSearch(String searchTerm) {

        List<Pelicula> peliculasFiltradas = filtrarPeliculas(tuListaDePeliculasEnCartelera, searchTerm);
        mostrarPeliculas(peliculasFiltradas);
    }


    private List<Pelicula> filtrarPeliculas(List<Pelicula> peliculas, String searchTerm) {
        List<Pelicula> peliculasFiltradas = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                peliculasFiltradas.add(pelicula);
            }
        }
        return peliculasFiltradas;
    }
    private void mostrarPeliculas(List<Pelicula> peliculas) {
        LinearLayout movieContainerLayout = findViewById(R.id.scrollcontent);

        movieContainerLayout.removeAllViews();

        for (Pelicula pelicula : peliculas) {
            LinearLayout movieLayout = new LinearLayout(MainActivity.this);
            movieLayout.setOrientation(LinearLayout.VERTICAL);

            ImageView posterImageView = new ImageView(MainActivity.this);
            posterImageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            posterImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

            Picasso.get()
                    .load(pelicula.getPosterUrl())
                    .error(R.drawable.error_image)
                    .into(posterImageView);

            Log.e("PRUEBA", (pelicula.getPosterUrl()));
            TextView titleTextView = new TextView(MainActivity.this);

            LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            titleParams.setMargins(0, 15, 0, 50);
            titleTextView.setLayoutParams(titleParams);
            titleTextView.setText(pelicula.getTitle());
            titleTextView.setGravity(Gravity.CENTER);
            titleTextView.setTextColor(Color.BLACK);
            titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            movieLayout.addView(posterImageView);
            movieLayout.addView(titleTextView);

            movieContainerLayout.addView(movieLayout);
        }
    }
}
