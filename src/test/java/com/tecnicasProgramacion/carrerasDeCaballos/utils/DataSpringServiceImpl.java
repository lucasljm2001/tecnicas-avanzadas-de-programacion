package com.tecnicasProgramacion.carrerasDeCaballos.utils;

import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.ApostadorServiceImpl;
import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.ApuestaServiceImpl;
import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.CaballoServiceImpl;
import com.tecnicasProgramacion.carrerasDeCaballos.service.impl.CarreraServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

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
