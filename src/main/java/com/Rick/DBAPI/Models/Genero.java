package com.Rick.DBAPI.Models;

public enum Genero {
    MASCULINO("Male"),
    FEMENINO("Female"),
    DESCONOCIDO("Unknown");

    private final String nombreGenero;

    Genero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public static Genero fromString(String text) {
        for (Genero g : Genero.values()) {
            if (g.nombreGenero.equalsIgnoreCase(text)) {
                return g;
            }
        }
        return DESCONOCIDO;
    }
}

