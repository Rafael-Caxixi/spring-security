package com.example.spring_security.services;

import com.example.spring_security.dto.UsuarioRequestDto;
import com.example.spring_security.dto.UsuarioResponseDto;
import com.example.spring_security.entities.Usuario;
import com.example.spring_security.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrar(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario(usuarioRequestDto.login(), usuarioRequestDto.senha());
        usuarioRepository.save(usuario);
    }

    public List<UsuarioResponseDto> listarUsuarios(){
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioResponseDto(usuario.getLogin()))
                .toList();
    }


//    public String login(String login, String senha) {
//
//    }

}
