package com.Rick.DBAPI.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPersonaje(
        @JsonAlias("id") Long id,
        @JsonAlias("name") String nombre,
        @JsonAlias("ki") String ki,
        @JsonAlias("maxKi") String kiMaximo,
        @JsonAlias("race") String raza,
        @JsonAlias("gender") String genero,
        @JsonAlias("description") String descripcion,
        @JsonAlias("image") String imagen,
        @JsonAlias("affiliation") String afiliacion,
        @JsonAlias("originPlanet") DatosPlaneta planetaOrigen,
        @JsonAlias("transformations") List<DatosTranformacion> transformaciones)
 {
}
