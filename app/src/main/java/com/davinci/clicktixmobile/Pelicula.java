package com.davinci.clicktixmobile;
public class Pelicula {
    // Otras propiedades de la película
    private String poster_path;
    private String title;
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";

    public String getPosterUrl() {
        return BASE_IMAGE_URL + poster_path;
    }

    public String getTitle() {
        return title;
    }
}