package com.Rick.DBAPI;

import com.Rick.DBAPI.Models.PersonajeRepository;
import com.Rick.DBAPI.Models.PlanetaRepository;
import com.Rick.DBAPI.Models.TransformacionRepository;
import com.Rick.DBAPI.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbapiApplication implements CommandLineRunner {
	@Autowired
	private PersonajeRepository repository;
	@Autowired
	private TransformacionRepository tRepository;
	@Autowired
	private PlanetaRepository pRepository;
	public static void main(String[] args) {
		SpringApplication.run(DbapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal interfaz = new Principal(repository, tRepository, pRepository);
		interfaz.UI();
	}
}
