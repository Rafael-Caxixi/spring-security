package com.example.spring_security.dto;

import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDto(
        @NotNull
        String login,
        @NotNull
        String senha) {
}
