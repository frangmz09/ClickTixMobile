package com.davinci.clicktixmobile.model;

import java.io.Serializable;

public class Ticket implements Serializable {

    private String id;
    private String email;
    private Integer peliculaId;
    private String dimension;
    private String idioma;
    private String horario;
    private String cantidadButacas;
    private String fecha;

    private String titulo;




    public String toStringTicket() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", peliculaId=" + peliculaId +
                ", dimension='" + dimension + '\'' +
                ", idioma='" + idioma + '\'' +
                ", horario='" + horario + '\'' +
                ", cantidadButacas='" + cantidadButacas + '\'' +
                ", fecha='" + fecha + '\'' +
                ", titulo='" + titulo + '\'' +
                '}';
    }

    public Ticket(){

    }


    public Ticket(String id, String email, Integer peliculaId, String dimension, String idioma, String horario, String cantidadButacas, String fecha, String titulo) {
        this.id = id;
        this.email = email;
        this.peliculaId = peliculaId;
        this.dimension = dimension;
        this.idioma = idioma;
        this.horario = horario;
        this.cantidadButacas = cantidadButacas;
        this.fecha = fecha;
        this.titulo = titulo;
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

    public String getCantidadButacas() {
        return cantidadButacas;
    }

    public void setCantidadButacas(String cantidadButacas) {
        this.cantidadButacas = cantidadButacas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}



