package com.Rick.DBAPI.principal;

import com.Rick.DBAPI.Models.*;
import com.Rick.DBAPI.Service.ConsumoAPI;
import com.Rick.DBAPI.Service.ConvierteDatos;
import com.fasterxml.jackson.core.type.TypeReference;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    Scanner scan = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String url = "https://dragonball-api.com/api/characters";
    private final ConvierteDatos conversor = new ConvierteDatos();
    private PersonajeRepository repository;
    private TransformacionRepository Trepository;
    private PlanetaRepository planetaRepository;

    public Principal(PersonajeRepository repository, TransformacionRepository Trepository, PlanetaRepository planetaRepository) {
        this.repository = repository;
        this.Trepository = Trepository;
        this.planetaRepository = planetaRepository;
    }

    public void UI() {
        var control = -1;
        while (control != 0) {
            var menu = """
                    1.- Buscar por nombre
                    2.- Buscar personjes por planeta
                    0.- Salir
                    """;
            System.out.println();
            System.out.println("###########################");
            System.out.println(menu);
            System.out.println("###########################");
            control = scan.nextInt();
            scan.nextLine();

            switch (control) {
                case 1:
                    guardaDatosPersonaje();
                    break;
                case 2:
                    buscaPersonajesPorPlaneta();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }

    private Optional<DatosPersonaje> BuscaPorNombre() {
        System.out.println("Ingrese el nombre del personaje");
        var name = scan.nextLine();
        String nombreCodificado = URLEncoder.encode(name, StandardCharsets.UTF_8);
        var json = consumoApi.obtenerDatos("https://dragonball-api.com/api/characters?name=" + nombreCodificado);
        List<DatosPersonaje> personaje = conversor.obtenerListaDatos(json, new TypeReference<List<DatosPersonaje>>() {
        });
        if (personaje.isEmpty()) {
            System.out.println("Personaje no encontrado");
        }
        return personaje.stream().findFirst();
    }

    private void guardaDatosPersonaje() {
        Long idPersonajeBusqueda = BuscaPorNombre().get().id();
        var json = consumoApi.obtenerDatos("https://dragonball-api.com/api/characters/" + idPersonajeBusqueda);
        DatosPersonaje personaje = conversor.obtenerDatos(json, DatosPersonaje.class);

        // Usar PlanetaRepository para buscar o crear el planeta
        Optional<Planeta> planetaExistente = planetaRepository.findByNombre(personaje.planetaOrigen().nombre());
        Planeta planetaOrigen = planetaExistente.orElseGet(() -> {
            Planeta nuevoPlaneta = new Planeta(personaje.planetaOrigen());
            return planetaRepository.save(nuevoPlaneta);
        });

        List<DatosTranformacion> transformaciones = personaje.transformaciones();
        List<Transformacion> transformacionPersonaje = transformaciones.stream()
                .filter(Objects::nonNull)
                .map(dt -> {
                    Optional<Transformacion> tExistente = Trepository.findByNombre(dt.nombre());
                    if (tExistente.isPresent()) {
                        return tExistente.get();
                    } else {
                        Transformacion nueva = new Transformacion(dt);
                        Trepository.save(nueva);
                        return nueva;
                    }
                })
                .collect(Collectors.toUnmodifiableList());

        // Verificación por nombre antes de guardar
        Optional<Personaje> personajeExistentePorNombre = repository.findByNombre(personaje.nombre());
        if (personajeExistentePorNombre.isPresent()) {
            System.out.println(personajeExistentePorNombre);
            System.out.println("El personaje ya existe en la base de datos.");
            return;
        }

        Personaje personajeDb = new Personaje(personaje);
        personajeDb.setPlanetaOrigen(planetaOrigen);
        personajeDb.setTransformaciones(transformacionPersonaje);
        repository.save(personajeDb);
        System.out.println(personajeDb);
        System.out.println("Personaje guardado exitosamente.");
    }

        private void buscaPersonajesPorPlaneta () {
            System.out.println("Ingrese el nombre del planeta");
            var nombrePlaneta = scan.nextLine();
            Optional<Planeta> planeta = planetaRepository.findByNombreIgnoreCase(nombrePlaneta);
            if (planeta.isEmpty()) {
                System.out.println("Error: el planeta no existe");
                return;
            }
            List<Personaje> habitantes = repository.personajesPorPlaneta(nombrePlaneta);
            System.out.println("Personajes originarios del planeta " + nombrePlaneta);
            System.out.println("###########################");
            System.out.println("Total de personajes encontrados: " + habitantes.size());
            habitantes.forEach(e ->
                    System.out.printf("Nombre: %s | Afiliación: %s | Genero: %s | Ki: %s | Apariencia: %s%n",
                            e.getNombre(),
                            e.getAfiliacion(),
                            e.getGenero(),
                            e.getKi(),
                            e.getImagen()
                    )
            );
        }
    }
