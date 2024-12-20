package com.tecnicasProgramacion.carrerasDeCaballos.utils;

import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Caballo;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.Carrera;
import com.tecnicasProgramacion.carrerasDeCaballos.modelo.carrera.TipoDeCarrera;
import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.ApostadorServiceImpl;
import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.ApuestaServiceImpl;
import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.CaballoServiceImpl;
import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.CarreraServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DataSpringServiceImpl  implements DataSpringService {

    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

    @Autowired
    private ApostadorServiceImpl apostadorService;


    @Autowired
    private CaballoServiceImpl caballoService;

    @Autowired
    private CarreraServiceImpl carreraService;

    @Autowired
    private ApuestaServiceImpl apuestaService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void loadData() {
        apostadorService.crearApostador("1234", passwordEncoder.encode("admin"), "admin", true);
        apostadorService.crearApostador("567", passwordEncoder.encode("pepe"), "pepe", false);
        Caballo caballo = caballoService.crearCaballo("veloz", 50.0f, 10f, 5);
        Caballo caballo1 = caballoService.crearCaballo("caballo1", 40f, 22f, 10);
        Caballo caballo2 = caballoService.crearCaballo("caballo2", 20f, 33f, 23);
        Caballo caballo3 = caballoService.crearCaballo("caballo3", 44f, 11f, 33);
        Carrera carrera = carreraService.crearCarrera(LocalDateTime.of(2024, 12, 10, 10, 10), 100, "carrera1", TipoDeCarrera.CARRERA_NORMAL);
        carreraService.agregarCaballo(carrera, caballo);
        carreraService.agregarCaballo(carrera, caballo1);
        carreraService.agregarCaballo(carrera, caballo2);
        Carrera carrera2 = carreraService.crearCarrera(LocalDateTime.of(2024, 12, 10, 10, 10), 100, "carrera2", TipoDeCarrera.CARRERA_NORMAL);
        Carrera iniciada = carreraService.crearCarrera(LocalDateTime.now().plusSeconds(1), 100, "iniciada", TipoDeCarrera.CARRERA_NORMAL);
        Carrera paraIniciar = carreraService.crearCarrera(LocalDateTime.now().plusSeconds(1), 100, "paraIniciar", TipoDeCarrera.CARRERA_NORMAL);
        carreraService.agregarCaballo(paraIniciar, caballo);
        carreraService.agregarCaballo(paraIniciar, caballo1);
        carreraService.agregarCaballo(paraIniciar, caballo2);
    }

    @Override
    public void cleanUp() {

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
        apuestaService.removeAll();
    }
}
