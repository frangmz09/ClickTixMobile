package com.davinci.clicktixmobile.model;

public enum Dimension {
    D2("2D"),
    D3("3D");

    private final String label;

    Dimension(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
