package com.Rick.DBAPI.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Personajes")
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private String ki;
    private String kiMaximo;
    @Enumerated(EnumType.STRING)
    private Raza raza;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private String imagen;
    private String afiliacion;
    @ManyToOne
    private Planeta planetaOrigen;
    @ManyToMany
    @JoinTable(
            name = "personajes_transformaciones",
            joinColumns = @JoinColumn(name = "personaje_id"),
    inverseJoinColumns = @JoinColumn(name = "transformacion_id")
    )
    private List<Transformacion> transformaciones;

    public Personaje(){}

    public Personaje(DatosPersonaje d) {
        this.afiliacion = d.afiliacion();
        this.imagen = d.imagen();
        this.descripcion = d.descripcion();
        this.genero = Genero.fromString(d.genero());
        this.raza = Raza.fromString(d.raza());
        this.kiMaximo = d.kiMaximo();
        this.ki = d.ki();
        this.nombre = d.nombre();
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

    public String getKi() {
        return ki;
    }

    public void setKi(String ki) {
        this.ki = ki;
    }

    public String getKiMaximo() {
        return kiMaximo;
    }

    public void setKiMaximo(String kiMaximo) {
        this.kiMaximo = kiMaximo;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getAfiliacion() {
        return afiliacion;
    }

    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

    public Planeta getPlanetaOrigen() {
        return planetaOrigen;
    }

    public void setPlanetaOrigen(Planeta planetaOrigen) {
        this.planetaOrigen = planetaOrigen;
    }

    public List<Transformacion> getTransformaciones() {
        return transformaciones;
    }

    public void setTransformaciones(List<Transformacion> transformaciones) {
        this.transformaciones = transformaciones;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ki='" + ki + '\'' +
                ", kiMaximo='" + kiMaximo + '\'' +
                ", raza=" + raza +
                ", genero=" + genero +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", afiliacion='" + afiliacion + '\'' +
                ", planetaOrigen=" + planetaOrigen ;
    }
}
