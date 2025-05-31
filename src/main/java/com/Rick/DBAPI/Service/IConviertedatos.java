package com.Rick.DBAPI.Service;

public interface IConviertedatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
