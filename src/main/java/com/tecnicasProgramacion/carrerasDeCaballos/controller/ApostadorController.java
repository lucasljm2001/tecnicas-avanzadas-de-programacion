package com.tecnicasProgramacion.carrerasDeCaballos.controller;

import com.tecnicasProgramacion.carrerasDeCaballos.controller.dto.*;
import com.tecnicasProgramacion.carrerasDeCaballos.service.ApostadorService;
import com.tecnicasProgramacion.carrerasDeCaballos.utils.JwtService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/apostador")
public class ApostadorController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApostadorService apostadorService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/crear")
    public ApostadorDTO registrarApostador(@RequestBody @Valid ApostadorLoginDTO apostadorDTO) {
        return new ApostadorDTO( apostadorService.crearApostador(apostadorDTO.getDni(), passwordEncoder.encode(apostadorDTO.getClave()), apostadorDTO.getNombre() ,apostadorDTO.getEsAdmin()));
    }

    @GetMapping("/perfil")
    public ApostadorDTO perfil() {
        return new ApostadorDTO(apostadorService.recuperarApostador(SecurityContextHolder.getContext().getAuthentication().getName()).get());
    }

    @PostMapping("/apostar/{carrera}/{caballo}")
    public ApuestaDTO apostar(@PathVariable String carrera,
                              @PathVariable String caballo,
                              @RequestBody @Valid ApuestaDTO apuestaDTO) {
        return new ApuestaDTO(apostadorService.apostar(apuestaDTO.getTipo(), apuestaDTO.getMonto(), caballo, carrera), carrera);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid ApostadorLoginDTO authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getDni(), authRequest.getClave())
            );

            final String jwt = jwtService.generateToken(authRequest.getDni());

            AuthResponseDTO authResponseDto = new AuthResponseDTO();
            authResponseDto.setAccessToken(jwt);

            return new ResponseEntity<>(authResponseDto, HttpStatus.OK);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new ErrorDTO("Credenciales invalidas"));
        }
    }


}
