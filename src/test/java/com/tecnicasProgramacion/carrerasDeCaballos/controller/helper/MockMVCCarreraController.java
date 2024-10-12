package com.tecnicasProgramacion.carrerasDeCaballos.controller.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraConCaballosDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraInformacionDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.CarreraTerminadaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.util.NestedServletException;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class MockMVCCarreraController {

    @Autowired
    private MockMvc mockMvcCarrera;

    @Autowired
    private ObjectMapper objectMapper;

    public ResultActions performRequest(MockHttpServletRequestBuilder requestBuilder) throws Throwable {
        try {
            return mockMvcCarrera.perform(requestBuilder);
        } catch (NestedServletException e) {
            throw (e.getCause() != null ? e.getCause() : e);
        }
    }

    public CarreraDTO crearCarrera(CarreraDTO carreraDTO, HttpStatus expectedStatus) throws Throwable {
        String json = objectMapper.writeValueAsString(carreraDTO);
        String res = performRequest(
                post("/carrera/crear")
                        .contentType(MediaType.APPLICATION_JSON).content(json)
        )
                .andExpect(status().is(expectedStatus.value()))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(res, CarreraDTO.class);
    }

    public CarreraConCaballosDTO agregarCaballo(String carrera, String caballo, HttpStatus expectedStatus) throws Throwable {
        String res = performRequest(
                put("/carrera/agregarCaballo/" + carrera + "/" + caballo)
        )
                .andExpect(status().is(expectedStatus.value()))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(res, CarreraConCaballosDTO.class);
    }

    public CarreraTerminadaDTO iniciarCarrera(String carrera, HttpStatus expectedStatus) throws Throwable {
        String res = performRequest(
                put("/carrera/iniciar/" + carrera)
        )
                .andExpect(status().is(expectedStatus.value()))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(res, CarreraTerminadaDTO.class);
    }

    public List<CarreraInformacionDTO> listarCarreras(String pagina, String limite, HttpStatus expectedStatus) throws Throwable {
        String res = performRequest(
                get("/carrera/listar/" + pagina + "/" + limite)
        )
                .andExpect(status().is(expectedStatus.value()))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(res, objectMapper.getTypeFactory().constructCollectionType(List.class, CarreraInformacionDTO.class));
    }

}
