package com.tecnicasProgramacion.carrerasDeCaballos.controller.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.ApostadorController;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.ApostadorDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.ApostadorLoginDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.ApuestaDTO;
import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class MockMVCApostadorController {

    @Autowired
    private MockMvc mockMvcApostador;

    @Autowired
    private ApostadorController apostadorController;

    @Autowired
    private ObjectMapper objectMapper;

    public ResultActions performRequest(MockHttpServletRequestBuilder requestBuilder) throws Throwable {
        try {
            return mockMvcApostador.perform(requestBuilder);
        } catch (NestedServletException e) {
            throw (e.getCause() != null ? e.getCause() : e);
        }
    }

    public AuthResponseDTO login(String dni, String nombre, String clave, HttpStatus expectedStatus) throws Throwable {
        ApostadorLoginDTO dto = new ApostadorLoginDTO(dni, nombre, clave);
        String json = objectMapper.writeValueAsString(dto);
        String res =  performRequest(
                post("/apostador/login")
                        .contentType(MediaType.APPLICATION_JSON).content(json)
        )
                .andExpect(status().is(expectedStatus.value()))
                        .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(res, AuthResponseDTO.class);
    }

    public ApuestaDTO apostar(String carrera, String caballo, String tipo, float monto, HttpStatus expectedStatus) throws Throwable {
        ApuestaDTO dto = new ApuestaDTO(tipo, monto, caballo, carrera);
        String json = objectMapper.writeValueAsString(dto);
        String res =  performRequest(
                post("/apostador/apostar/" + carrera + "/" + caballo)
                        .contentType(MediaType.APPLICATION_JSON).content(json)
        )
                .andExpect(status().is(expectedStatus.value()))
                        .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(res, ApuestaDTO.class);
    }

    public ApostadorDTO perfil(HttpStatus expectedStatus) throws Throwable {
        String res =  performRequest(
                get("/apostador/perfil")
        )
                .andExpect(status().is(expectedStatus.value()))
                        .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(res, ApostadorDTO.class);
    }


}
