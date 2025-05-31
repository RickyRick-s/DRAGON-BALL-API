package com.Rick.DBAPI.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPlaneta(
        @JsonAlias("id") long id,
        @JsonAlias("name") String nombre,
        @JsonAlias("isDestroyed") boolean destruido,
        @JsonAlias("description") String descripcion,
        @JsonAlias("image") String imagen)
{
}
