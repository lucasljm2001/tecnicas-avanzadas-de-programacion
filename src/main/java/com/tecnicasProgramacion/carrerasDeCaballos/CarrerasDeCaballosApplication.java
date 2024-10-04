package com.tecnicasProgramacion.carrerasDeCaballos;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.TipoDeCarrera;
import com.tecnicasProgramacion.carrerasDeCaballos.service.CarreraService;
import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.ApostadorServiceImpl;
import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.CaballoServiceImpl;
import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.CarreraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class CarrerasDeCaballosApplication {

	@Autowired
	private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

	@Autowired
	private ApostadorServiceImpl apostadorService;


	@Autowired
	private CaballoServiceImpl caballoService;

	@Autowired
	private CarreraServiceImpl carreraService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
		apostadorService.crearApostador("1234", passwordEncoder.encode("admin"), "admin", true);
		apostadorService.crearApostador("567", passwordEncoder.encode("pepe"), "pepe", false);
		Caballo caballo = caballoService.crearCaballo("veloz", 50.0f, 10f, 5);
		Carrera carrera = carreraService.crearCarrera(LocalDateTime.of(2024, 10, 10, 10, 10), 100, "carrera1", TipoDeCarrera.CARRERA_NORMAL);
		carreraService.agregarCaballo(carrera, caballo);
	}

	@EventListener
	public void onApplicationEvent(ContextClosedEvent event) {
		// 1° Traer todas las tablas
		List<Map<String, Object>> tables = jdbcTemplate.queryForList("SHOW TABLES");
		List<String> tableNames = tables.stream()
				.map(table -> table.values().iterator().next().toString())
				.toList();

		// 2° Desactivar el chequeo por FK
		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");

		// 3° Vaciar todas las tablas
		tableNames.forEach(tableName -> jdbcTemplate.execute("TRUNCATE TABLE " + tableName));

		// 4° Activar el chequeo por FK
		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
		apostadorService.removeAll();
		caballoService.removeAll();
		carreraService.removeAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(CarrerasDeCaballosApplication.class, args);
	}

}
