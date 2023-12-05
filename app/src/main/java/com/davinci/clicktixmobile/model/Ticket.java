package com.davinci.clicktixmobile.model;

public class Ticket {

    private String id;
    private String email;
    private Integer peliculaId;
    private String dimension;
    private String idioma;
    private String horario;
    private Integer cantidadButacas;
    private String fecha;


    public Ticket(String id, String email, Integer peliculaId, String dimension, String idioma, String horario, Integer cantidadButacas, String fecha) {
        this.id = id;
        this.email = email;
        this.peliculaId = peliculaId;
        this.dimension = dimension;
        this.idioma = idioma;
        this.horario = horario;
        this.cantidadButacas = cantidadButacas;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdEmail() {
        return email;
    }

    public void setIdEmail(String idEmail) {
        this.email = idEmail;
    }

    public Integer getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(Integer peliculaId) {
        this.peliculaId = peliculaId;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getCantidadButacas() {
        return cantidadButacas;
    }

    public void setCantidadButacas(Integer cantidadButacas) {
        this.cantidadButacas = cantidadButacas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
