package com.davinci.clicktixmobile.model;

public enum Horario {

    H1("12:00"),H2("14:00"),H3("16:00"),H4("18:00"),H5("20:00"),H6("22:00"),H7("24:00");


    private final String label;

    Horario(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }



}
