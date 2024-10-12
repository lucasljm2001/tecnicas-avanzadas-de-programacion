package com.tecnicasProgramacion.carrerasDeCaballos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Configuration
@ComponentScan(basePackages = "com.tecnicasProgramacion.carrerasDeCaballos")
public class MockMVCConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
    }

    @Bean
    public MockMvc mockMvcApostador(ApostadorController apostadorController) {
        return MockMvcBuilders.standaloneSetup(apostadorController).build();
    }

    @Bean
    public MockMvc mockMvcCarrera(CarreraController carreraController) {
        return MockMvcBuilders.standaloneSetup(carreraController).build();
    }
}
