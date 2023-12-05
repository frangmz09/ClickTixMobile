package com.davinci.clicktixmobile;
public class Pelicula {
    private Integer id;

    private String poster_path;
    private String title;
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    private static String release_date;

    private static String overview;

    public String getPosterUrl() {
        return BASE_IMAGE_URL + poster_path;
    }

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }
}