package com.davinci.clicktixmobile;
public class Pelicula {
    // Otras propiedades de la película
    String posterPath;

    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"; // El tamaño 'w500' puede variar según tus necesidades

    // Getter y setter para otras propiedades

    public String getPosterUrl() {
        return BASE_IMAGE_URL + posterPath; // Asegúrate de tener la propiedad posterPath en tu clase Pelicula
    }
    private String title;

    public String getTitle() {
        return title;
    }
}