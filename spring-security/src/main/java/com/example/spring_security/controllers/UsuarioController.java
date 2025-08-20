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
@RequestMapping("/usuarios")
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioRequestDto dto) {
        if(usuarioService.login(dto.login(), dto.senha())){
        return ResponseEntity.ok().body("Login realizado com sucesso");
        }
        return ResponseEntity.status(401).body("Login ou senha incorretos");
    }
}
