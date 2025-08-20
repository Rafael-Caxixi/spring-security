package com.example.spring_security.services;

import com.example.spring_security.dto.UsuarioRequestDto;
import com.example.spring_security.dto.UsuarioResponseDto;
import com.example.spring_security.entities.Usuario;
import com.example.spring_security.repositories.UsuarioRepository;
import com.example.spring_security.security.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordService passwordService;

    public void cadastrar(UsuarioRequestDto usuarioRequestDto) {
        try {
            if(usuarioRepository.findByLogin(usuarioRequestDto.login()).isPresent()) {
                throw new RuntimeException("Usuário já cadastrado");
            }

            Usuario usuario = new Usuario(usuarioRequestDto.login(), passwordService.encodePassword(usuarioRequestDto.senha()));
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<UsuarioResponseDto> listarUsuarios() {
        try {
            return usuarioRepository.findAll().stream()
                    .map(usuario -> new UsuarioResponseDto(usuario.getLogin()))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean login(String login, String senha) {
        try {
            Usuario usuario = usuarioRepository.findByLogin(login)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            return passwordService.matches(senha, usuario.getSenha());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
