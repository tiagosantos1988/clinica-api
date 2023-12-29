package com.leucotron.api.entity.dto;

import jakarta.validation.constraints.NotNull;

public record MedicoUpdateDTO(@NotNull Long id, String nome, String email, EnderecoDTO endereco) {
}
