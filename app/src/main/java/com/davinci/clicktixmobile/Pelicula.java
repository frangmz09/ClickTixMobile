package com.davinci.clicktixmobile;
public class Pelicula {
    // Otras propiedades de la película
    private String posterPath;
    private String title;
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"; // El tamaño 'w500' puede variar según tus necesidades

    public String getPosterUrl() {
        return BASE_IMAGE_URL + posterPath; // Asegúrate de tener la propiedad posterPath en tu clase Pelicula
    }

    public String getTitle() {
        return title;
    }
}