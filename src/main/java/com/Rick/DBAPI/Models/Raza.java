package com.Rick.DBAPI.Models;

public enum Raza {

    SAIYAYIN("Saiyan"),
    NAMEKUSEI("Namekian"),
    HUMANO("Human"),
    DEMONIO_DEL_FRIO("Frieza Race"),
    ANDROIDE("Android"),
    MAJIN("Majin"),
    RAZA_DE_JIREN("Jiren Race"),
    DIOS("God"),
    ANGEL("Angel"),
    MALVADO("Evil"),
    NUCLEICO("Nucleico"),
    NUCLEICO_BENIGNO("Nucleico benigno"),
    DESCONOCIDO("Unknown");

    private final String nombreRaza;

    Raza(String nombreRaza) {
        this.nombreRaza = nombreRaza;
    }

    public String getNombreRaza() {
        return nombreRaza;
    }

    public static Raza fromString(String text) {
        for (Raza raza : Raza.values()) {
            if (raza.nombreRaza.equalsIgnoreCase(text)) {
                return raza;
            }
        }
        throw new IllegalArgumentException("Ninguna raza encontrada: " + text);
    }
}
