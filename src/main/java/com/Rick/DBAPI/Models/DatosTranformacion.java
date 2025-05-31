package com.Rick.DBAPI.Models;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosTranformacion(
        @JsonAlias("id") Long id,
        @JsonAlias("name") String nombre,
        @JsonAlias("image") String imagen,
        @JsonAlias("ki") String ki
) {
}
