package com.Rick.DBAPI.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Transformacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String imagen;
    private String ki;
    @ManyToMany(mappedBy = "transformaciones" , cascade = CascadeType.ALL)
    private List<Personaje> personajes;

    public Transformacion(){}

    public Transformacion(DatosTranformacion t) {
        this.nombre = t.nombre();
        this.imagen = t.imagen();
        this.ki = t.ki();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getKi() {
        return ki;
    }

    public void setKi(String ki) {
        this.ki = ki;
    }

    @Override
    public String toString() {
        return "Transformacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", ki='" + ki + '\'' +
                ", personajes=" + personajes +
                '}';
    }
}

