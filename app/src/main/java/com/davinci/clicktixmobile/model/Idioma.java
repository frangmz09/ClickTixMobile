package com.davinci.clicktixmobile.model;

public enum Idioma {

    I1("Ingles"),I2("Español");


    private final String label;

    Idioma(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
