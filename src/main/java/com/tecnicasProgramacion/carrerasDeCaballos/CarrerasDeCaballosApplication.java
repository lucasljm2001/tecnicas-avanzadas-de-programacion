package com.tecnicasProgramacion.carrerasDeCaballos;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Apostador;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.TipoDeCarrera;
import com.tecnicasProgramacion.carrerasDeCaballos.service.ApuestaService;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Autowired
	private ApuestaService apuestaServiec;


	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
		Apostador admin = apostadorService.crearApostador("1234", passwordEncoder.encode("admin"), "admin", true);
		Apostador pepe = apostadorService.crearApostador("567", passwordEncoder.encode("pepe"), "pepe", false);
		Caballo caballo = caballoService.crearCaballo("veloz", 50.0f, 10f, 5);
		Caballo caballo1 = caballoService.crearCaballo("caballo1", 40f, 22f, 10);
		Caballo caballo2 = caballoService.crearCaballo("caballo2", 20f, 33f, 23);
		Caballo caballo3 = caballoService.crearCaballo("caballo3", 44f, 11f, 33);
		Carrera carrera = carreraService.crearCarrera(LocalDateTime.of(2024, 12, 10, 10, 10), 100, "carrera1", TipoDeCarrera.CARRERA_NORMAL);
		carreraService.agregarCaballo(carrera, caballo);
		carreraService.agregarCaballo(carrera, caballo1);
		carreraService.agregarCaballo(carrera, caballo2);
		Carrera carrera2 = carreraService.crearCarrera(LocalDateTime.of(2024, 12, 10, 10, 10), 100, "carrera2", TipoDeCarrera.CARRERA_NORMAL);
		Carrera iniciada = carreraService.crearCarrera(LocalDateTime.now().plusSeconds(5), 100, "iniciada", TipoDeCarrera.CARRERA_NORMAL);
		carreraService.agregarCaballo(iniciada, caballo);
		carreraService.agregarCaballo(iniciada, caballo1);
		carreraService.agregarCaballo(iniciada, caballo2);
		UsernamePasswordAuthenticationToken authAdmin = new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authAdmin);
		apostadorService.apostar("Ganador", 100, "veloz", "iniciada");
		UsernamePasswordAuthenticationToken authPepe= new UsernamePasswordAuthenticationToken(pepe, null, pepe.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authPepe);
		apostadorService.apostar("Segundo", 100, "veloz", "iniciada");
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	@EventListener
	public void onApplicationEvent(ContextClosedEvent event) {
		List<Map<String, Object>> tables = jdbcTemplate.queryForList("SHOW TABLES");
		List<String> tableNames = tables.stream()
				.map(table -> table.values().iterator().next().toString())
				.toList();

		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");

		tableNames.forEach(tableName -> jdbcTemplate.execute("TRUNCATE TABLE " + tableName));

		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
		apostadorService.removeAll();
		caballoService.removeAll();
		carreraService.removeAll();
		apuestaServiec.removeAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(CarrerasDeCaballosApplication.class, args);
	}

}
