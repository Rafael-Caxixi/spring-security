package com.example.spring_security.controllers;

import com.example.spring_security.dto.UsuarioRequestDto;
import com.example.spring_security.dto.UsuarioResponseDto;
import com.example.spring_security.repositories.UsuarioRepository;
import com.example.spring_security.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioRequestDto dto) {
        usuarioService.cadastrar(dto);
        return  ResponseEntity.ok().body("Cadastro realizado com sucesso");
    }

    @GetMapping
    public  ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        return ResponseEntity.ok().body(usuarioService.listarUsuarios());
    }

//    @PostMapping("/login")
//    public ResponseEntity<UsuarioResponseDto> login() {
//
//    }
}
