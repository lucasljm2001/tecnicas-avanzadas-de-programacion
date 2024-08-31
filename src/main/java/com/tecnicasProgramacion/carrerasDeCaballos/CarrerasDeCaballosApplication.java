package com.tecnicasProgramacion.carrerasDeCaballos;

import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.ApostadorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CarrerasDeCaballosApplication {
	@Autowired
	private ApostadorServiceImpl apostadorService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
		apostadorService.crearApostador("1234", passwordEncoder.encode("admin"), "admin", true);
	}

	public static void main(String[] args) {
		SpringApplication.run(CarrerasDeCaballosApplication.class, args);
	}

}
